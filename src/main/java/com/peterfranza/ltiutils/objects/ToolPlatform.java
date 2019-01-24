package com.peterfranza.ltiutils.objects;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
 * "https:\/\/purl.imsglobal.org\/spec\/lti\/claim\/tool_platform": { "name":
 * "", "contact_email": "", "description": "", "url": "", "product_family_code":
 * "", "version": "1.0" },
 */
public class ToolPlatform {

	@Expose @SerializedName("name")
	private String name;
	@Expose @SerializedName("contact_email")
	private String contact_email;
	@Expose @SerializedName("description")
	private String description;
	@Expose @SerializedName("url")
	private String url;
	@Expose @SerializedName("product_family_code")
	private String product_family_code;
	@Expose @SerializedName("version")
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProduct_family_code() {
		return product_family_code;
	}

	public void setProduct_family_code(String product_family_code) {
		this.product_family_code = product_family_code;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact_email == null) ? 0 : contact_email.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((product_family_code == null) ? 0 : product_family_code.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		ToolPlatform other = (ToolPlatform) obj;
		if (contact_email == null) {
			if (other.contact_email != null)
				return false;
		} else if (!contact_email.equals(other.contact_email))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (product_family_code == null) {
			if (other.product_family_code != null)
				return false;
		} else if (!product_family_code.equals(other.product_family_code))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
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
		return "ToolPlatform [name=" + name + ", contact_email=" + contact_email + ", description=" + description
				+ ", url=" + url + ", product_family_code=" + product_family_code + ", version=" + version + "]";
	}

}
