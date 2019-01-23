package com.peterfranza.ltiutils;

import java.security.Key;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolverAdapter;

public class LTI13JWSParser {

	private SignatureKeyProvider keyResolver;
	private Consumer<String> errorConsumer;
	private Consumer<Jws<Claims>> successConsumer;
	private Consumer<Exception> exceptionConsumer;

	public LTI13JWSParser(SignatureKeyProvider keyResolver) {
		this.keyResolver = keyResolver;
	}

	public LTI13JWSParser onError(Consumer<String> errorConsumer) {
		this.errorConsumer = errorConsumer;
		return this;
	}

	public LTI13JWSParser onSuccess(Consumer<Jws<Claims>> successConsumer) {
		this.successConsumer = successConsumer;
		return this;
	}

	public LTI13JWSParser onException(Consumer<Exception> exceptionConsumer) {
		this.exceptionConsumer = exceptionConsumer;
		return this;
	}

	public Optional<Jws<Claims>> parse(String jwsRaw) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKeyResolver(new SigningKeyResolverAdapter() {
			@Override
			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims) {
				try {
					String kid = header.get("kid").toString();
					return keyResolver.getById(kid).orElseThrow(() -> {
						return new RuntimeException("Unable to load key for id: " + kid);
					}).getPublicKey();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Error loading key", e);
				}
			}
		});

		try {
			Jws<Claims> jws = parser.parseClaimsJws(jwsRaw);
			Optional<String> errors = getLTI3RequestErrors(jws);
			if (errors.isPresent()) {
				if (errorConsumer != null) {
					errorConsumer.accept(errors.get());
				}
			} else {
				if (successConsumer != null) {
					successConsumer.accept(jws);
				}
				return Optional.of(jws);
			}
		} catch (Exception e) {
			if (exceptionConsumer != null) {
				exceptionConsumer.accept(e);
			}
		}

		return Optional.empty();
	}

	public Optional<String> getLTI3RequestErrors(Jws<Claims> jws) {

		String ltiVersion = jws.getBody().get(LTI13Request.LTI_VERSION, String.class);
		if (ltiVersion == null) {
			return Optional.of("Missing version");
		} else if (ltiVersion != null && !LTI13Request.LTI_VERSION_3.equals(ltiVersion)) {
			return Optional.of("Incorrect version: " + ltiVersion);
		}

		String ltiMessageType = jws.getBody().get(LTI13Request.LTI_MESSAGE_TYPE, String.class);
		if (ltiMessageType == null) {
			return Optional.of("Missing message type");
		} else if (ltiMessageType != null && !LTI13Request.LTI_MESSAGE_TYPE_RESOURCE_LINK.equals(ltiMessageType)) {
			return Optional.of("Incorrect message type: " + ltiMessageType + ". ");
		}

		return Optional.empty();
	}

	public static Optional<LTI13Request> optionallyConvert(SignatureKeyProvider keyProvider,
			HttpServletRequest request) {
		try {
			return Optional.of(convertOrThrow(keyProvider, request, (msg) -> {
				return new Exception(msg);
			}));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static LTI13Request convertOrThrow(SignatureKeyProvider keyProvider, HttpServletRequest request) {
		try {
			return convertOrThrow(keyProvider, request, (msg) -> {
				return new Exception(msg);
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static LTI13Request convertOrThrow(SignatureKeyProvider keyProvider, HttpServletRequest request,
			Function<String, ? extends Exception> exceptionSupplier) throws Exception {

		StringBuffer errorBuffer = new StringBuffer();

		Optional<Jws<Claims>> jws = new LTI13JWSParser(keyProvider).onException(exception -> {
			errorBuffer.append(exception.getMessage());
		}).onError(error -> {
			errorBuffer.append(error);
		}).parse(request.getParameter("id_token"));

		if (jws.isPresent()) {
			return new LTI13Request(request, jws.get());
		}

		String error = errorBuffer.toString();
		if (Strings.isNullOrEmpty(error)) {
			throw exceptionSupplier.apply("Unable to convert HttpServletRequest to JWS");
		} else {
			throw exceptionSupplier.apply(error);
		}
	}

}
