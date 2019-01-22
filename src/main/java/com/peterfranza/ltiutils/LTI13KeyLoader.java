package com.peterfranza.ltiutils;

import java.security.Key;

public interface LTI13KeyLoader {

	String getKeyPairIdentifier();

	Key loadPublicKey() throws Exception;

	Key loadPrivateKey() throws Exception;

}
