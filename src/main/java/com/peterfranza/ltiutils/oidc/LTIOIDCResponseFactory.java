package com.peterfranza.ltiutils.oidc;

import javax.servlet.http.HttpServletRequest;

public class LTIOIDCResponseFactory {

	private HttpServletRequest request;

	public LTIOIDCResponseFactory(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isValidRequest() {
		return true;
	}

	public String generateReponseURL() {
		return null;
	}

}
