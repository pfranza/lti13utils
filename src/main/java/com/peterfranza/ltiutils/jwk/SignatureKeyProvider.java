package com.peterfranza.ltiutils.jwk;

import java.util.Optional;
import java.util.function.Function;

public interface SignatureKeyProvider {

	Optional<SignatureKey> getById(String keyId);

	default Optional<SignatureKey> getFirst() {
		return getFirst((key) -> {
			return true;
		});
	}

	Optional<SignatureKey> getFirst(Function<SignatureKey, Boolean> filter);

}
