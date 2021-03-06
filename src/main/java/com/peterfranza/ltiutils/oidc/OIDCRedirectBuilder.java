package com.peterfranza.ltiutils.oidc;

import javax.servlet.http.HttpServletRequest;

import com.peterfranza.ltiutils.jwk.SignatureKey;

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

	default String buildRedirectURL(SignatureKey keyLoader) throws Exception {
		// @formatter:off
		return new StringBuilder().append(getOIDCEndpoint()).append("?client_id=").append(getClientID())
				.append("&login_hint=").append(getLoginHint()).append("&lti_message_hint=").append(getMessageHint())
				.append("&nonce=").append(getStateBuilder().getNonce()).append("&prompt=").append(getPrompt())
				.append("&redirect_uri=").append(getRedirectURI()).append("&response_mode=").append(getResponseMode())
				.append("&response_type=").append(getResponseType()).append("&scope=").append(getScope())
				.append("&state=").append(getStateBuilder().build(keyLoader)).toString();
		// @formatter:on
	}

	public static OIDCRedirectBuilder createFromRequest(HttpServletRequest request, String localISS, String issuer,
			String controller, String audience, String clientId, String endpoint) throws Exception {

		return new OIDCRedirectBuilder() {

			@Override
			public String getOIDCEndpoint() {
				return endpoint;
			}

			@Override
			public String getClientID() {
				return clientId;
			}

			@Override
			public OIDCStateBuilder getStateBuilder() {
				return OIDCStateBuilder.fromRequest(request, localISS, issuer, controller, audience);
			}
		};
	}

}
