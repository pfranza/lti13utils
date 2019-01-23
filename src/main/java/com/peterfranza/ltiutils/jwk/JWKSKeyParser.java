package com.peterfranza.ltiutils.jwk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.peterfranza.ltiutils.LTI13KeyLoader;

public class JWKSKeyParser {

	public List<LTI13KeyLoader> loadFromURL(URL url) throws IOException {
		URLConnection c = url.openConnection();
		try(InputStream is = c.getInputStream()) {
			JWKS jwks = new Gson().fromJson(new InputStreamReader(is), JWKS.class);
			return jwks.keys.stream().map(this::convert).collect(Collectors.toList());
		}
	}

	private LTI13KeyLoader convert(JWK keySpec) {
		return new LTI13KeyLoader() {

			@Override
			public Key loadPublicKey() throws Exception {
				return keySpec.getPublicKey();
			}

			@Override
			public Key loadPrivateKey() throws Exception {
				throw new RuntimeException("No Private Key Loaded from JWKS");
			}

			@Override
			public String getKeyPairIdentifier() {
				return keySpec.getId();
			}
		};
	}

	private static class JWKS {
		@SerializedName("keys")
		private Set<JWK> keys = new HashSet<>();
	}

}
