package com.peterfranza.ltiutils.jwk;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class CollectionSignatureKeyProvider implements SignatureKeyProvider {

	private Collection<SignatureKey> keys;

	public CollectionSignatureKeyProvider(Collection<SignatureKey> keys) {
		this.keys = keys;
	}

	@Override
	public Optional<SignatureKey> getById(String keyId) {
		return keys.stream().filter(k -> {
			return k.getKeyPairIdentifier().equalsIgnoreCase(keyId);
		}).findFirst();
	}

	@Override
	public Optional<SignatureKey> getFirst(Function<SignatureKey, Boolean> filter) {
		return keys.stream().filter(k -> {
			return filter.apply(k);
		}).findFirst();
	}

}
