package com.peterfranza.ltiutils.oidc;

import com.peterfranza.ltiutils.LTI13KeyLoader;

public interface OIDCRedirectBuilder {

	String getOIDCEndpoint();
	
	String getClientID();
	
	default String getLoginHint() {
		return getStateBuilder().getLoginHint();
	}
	
	default String getMessageHint() {
		return getStateBuilder().getLtiMessageHint();
	}
	
	OIDCStateBuilder getStateBuilder();
	
	default String getScope() {
		return "openId";
	}

	default String getResponseType() {
		return "id_token";
	}

	default String getResponseMode() {
		return "form_post";
	}

	default String getRedirectURI() {
		return getStateBuilder().getTargetLinkUri();
	}

	default String getPrompt() {
		return "none";
	}
	
	default String buildRedirectURL(LTI13KeyLoader keyLoader) throws Exception {
		// @formatter:off
		 return new StringBuilder()
	                .append(getOIDCEndpoint())
	                .append("?client_id=")
	                .append(getClientID())
	                .append("&login_hint=")
	                .append(getLoginHint())
	                .append("&lti_message_hint=")
	                .append(getMessageHint())
	                .append("&nonce=")
	                .append(getStateBuilder().getNonce())
	                .append("&prompt=")
	                .append(getPrompt())
	                .append("&redirect_uri=")
	                .append(getRedirectURI())
	                .append("&response_mode=")
	                .append(getResponseMode())
	                .append("&response_type=")
	                .append(getResponseType())
	                .append("&scope=")
	                .append(getScope())
	                .append("&state=")
	                .append(getStateBuilder().build(keyLoader)).toString();
		// @formatter:on
	}

	
	
}
