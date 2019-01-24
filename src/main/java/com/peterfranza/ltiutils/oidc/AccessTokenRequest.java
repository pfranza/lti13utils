package com.peterfranza.ltiutils.oidc;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.peterfranza.ltiutils.jwk.SignatureKeyProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AccessTokenRequest {

	private static String getJws(SignatureKeyProvider keys, String tokenEndpoint, String client) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 5);

		return Jwts.builder().setIssuer(client).setSubject(client).setAudience(tokenEndpoint).setExpiration(exp)
				.signWith(SignatureAlgorithm.RS256, keys.getFirst().get().getPrivateKey().orElseThrow(() -> {
					return new RuntimeException("Unable to sign, missing private key");
				})).compact();

	}

	private static String doAccessTokenRequest(String tokenEndpoint, String code, String signedJwt) throws Exception {

		List<NameValuePair> parameters = new LinkedList<>();
		parameters.add(new BasicNameValuePair(OAuth2Constants.GRANT_TYPE, OAuth2Constants.AUTHORIZATION_CODE));
		parameters.add(new BasicNameValuePair(OAuth2Constants.CODE, code));
		parameters.add(new BasicNameValuePair(OAuth2Constants.REDIRECT_URI, "https://localhost.com/"));
		parameters.add(new BasicNameValuePair(OAuth2Constants.CLIENT_ASSERTION_TYPE,
				OAuth2Constants.CLIENT_ASSERTION_TYPE_JWT));
		parameters.add(new BasicNameValuePair(OAuth2Constants.CLIENT_ASSERTION, signedJwt));

		HttpPost httpost = new HttpPost(tokenEndpoint);
		httpost.setEntity(new UrlEncodedFormEntity(parameters));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try (CloseableHttpResponse response2 = httpclient.execute(httpost)) {
			return EntityUtils.toString(response2.getEntity(), "UTF-8");
		}

	}

	public static AccessTokenResponse getAccessToken(SignatureKeyProvider keys, String tokenEndpoint, String client,
			String code)
			throws Exception {
		String resp = doAccessTokenRequest(tokenEndpoint, code, getJws(keys, tokenEndpoint, client));
		return new Gson().fromJson(resp, AccessTokenResponse.class);
	}

}
