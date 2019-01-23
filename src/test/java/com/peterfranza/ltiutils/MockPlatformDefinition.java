package com.peterfranza.ltiutils;

import java.io.IOException;
import java.net.URL;

import com.peterfranza.ltiutils.jwk.GuavaCachedSignatureKeyProvider;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;
import com.peterfranza.ltiutils.jwk.URLJWKSKeyProvider;

public class MockPlatformDefinition {

	private String keySetURL = "https://lti-ri.imsglobal.org/platforms/115/platform_keys/102.json";
	private String oauth2URL = "https://lti-ri.imsglobal.org/platforms/115/access_tokens";
	private String platformOIDCAuthURL = "https://lti-ri.imsglobal.org/platforms/115/authorizations/new";

	protected SignatureKeyProvider getPlatformKeys() throws IOException {
		return new GuavaCachedSignatureKeyProvider(new URLJWKSKeyProvider(new URL(keySetURL)));
	}

	public String getKeySetURL() {
		return keySetURL;
	}

	public void setKeySetURL(String keySetURL) {
		this.keySetURL = keySetURL;
	}

	public String getOauth2URL() {
		return oauth2URL;
	}

	public void setOauth2URL(String oauth2url) {
		oauth2URL = oauth2url;
	}

	public void setPlatformOIDCAuthURL(String platformOIDCAuthURL) {
		this.platformOIDCAuthURL = platformOIDCAuthURL;
	}

	public String getPlatformOIDCAuthURL() {
		return platformOIDCAuthURL;
	}

}
