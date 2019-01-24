package com.peterfranza.ltiutils.objects;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
	"https://purl.imsglobal.org/spec/lti-nrps/claim/namesroleservice": {
		"context_memberships_url": "https://www.myuniv.example.com/2344/memberships",
		"service_version": "2.0"
	}
 */
public class NamesAndRoles {

	public static String SERVICE_VERSION_LTI13 = "2.0";

	@Expose @SerializedName("context_memberships_url")
	private String context_memberships_url;
	@Expose @SerializedName("service_version")
	private String service_version = SERVICE_VERSION_LTI13;

	public String getContext_memberships_url() {
		return context_memberships_url;
	}

	public void setContext_memberships_url(String context_memberships_url) {
		this.context_memberships_url = context_memberships_url;
	}

	public String getService_version() {
		return service_version;
	}

	public void setService_version(String service_version) {
		this.service_version = service_version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context_memberships_url == null) ? 0 : context_memberships_url.hashCode());
		result = prime * result + ((service_version == null) ? 0 : service_version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NamesAndRoles other = (NamesAndRoles) obj;
		if (context_memberships_url == null) {
			if (other.context_memberships_url != null)
				return false;
		} else if (!context_memberships_url.equals(other.context_memberships_url))
			return false;
		if (service_version == null) {
			if (other.service_version != null)
				return false;
		} else if (!service_version.equals(other.service_version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NamesAndRoles [context_memberships_url=" + context_memberships_url + ", service_version="
				+ service_version + "]";
	}


}
