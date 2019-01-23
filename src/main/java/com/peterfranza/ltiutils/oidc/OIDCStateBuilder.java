package com.peterfranza.ltiutils.oidc;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;

import com.peterfranza.ltiutils.LTI13KeyLoader;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public interface OIDCStateBuilder {

	// This is our own identifier, to know that we are the issuer.
	String getIssuer();

	default String getNonce() {
		return UUID.randomUUID().toString();
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

	public default String build(LTI13KeyLoader keyLoader) throws Exception {
		Date date = new Date();
		return Jwts.builder()
				.setHeaderParam("kid", keyLoader.getKeyPairIdentifier())
				.setIssuer(getIssuer())
				.setSubject(getPlatformISS()).setAudience(getAudience())
				.setExpiration(DateUtils.addSeconds(date, getExpiration())).setNotBefore(date).setIssuedAt(date)
				.setId(getNonce()).claim("original_iss", getOrigionalISS()).claim("loginHint", getLoginHint())
				.claim("ltiMessageHint", getLtiMessageHint())
				.claim("targetLinkUri", getTargetLinkUri())
				.claim("controller", getController())
				
				.signWith(SignatureAlgorithm.RS256, keyLoader.loadPrivateKey()).compact();
	}

}
