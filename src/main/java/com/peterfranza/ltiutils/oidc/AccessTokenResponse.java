package com.peterfranza.ltiutils.oidc;

public class AccessTokenResponse {
	private String error;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String scope;
	private Long expired_in;

	@Override
	public String toString() {
		return "AccessTokenResponse [error=" + error + ", access_token=" + access_token + ", refresh_token="
				+ refresh_token + ", token_type=" + token_type + ", scope=" + scope + ", expired_in=" + expired_in
				+ "]";
	}


}
