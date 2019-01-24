package com.peterfranza.ltiutils.objects;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
    "https://purl.imsglobal.org/spec/lti/claim/lis": {
      "person_sourcedid": "example.edu:71ee7e42-f6d2-414a-80db-b69ac2defd4",
      "course_offering_sourcedid": "example.edu:SI182-F16",
      "course_section_sourcedid": "example.edu:SI182-001-F16"
    }
 */
public class LaunchLIS {

	public static final String SCOPE_NAMES_AND_ROLES = "https://purl.imsglobal.org/spec/lti-nrps/scope/contextmembership.readonly";

	@Expose @SerializedName("person_sourcedid")
	private String person_sourcedid;
	@Expose @SerializedName("course_offering_sourcedid")
	private String course_offering_sourcedid;
	@Expose @SerializedName("course_section_sourcedid")
	private String course_section_sourcedid;
	@Expose @SerializedName("version")
	private List<String> version;

	public String getPerson_sourcedid() {
		return person_sourcedid;
	}

	public void setPerson_sourcedid(String person_sourcedid) {
		this.person_sourcedid = person_sourcedid;
	}

	public String getCourse_offering_sourcedid() {
		return course_offering_sourcedid;
	}

	public void setCourse_offering_sourcedid(String course_offering_sourcedid) {
		this.course_offering_sourcedid = course_offering_sourcedid;
	}

	public String getCourse_section_sourcedid() {
		return course_section_sourcedid;
	}

	public void setCourse_section_sourcedid(String course_section_sourcedid) {
		this.course_section_sourcedid = course_section_sourcedid;
	}

	public List<String> getVersion() {
		return version;
	}

	public void setVersion(List<String> version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course_offering_sourcedid == null) ? 0 : course_offering_sourcedid.hashCode());
		result = prime * result + ((course_section_sourcedid == null) ? 0 : course_section_sourcedid.hashCode());
		result = prime * result + ((person_sourcedid == null) ? 0 : person_sourcedid.hashCode());
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
		LaunchLIS other = (LaunchLIS) obj;
		if (course_offering_sourcedid == null) {
			if (other.course_offering_sourcedid != null)
				return false;
		} else if (!course_offering_sourcedid.equals(other.course_offering_sourcedid))
			return false;
		if (course_section_sourcedid == null) {
			if (other.course_section_sourcedid != null)
				return false;
		} else if (!course_section_sourcedid.equals(other.course_section_sourcedid))
			return false;
		if (person_sourcedid == null) {
			if (other.person_sourcedid != null)
				return false;
		} else if (!person_sourcedid.equals(other.person_sourcedid))
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
		return "LaunchLIS [person_sourcedid=" + person_sourcedid + ", course_offering_sourcedid="
				+ course_offering_sourcedid + ", course_section_sourcedid=" + course_section_sourcedid + ", version="
				+ version + "]";
	}

}
