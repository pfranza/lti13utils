package com.peterfranza.ltiutils.jwk;

import java.security.Key;
import java.util.Optional;

public interface SignatureKey {

	String getKeyPairIdentifier();

	Key getPublicKey();

	Optional<Key> getPrivateKey();

}
