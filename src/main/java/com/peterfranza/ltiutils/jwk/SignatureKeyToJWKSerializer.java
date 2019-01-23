package com.peterfranza.ltiutils.jwk;

import java.math.BigInteger;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class SignatureKeyToJWKSerializer {

	private String usage = "sig";

	public String serialize(SignatureKey... keys) {
		JWKS set = new JWKS();
		set.keys = Arrays.asList(keys).stream().map(this::convert).collect(Collectors.toList());
		return new GsonBuilder().disableHtmlEscaping().create().toJson(set, JWKS.class);
	}

	private JWK convert(SignatureKey key) {
		Key pub = key.getPublicKey();
		if (pub instanceof RSAPublicKey) {
			return convert((RSAPublicKey) pub, key.getKeyPairIdentifier(), getUsage());
		}

		throw new RuntimeException("Unable to write JWK for key type " + pub.getFormat() + ":" + pub.getAlgorithm());
	}

	private JWK convert(RSAPublicKey publicKey, String id, String use) {
		JWK jwk = new JWK();
		jwk.setId(id);
		jwk.setUsage(use);
		jwk.setAlgorithm(publicKey.getAlgorithm());
		jwk.setModulus(base64URLEncode(publicKey.getModulus()));
		jwk.setExponent(base64URLEncode(publicKey.getPublicExponent()));
		return jwk;
	}

	private String base64URLEncode(BigInteger bigInt) {
		return Base64.encodeBase64String(Base64.encodeInteger(bigInt));
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	private static class JWKS {
		@SerializedName("keys")
		private List<JWK> keys = new ArrayList<>();
	}

}
