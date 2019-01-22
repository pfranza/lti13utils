package com.peterfranza.ltiutils.oidc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.peterfranza.ltiutils.utils.LTI13Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class LTI13Request {

	private HttpServletRequest request;
	private Jws<Claims> jws;

	public LTI13Request(HttpServletRequest request, Jws<Claims> jws) {
		this.request = request;
		this.jws = jws;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public Jws<Claims> getJws() {
		return jws;
	}

	public String getMessageType() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_MESSAGE_TYPE);
	}

	public String getVersion() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_VERSION);
	}

	public String getDeploymentId() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_DEPLOYMENT_ID);
	}

	public String getGivenName() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_GIVEN_NAME);
	}

	public String getFamilyName() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_FAMILY_NAME);
	}

	public String getMiddleName() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_MIDDLE_NAME);
	}

	public String getPicture() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_PICTURE);
	}

	public String getEmail() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_EMAIL);
	}

	public String getName() {
		return getStringFromLTIRequest(getJws(), LTI13Strings.LTI_NAME);
	}

	public List<String> getRoles() {
		return getListFromLTIRequest(getJws(), LTI13Strings.LTI_ROLES);
	}

	protected final String getStringFromLTIRequest(Jws<Claims> jws, String stringToGet) {
		if (jws.getBody().containsKey(stringToGet)) {
			return jws.getBody().get(stringToGet, String.class);
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	protected final String getStringFromLTIRequestMap(Map map, String stringToGet) {
		if (map.containsKey(stringToGet)) {
			return map.get(stringToGet).toString();
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	protected final Integer getIntegerFromLTIRequestMap(Map map, String integerToGet) {
		if (map.containsKey(integerToGet)) {
			try {
				return Integer.valueOf(map.get(integerToGet).toString());
			} catch (Exception ex) {
				logError("No integer when expected in: {0}. Returning null", integerToGet);
				return null;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<String> getListFromLTIRequestMap(Map map, String listToGet) {
		if (map.containsKey(listToGet)) {
			try {
				return (List) map.get(listToGet);
			} catch (Exception ex) {
				logError("No list when expected in: {0} Returning null", listToGet);
				return new ArrayList<>();
			}
		} else {
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	protected final Map<String, Object> getMapFromLTIRequest(Jws<Claims> jws, String mapToGet) {
		if (jws.getBody().containsKey(mapToGet)) {
			try {
				return jws.getBody().get(mapToGet, Map.class);
			} catch (Exception ex) {
				logError("No map integer when expected in: {0}. Returning null", mapToGet);
				return new HashMap<>();
			}
		} else {
			return new HashMap<>();
		}
	}

	@SuppressWarnings("unchecked")
	protected final List<String> getListFromLTIRequest(Jws<Claims> jws, String listToGet) {
		if (jws.getBody().containsKey(listToGet)) {
			try {
				return jws.getBody().get(listToGet, List.class);
			} catch (Exception ex) {
				logError("No map integer when expected in: " + listToGet + ". Returning	 null");
				return new ArrayList<>();
			}
		} else {
			return new ArrayList<>();
		}
	}

	protected void logError(String error, String... args) {

	}
}
