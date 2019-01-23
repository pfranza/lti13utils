package com.peterfranza.ltiutils.jwk;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class URLJWKSKeyProvider implements SignatureKeyProvider {

	private URL url;

	public URLJWKSKeyProvider(URL url) {
		this.url = url;
	}

	private Set<JWK> loadFromURL(URL url) {
		try {
			URLConnection c = url.openConnection();
			try (InputStream is = c.getInputStream()) {
				JWKS jwks = new Gson().fromJson(new InputStreamReader(is), JWKS.class);
				return jwks.keys;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Optional<SignatureKey> getFirst(Function<SignatureKey, Boolean> filter) {
		return loadFromURL(url).stream().filter(jwk -> {
			return filter.apply(convert(jwk));
		}).map(this::convert).findFirst();
	}

	@Override
	public Optional<SignatureKey> getById(String keyId) {
		return loadFromURL(url).stream().filter(jwk -> {
			return jwk.getId().equals(keyId);
		}).map(this::convert).findFirst();
	}

	private SignatureKey convert(JWK keySpec) {
		return new SignatureKey() {

			@Override
			public Key getPublicKey() {
				return keySpec.getPublicKey();
			}

			@Override
			public Optional<Key> getPrivateKey() {
				return Optional.empty();
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
