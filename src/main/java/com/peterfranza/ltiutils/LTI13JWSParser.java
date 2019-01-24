package com.peterfranza.ltiutils;

import java.security.Key;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.peterfranza.ltiutils.jwk.JWK;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;
import com.peterfranza.ltiutils.objects.LaunchJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;

public class LTI13JWSParser {

	public static final String LTI_MESSAGE_TYPE = "https://purl.imsglobal.org/spec/lti/claim/message_type";
	public static final String LTI_MESSAGE_TYPE_RESOURCE_LINK = "LtiResourceLinkRequest";

	public static final String LTI_VERSION = "https://purl.imsglobal.org/spec/lti/claim/version";
	public static final String LTI_VERSION_3 = "1.3.0";



	public static Optional<LaunchJWT> optionallyConvert(SignatureKeyProvider keyProvider,
			HttpServletRequest request) {
		try {
			return Optional.of(convertOrThrow(keyProvider, request, LTI13JWSParser::self));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public static LaunchJWT convertOrThrow(SignatureKeyProvider keyProvider, HttpServletRequest request)
			throws Exception {
		return convertOrThrow(keyProvider, request, LTI13JWSParser::self);
	}

	public static LaunchJWT convertOrThrow(SignatureKeyProvider keyProvider, HttpServletRequest request,
			Function<Exception, ? extends Exception> exceptionSupplier) throws Exception {
		return convertOrThrow(request, () -> {
			return parserFactory(keyProvider);
		}, exceptionSupplier, LTI13JWSParser::convertToLaunch);
	}

	public static <T> T convertOrThrow(HttpServletRequest request,
			Supplier<JwtParser> parserSupplier,
			Function<Exception, ? extends Exception> exceptionSupplier,
			BiFunction<String, Jws<Claims>, T> objectCreator)
			throws Exception {
		try {
			String idToken = request.getParameter("id_token");
			Jws<Claims> jws = parserSupplier.get().parseClaimsJws(idToken);
			return objectCreator.apply(idToken, jws);
		} catch (Exception e) {
			throw exceptionSupplier.apply(e);
		}
	}

	public static SigningKeyResolver createResolverAdapter(SignatureKeyProvider keyProvider) {
		return new SigningKeyResolverAdapter() {
			@Override
			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims) {
				try {
					String kid = header.get(JWK.KEY_ID).toString();
					return keyProvider.getById(kid).orElseThrow(() -> {
						return new RuntimeException("Unable to load key for id: " + kid);
					}).getPublicKey();
				} catch (Exception e) {
					throw new RuntimeException("Error loading key", e);
				}
			}
		};
	}

	/**
	 * This creates the JWT Parser and sets up a basic configuration for it adding
	 * some claims as required values
	 * 
	 * @param keyProvider
	 * @return
	 */
	public static JwtParser parserFactory(SignatureKeyProvider keyProvider) {
		return Jwts.parser().setSigningKeyResolver(createResolverAdapter(keyProvider))
		.require(LTI_VERSION, LTI_VERSION_3)
		.require(LTI_MESSAGE_TYPE, LTI_MESSAGE_TYPE_RESOURCE_LINK);

	}

	public static LaunchJWT convertToLaunch(String idToken, Jws<Claims> claims) {
		return new Gson().fromJson(rawJwtBody(rawJwtBody(idToken)), LaunchJWT.class);
	}

	public static String rawJwtBody(String encoded) {
		String[] parts = encoded.split("\\.");
		if (parts.length != 2 && parts.length != 3) {
			return null;
		}
		byte[] bytes = java.util.Base64.getDecoder().decode(parts[1]);
		return new String(bytes);
	}

	public static String rawJwtHeader(String encoded) {
		String[] parts = encoded.split("\\.");
		if (parts.length != 2 && parts.length != 3) {
			return null;
		}
		byte[] bytes = java.util.Base64.getDecoder().decode(parts[0]);
		return new String(bytes);
	}

	private static <T> T self(T arg) {
		return arg;
	}

}
