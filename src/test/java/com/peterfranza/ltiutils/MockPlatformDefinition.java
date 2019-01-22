package com.peterfranza.ltiutils;

public abstract class MockPlatformDefinition {

	private String keySetURL = "https://lti-ri.imsglobal.org/platforms/115/platform_keys/17.json";
	private String oauth2URL = "https://lti-ri.imsglobal.org/platforms/115/access_tokens";
	private String platformOIDCAuthURL = "https://lti-ri.imsglobal.org/platforms/115/authorizations/new";

	protected abstract LTI13KeyLoader getPlatformKeys();

	public String getPlatformOIDCAuthURL() {
		return platformOIDCAuthURL;
	}

}
