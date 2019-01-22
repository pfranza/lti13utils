package com.peterfranza.ltiutils;

import java.security.Key;
import java.util.Optional;
import java.util.function.Consumer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolverAdapter;

public class LTI13JWSParser {

	private LTI13KeyLoader keyResolver;
	private Consumer<String> errorConsumer;
	private Consumer<Jws<Claims>> successConsumer;
	private Consumer<Exception> exceptionConsumer;

	public LTI13JWSParser(LTI13KeyLoader keyResolver) {
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

	public void parse(String jwsRaw) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKeyResolver(new SigningKeyResolverAdapter() {
			@Override
			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims) {
				try {
					return keyResolver.loadPublicKey();
				} catch (Exception e) {
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
			}
		} catch (Exception e) {
			if (exceptionConsumer != null) {
				exceptionConsumer.accept(e);
			}
		}
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

}
