package com.peterfranza.ltiutils.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

public class LaunchJWT extends BaseJWT {

	public static String CLAIM_PREFIX = "https://purl.imsglobal.org/spec/lti/claim/";

	public static String MESSAGE_TYPE_LAUNCH = "LtiResourceLinkRequest";
	public static String MESSAGE_TYPE_DEEP_LINK = "LtiDeepLinkingRequest";

	public static String ROLE_LEARNER = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Learner";
	public static String ROLE_INSTRUCTOR = "http://purl.imsglobal.org/vocab/lis/v2/institution/person#Instructor";

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/deployment_id")
	private String deployment_id;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/target_link_uri")
	private String target_link_uri;
	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/message_type")
	private String message_type = MESSAGE_TYPE_LAUNCH;
	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/version")
	private String version = "1.3.0";
	@Expose @SerializedName("given_name")
	private String given_name;
	@Expose @SerializedName("family_name")
	private String family_name;
	@Expose @SerializedName("middle_name")
	private String middle_name;
	@Expose @SerializedName("picture")
	private String picture;
	@Expose @SerializedName("email")
	private String email;
	@Expose @SerializedName("name")
	private String name;
	@Expose @SerializedName("locale")
	private String locale;
	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/custom")
	private Map<String, String> custom;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/roles")
	private List<String> roles = new ArrayList<String>();
	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/role_scope_mentor")
	private List<String> role_scope_mentor = new ArrayList<String>();

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/launch_presentation")
	private LaunchPresentation launch_presentation = new LaunchPresentation();

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/resource_link")
	private ResourceLink resource_link;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/lti1_1_user_id")
	private String lti1_1_user_id;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/context")
	private Context context;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/tool_platform")
	private ToolPlatform tool_platform;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti/claim/lis")
	private LaunchLIS lis;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti-ags/claim/endpoint")
	private Endpoint endpoint;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti-bo/claim/basicoutcome")
	private BasicOutcome basicoutcome;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice")
	private NamesAndRoles names_and_roles;

	@Expose @SerializedName("https://purl.imsglobal.org/spec/lti-dl/claim/deep_linking_settings")
	private DeepLink deep_link;

	public String getDeployment_id() {
		return deployment_id;
	}

	public void setDeployment_id(String deployment_id) {
		this.deployment_id = deployment_id;
	}

	public String getTarget_link_uri() {
		return target_link_uri;
	}

	public void setTarget_link_uri(String target_link_uri) {
		this.target_link_uri = target_link_uri;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Map<String, String> getCustom() {
		return custom;
	}

	public void setCustom(Map<String, String> custom) {
		this.custom = custom;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRole_scope_mentor() {
		return role_scope_mentor;
	}

	public void setRole_scope_mentor(List<String> role_scope_mentor) {
		this.role_scope_mentor = role_scope_mentor;
	}

	public LaunchPresentation getLaunch_presentation() {
		return launch_presentation;
	}

	public void setLaunch_presentation(LaunchPresentation launch_presentation) {
		this.launch_presentation = launch_presentation;
	}

	public ResourceLink getResource_link() {
		return resource_link;
	}

	public void setResource_link(ResourceLink resource_link) {
		this.resource_link = resource_link;
	}

	public String getLti1_1_user_id() {
		return lti1_1_user_id;
	}

	public void setLti1_1_user_id(String lti1_1_user_id) {
		this.lti1_1_user_id = lti1_1_user_id;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ToolPlatform getTool_platform() {
		return tool_platform;
	}

	public void setTool_platform(ToolPlatform tool_platform) {
		this.tool_platform = tool_platform;
	}

	public LaunchLIS getLis() {
		return lis;
	}

	public void setLis(LaunchLIS lis) {
		this.lis = lis;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	public BasicOutcome getBasicoutcome() {
		return basicoutcome;
	}

	public void setBasicoutcome(BasicOutcome basicoutcome) {
		this.basicoutcome = basicoutcome;
	}

	public NamesAndRoles getNames_and_roles() {
		return names_and_roles;
	}

	public void setNames_and_roles(NamesAndRoles names_and_roles) {
		this.names_and_roles = names_and_roles;
	}

	public DeepLink getDeep_link() {
		return deep_link;
	}

	public void setDeep_link(DeepLink deep_link) {
		this.deep_link = deep_link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((basicoutcome == null) ? 0 : basicoutcome.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((custom == null) ? 0 : custom.hashCode());
		result = prime * result + ((deep_link == null) ? 0 : deep_link.hashCode());
		result = prime * result + ((deployment_id == null) ? 0 : deployment_id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
		result = prime * result + ((family_name == null) ? 0 : family_name.hashCode());
		result = prime * result + ((given_name == null) ? 0 : given_name.hashCode());
		result = prime * result + ((launch_presentation == null) ? 0 : launch_presentation.hashCode());
		result = prime * result + ((lis == null) ? 0 : lis.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((lti1_1_user_id == null) ? 0 : lti1_1_user_id.hashCode());
		result = prime * result + ((message_type == null) ? 0 : message_type.hashCode());
		result = prime * result + ((middle_name == null) ? 0 : middle_name.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((names_and_roles == null) ? 0 : names_and_roles.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((resource_link == null) ? 0 : resource_link.hashCode());
		result = prime * result + ((role_scope_mentor == null) ? 0 : role_scope_mentor.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((target_link_uri == null) ? 0 : target_link_uri.hashCode());
		result = prime * result + ((tool_platform == null) ? 0 : tool_platform.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LaunchJWT other = (LaunchJWT) obj;
		if (basicoutcome == null) {
			if (other.basicoutcome != null)
				return false;
		} else if (!basicoutcome.equals(other.basicoutcome))
			return false;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (custom == null) {
			if (other.custom != null)
				return false;
		} else if (!custom.equals(other.custom))
			return false;
		if (deep_link == null) {
			if (other.deep_link != null)
				return false;
		} else if (!deep_link.equals(other.deep_link))
			return false;
		if (deployment_id == null) {
			if (other.deployment_id != null)
				return false;
		} else if (!deployment_id.equals(other.deployment_id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endpoint == null) {
			if (other.endpoint != null)
				return false;
		} else if (!endpoint.equals(other.endpoint))
			return false;
		if (family_name == null) {
			if (other.family_name != null)
				return false;
		} else if (!family_name.equals(other.family_name))
			return false;
		if (given_name == null) {
			if (other.given_name != null)
				return false;
		} else if (!given_name.equals(other.given_name))
			return false;
		if (launch_presentation == null) {
			if (other.launch_presentation != null)
				return false;
		} else if (!launch_presentation.equals(other.launch_presentation))
			return false;
		if (lis == null) {
			if (other.lis != null)
				return false;
		} else if (!lis.equals(other.lis))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (lti1_1_user_id == null) {
			if (other.lti1_1_user_id != null)
				return false;
		} else if (!lti1_1_user_id.equals(other.lti1_1_user_id))
			return false;
		if (message_type == null) {
			if (other.message_type != null)
				return false;
		} else if (!message_type.equals(other.message_type))
			return false;
		if (middle_name == null) {
			if (other.middle_name != null)
				return false;
		} else if (!middle_name.equals(other.middle_name))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (names_and_roles == null) {
			if (other.names_and_roles != null)
				return false;
		} else if (!names_and_roles.equals(other.names_and_roles))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (resource_link == null) {
			if (other.resource_link != null)
				return false;
		} else if (!resource_link.equals(other.resource_link))
			return false;
		if (role_scope_mentor == null) {
			if (other.role_scope_mentor != null)
				return false;
		} else if (!role_scope_mentor.equals(other.role_scope_mentor))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (target_link_uri == null) {
			if (other.target_link_uri != null)
				return false;
		} else if (!target_link_uri.equals(other.target_link_uri))
			return false;
		if (tool_platform == null) {
			if (other.tool_platform != null)
				return false;
		} else if (!tool_platform.equals(other.tool_platform))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LaunchJWT [deployment_id=" + deployment_id + ", target_link_uri=" + target_link_uri + ", message_type="
				+ message_type + ", version=" + version + ", given_name=" + given_name + ", family_name=" + family_name
				+ ", middle_name=" + middle_name + ", picture=" + picture + ", email=" + email + ", name=" + name
				+ ", locale=" + locale + ", custom=" + custom + ", roles=" + roles + ", role_scope_mentor="
				+ role_scope_mentor + ", launch_presentation=" + launch_presentation + ", resource_link="
				+ resource_link + ", lti1_1_user_id=" + lti1_1_user_id + ", context=" + context + ", tool_platform="
				+ tool_platform + ", lis=" + lis + ", endpoint=" + endpoint + ", basicoutcome=" + basicoutcome
				+ ", names_and_roles=" + names_and_roles + ", deep_link=" + deep_link + ", toString()="
				+ super.toString() + "]";
	}

}
