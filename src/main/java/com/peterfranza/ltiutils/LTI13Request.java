package com.peterfranza.ltiutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.peterfranza.ltiutils.objects.LaunchJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class LTI13Request {

	public static final String LTI_MESSAGE_TYPE = "https://purl.imsglobal.org/spec/lti/claim/message_type";
	public static final String LTI_VERSION = "https://purl.imsglobal.org/spec/lti/claim/version";
	public static final String LTI_MESSAGE_TYPE_RESOURCE_LINK = "LtiResourceLinkRequest";
	public static final String LTI_TARGET_LINK_URI = "https://purl.imsglobal.org/spec/lti/claim/target_link_uri";

	public static final String LTI_VERSION_3 = "1.3.0";

	private String idToken;

	private HttpServletRequest request;
	private Jws<Claims> jws;
	

	LTI13Request(HttpServletRequest request, Jws<Claims> jws, String idToken) {
		this.request = request;
		this.jws = jws;
		this.idToken = idToken;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public Jws<Claims> getJws() {
		return jws;
	}

	public String getRawSource() {
		return idToken;
	}

	public String getMessageType() {
		return getStringFromLTIRequest(getJws(), LTI_MESSAGE_TYPE);
	}

	public LaunchJWT getLaunch() {
		return parseBodyAs(LaunchJWT.class);
	}

	protected final <T> T parseBodyAs(Class<T> clazz) {
		return new Gson().fromJson(rawJwtBody(getRawSource()), clazz);
	}

	protected static final String getStringFromLTIRequest(Jws<Claims> jws, String stringToGet) {
		if (jws.getBody().containsKey(stringToGet)) {
			return jws.getBody().get(stringToGet, String.class);
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	protected static final String getStringFromLTIRequestMap(Map map, String stringToGet) {
		if (map.containsKey(stringToGet)) {
			return map.get(stringToGet).toString();
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	protected static final Integer getIntegerFromLTIRequestMap(Map map, String integerToGet) {
		if (map.containsKey(integerToGet)) {
			try {
				return Integer.valueOf(map.get(integerToGet).toString());
			} catch (Exception ex) {
				return null;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static final List<String> getListFromLTIRequestMap(Map map, String listToGet) {
		if (map.containsKey(listToGet)) {
			try {
				return (List) map.get(listToGet);
			} catch (Exception ex) {
				return new ArrayList<>();
			}
		} else {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	protected static final Map<String, Object> getMapFromLTIRequest(Jws<Claims> jws, String mapToGet) {
		if (jws.getBody().containsKey(mapToGet)) {
			try {
				return jws.getBody().get(mapToGet, Map.class);
			} catch (Exception ex) {
				return new HashMap<>();
			}
		} else {
			return new HashMap<>();
		}
	}

	@SuppressWarnings("unchecked")
	protected static final List<String> getListFromLTIRequest(Jws<Claims> jws, String listToGet) {
		if (jws.getBody().containsKey(listToGet)) {
			try {
				return jws.getBody().get(listToGet, List.class);
			} catch (Exception ex) {
				return new ArrayList<>();
			}
		} else {
			return new ArrayList<>();
		}
	}

	private static String rawJwtBody(String encoded) {
		String[] parts = encoded.split("\\.");
		if (parts.length != 2 && parts.length != 3) {
			return null;
		}
		byte[] bytes = java.util.Base64.getDecoder().decode(parts[1]);
		return new String(bytes);
	}


}
