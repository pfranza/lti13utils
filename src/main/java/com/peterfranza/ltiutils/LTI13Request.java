package com.peterfranza.ltiutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class LTI13Request {

	// Those are used by the session.
	public static final String LTI_SESSION_USER_ID = "user_id";
	public static final String LTI_SESSION_USER_ROLE = "user_role";
	public static final String LTI_SESSION_CONTEXT_ID = "context_id";

	// BASICS
	public static final String LTI_MESSAGE_TYPE = "https://purl.imsglobal.org/spec/lti/claim/message_type";
	public static final String LTI_VERSION = "https://purl.imsglobal.org/spec/lti/claim/version";
	public static final String LTI_DEPLOYMENT_ID = "https://purl.imsglobal.org/spec/lti/claim/deployment_id";

	// RESOURCE_LINK
	public static final String LTI_LINK = "https://purl.imsglobal.org/spec/lti/claim/resource_link";
	public static final String LTI_LINK_ID = "id";
	public static final String LTI_LINK_DESC = "description";
	public static final String LTI_LINK_TITLE = "title";

	// ROLES
	public static final String LTI_ROLES = "https://purl.imsglobal.org/spec/lti/claim/roles";
	public static final String LTI_ROLE_SCOPE_MENTOR = "https://purl.imsglobal.org/spec/lti/claim/role_scope_mentor";

	// ROLES INSTITUTION
	public static final String LTI_ROLE_STUDENT = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Student";
	public static final String LTI_ROLE_LEARNER = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Learner";
	public static final String LTI_ROLE_MENTOR = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Mentor";
	public static final String LTI_ROLE_INSTRUCTOR = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Instructor ";
	public static final String LTI_ROLE_GUEST = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Guest";
	public static final String LTI_ROLE_OTHER = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Other";
	public static final String LTI_ROLE_STAFF = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Staff";
	public static final String LTI_ROLE_ALUMNI = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Alumni";
	public static final String LTI_ROLE_FACULTY = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Faculty";
	public static final String LTI_ROLE_MEMBER = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Member";
	public static final String LTI_ROLE_OBSERVER = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Observer";
	public static final String LTI_ROLE_PROSPECTIVE_STUDENT = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#ProspectiveStudent";
	public static final String LTI_ROLE_ADMIN = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Administrator";

	// ROLES MEMBERSHIP
	public static final String LTI_ROLE_MEMBERSHIP_ADMIN = "http://purl.imsglobal.org/vocab/lis/v2/membership#Administrator";
	public static final String LTI_ROLE_MEMBERSHIP_CONTENT_DEVELOPER = "http://purl.imsglobal.org/vocab/lis/v2/membership#ContentDeveloper";
	public static final String LTI_ROLE_MEMBERSHIP_INSTRUCTOR = "http://purl.imsglobal.org/vocab/lis/v2/membership#Instructor";
	public static final String LTI_ROLE_MEMBERSHIP_LEARNER = "http://purl.imsglobal.org/vocab/lis/v2/membership#Learner";
	public static final String LTI_ROLE_MEMBERSHIP_MENTOR = "http://purl.imsglobal.org/vocab/lis/v2/membership#Mentor";
	public static final String LTI_ROLE_MEMBERSHIP_MANAGER = "http://purl.imsglobal.org/vocab/lis/v2/membership#Manager";
	public static final String LTI_ROLE_MEMBERSHIP_MEMBER = "http://purl.imsglobal.org/vocab/lis/v2/membership#Member";
	public static final String LTI_ROLE_MEMBERSHIP_OFFICER = "http://purl.imsglobal.org/vocab/lis/v2/membership#Officer";

	// ROLES SYSTEM
	public static final String LTI_ROLE_SYS_ADMININSTRATOR = "http://purl.imsglobal.org/vocab/lis/v2/system/person#Administrator";
	public static final String LTI_ROLE_NONE = "http://purl.imsglobal.org/vocab/lis/v2/system/person#None";
	public static final String LTI_ROLE_GENERAL = "http://purl.imsglobal.org/vocab/lis/v2/system/person#User";
	public static final String LTI_ROLE_ACCOUNT_ADMIN = "http://purl.imsglobal.org/vocab/lis/v2/system/person#AccountAdmin";
	public static final String LTI_ROLE_CREATOR = "http://purl.imsglobal.org/vocab/lis/v2/system/person#Creator";
	public static final String LTI_ROLE_SYS_ADMIN = "http://purl.imsglobal.org/vocab/lis/v2/system/person#SysAdmin";
	public static final String LTI_ROLE_SYS_SUPPORT = "http://purl.imsglobal.org/vocab/lis/v2/system/person#SysSupport";

	// CONTEXT
	public static final String LTI_CONTEXT = "https://purl.imsglobal.org/spec/lti/claim/context";
	public static final String LTI_CONTEXT_ID = "id";
	public static final String LTI_CONTEXT_TYPE = "type";
	public static final String LTI_CONTEXT_LABEL = "label";
	public static final String LTI_CONTEXT_TITLE = "title";
	public static final String LTI_CONTEXT_TYPE_COURSE_TEMPLATE = "http://purl.imsglobal.org/vocab/lis/v2/course#CourseTemplate";
	public static final String LTI_CONTEXT_TYPE_COURSE_OFFERING = "http://purl.imsglobal.org/vocab/lis/v2/course#CourseOffering";
	public static final String LTI_CONTEXT_TYPE_COURSE_SECTION = "http://purl.imsglobal.org/vocab/lis/v2/course#CourseSection";
	public static final String LTI_CONTEXT_TYPE_GROUP = "http://purl.imsglobal.org/vocab/lis/v2/course#Group";

	// PLATFORM
	public static final String LTI_PLATFORM = "https://purl.imsglobal.org/spec/lti/claim/tool_platform";
	public static final String LTI_PLATFORM_GUID = "guid";
	public static final String LTI_PLATFORM_CONTACT_EMAIL = "contact_email";
	public static final String LTI_PLATFORM_DESC = "description";
	public static final String LTI_PLATFORM_NAME = "name";
	public static final String LTI_PLATFORM_URL = "url";
	public static final String LTI_PLATFORM_PRODUCT = "product_family_code";
	public static final String LTI_PLATFORM_PRODUCT_FAMILY_CODE = "product_family_code";
	public static final String LTI_PLATFORM_VERSION = "version";

	// LAUNCH_PRESENTATION
	public static final String LTI_LAUNCH_PRESENTATION = "https://purl.imsglobal.org/spec/lti/claim/launch_presentation";
	public static final String LTI_PRES_TARGET = "document_target";
	public static final String LTI_PRES_WIDTH = "width";
	public static final String LTI_PRES_HEIGHT = "height";
	public static final String LTI_PRES_RETURN_URL = "return_url";
	public static final String LTI_PRES_LOCALE = "locale";
	public static final String LTI_PRES_RETURN_URL_PARAMETER_ERROR_MSG = "_ltierrormsg";
	public static final String LTI_PRES_RETURN_URL_PARAMETER_MSG = "_ltimsg";
	public static final String LTI_PRES_RETURN_URL_PARAMETER_ERROR_LOG = "_ltierrorlog";
	public static final String LTI_PRES_RETURN_URL_PARAMETER_LOG = "_ltilog";

	// LIS
	public static final String LTI_LIS = "https://purl.imsglobal.org/spec/lti/claim/lis";
	public static final String LTI_PERSON_SOURCEDID = "person_sourcedid";
	public static final String LTI_COURSE_OFFERING_SOURCEDID = "course_offering_sourcedid";
	public static final String LTI_COURSE_SECTION_SOURCEDID = "course_section_sourcedid";

	// CUSTOM AND EXTENSION TEST
	public static final String LTI_EXTENSION = "https://www.example.com/extension";
	public static final String LTI_CUSTOM = "https://purl.imsglobal.org/spec/lti/claim/custom";

	// OTHERS
	public static final String LTI_NAME = "name";
	public static final String LTI_GIVEN_NAME = "given_name";
	public static final String LTI_FAMILY_NAME = "family_name";
	public static final String LTI_MIDDLE_NAME = "middle_name";
	public static final String LTI_PICTURE = "picture";
	public static final String LTI_EMAIL = "email";
	public static final String LTI_LOCALE = "locale";
	public static final String LTI_ENDPOINT = "https://purl.imsglobal.org/spec/lti-ags/claim/endpoint";
	public static final String LTI_ENDPOINT_SCOPE = "scope";
	public static final String LTI_ENDPOINT_LINEITEMS = "lineitems";
	public static final String LTI_NAMES_ROLE_SERVICE = "https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice";
	public static final String LTI_NAMES_ROLE_SERVICE_CONTEXT = "context_memberships_url";
	public static final String LTI_NAMES_ROLE_SERVICE_VERSIONS = "service_versions";

	public static final String LTI_CALIPER_ENDPOINT_SERVICE = "https://purl.imsglobal.org/spec/lti-ces/claim/caliper-endpoint-service";
	public static final String LTI_CALIPER_ENDPOINT_SERVICE_SCOPES = "scopes";
	public static final String LTI_CALIPER_ENDPOINT_SERVICE_URL = "caliper_endpoint_url";
	public static final String LTI_CALIPER_ENDPOINT_SERVICE_SESSION_ID = "caliper_federated_session_id";

	public static final String LTI_11_LEGACY_USER_ID = "https://purl.imsglobal.org/spec/lti/claim/lti11_legacy_user_id";
	public static final String LTI_NONCE = "nonce";

	public static final String LTI_CONSUMER_KEY = "oauth_consumer_key";

	public static final String LTI_MESSAGE_TYPE_RESOURCE_LINK = "LtiResourceLinkRequest";
	public static final String LTI_VERSION_3 = "1.3.0";
	public static final String LTI_TARGET_LINK_URI = "https://purl.imsglobal.org/spec/lti/claim/target_link_uri";

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
		return getStringFromLTIRequest(getJws(), LTI_MESSAGE_TYPE);
	}

	public String getVersion() {
		return getStringFromLTIRequest(getJws(), LTI_VERSION);
	}

	public String getDeploymentId() {
		return getStringFromLTIRequest(getJws(), LTI_DEPLOYMENT_ID);
	}

	public String getGivenName() {
		return getStringFromLTIRequest(getJws(), LTI_GIVEN_NAME);
	}

	public String getFamilyName() {
		return getStringFromLTIRequest(getJws(), LTI_FAMILY_NAME);
	}

	public String getMiddleName() {
		return getStringFromLTIRequest(getJws(), LTI_MIDDLE_NAME);
	}

	public String getPicture() {
		return getStringFromLTIRequest(getJws(), LTI_PICTURE);
	}

	public String getEmail() {
		return getStringFromLTIRequest(getJws(), LTI_EMAIL);
	}

	public String getName() {
		return getStringFromLTIRequest(getJws(), LTI_NAME);
	}

	public List<String> getRoles() {
		return getListFromLTIRequest(getJws(), LTI_ROLES);
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