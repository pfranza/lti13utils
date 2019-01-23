package com.peterfranza.ltiutils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.peterfranza.ltiutils.jwk.JWKSKeyParser;

public class MockPlatformDefinition {

	private String keySetURL = "https://lti-ri.imsglobal.org/platforms/115/platform_keys/102.json";
	private String oauth2URL = "https://lti-ri.imsglobal.org/platforms/115/access_tokens";
	private String platformOIDCAuthURL = "https://lti-ri.imsglobal.org/platforms/115/authorizations/new";

	protected List<LTI13KeyLoader> getPlatformKeys() throws IOException {
		return new JWKSKeyParser().loadFromURL(new URL(keySetURL));
	}

	public String getPlatformOIDCAuthURL() {
		return platformOIDCAuthURL;
	}

}
