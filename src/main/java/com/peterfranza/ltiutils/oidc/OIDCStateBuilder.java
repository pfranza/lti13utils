package com.peterfranza.ltiutils.oidc;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;

import com.peterfranza.ltiutils.jwk.SignatureKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public interface OIDCStateBuilder {

	// This is our own identifier, to know that we are the issuer.
	String getIssuer();

	default String getNonce() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	String getPlatformISS();

	String getOrigionalISS();

	String getLoginHint();

	String getLtiMessageHint();

	String getTargetLinkUri();

	String getController();

	String getAudience();

	default int getExpiration() {
		return 3600;
	}

	public default String build(SignatureKey keyLoader) throws Exception {
		Date date = new Date();
		return Jwts.builder().setHeaderParam("kid", keyLoader.getKeyPairIdentifier()).setIssuer(getIssuer())
				.setSubject(getPlatformISS()).setAudience(getAudience())
				.setExpiration(DateUtils.addSeconds(date, getExpiration())).setNotBefore(date).setIssuedAt(date)
				.setId(getNonce()).claim("original_iss", getOrigionalISS()).claim("loginHint", getLoginHint())
				.claim("ltiMessageHint", getLtiMessageHint()).claim("targetLinkUri", getTargetLinkUri())
				.claim("controller", getController())

				.signWith(SignatureAlgorithm.RS256, keyLoader.getPrivateKey().orElseThrow(() -> {
					return new RuntimeException("Unable to sign, missing private key");
				})).compact();
	}

	public static OIDCStateBuilder fromRequest(HttpServletRequest request, String localISS, String issuer,
			String controller, String audience) {
		return new OIDCStateBuilder() {

			@Override
			public String getTargetLinkUri() {
				return request.getParameter("target_link_uri");
			}

			@Override
			public String getPlatformISS() {
				return localISS;
			}

			@Override
			public String getOrigionalISS() {
				return request.getParameter("iss");
			}

			@Override
			public String getLtiMessageHint() {
				return request.getParameter("lti_message_hint");
			}

			@Override
			public String getLoginHint() {
				return request.getParameter("login_hint");
			}

			@Override
			public String getIssuer() {
				return issuer;
			}

			@Override
			public String getController() {
				return controller;
			}

			@Override
			public String getAudience() {
				return audience;
			}
		};
	}

}
