package com.peterfranza.ltiutils.utils;

import java.security.Key;

public interface LTI13KeyLoader {

	Key loadPublicKey() throws Exception;

	Key loadPrivateKey() throws Exception;

}
