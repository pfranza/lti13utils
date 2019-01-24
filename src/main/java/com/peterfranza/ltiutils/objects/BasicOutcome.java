package com.peterfranza.ltiutils.objects;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")
/*
    "https://purl.imsglobal.org/spec/lti/claim/context": {
        "id": "6",
        "label": "12345",
        "title": "qwertyuio",
        "type": [
            "0987654321"
        ]
    },
 */
public class BasicOutcome {

	@Expose @SerializedName("lis_result_sourcedid")
	private String lis_result_sourcedid;
	@Expose @SerializedName("lis_outcome_service_url")
	private String lis_outcome_service_url;

	public String getLis_result_sourcedid() {
		return lis_result_sourcedid;
	}

	public void setLis_result_sourcedid(String lis_result_sourcedid) {
		this.lis_result_sourcedid = lis_result_sourcedid;
	}

	public String getLis_outcome_service_url() {
		return lis_outcome_service_url;
	}

	public void setLis_outcome_service_url(String lis_outcome_service_url) {
		this.lis_outcome_service_url = lis_outcome_service_url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lis_outcome_service_url == null) ? 0 : lis_outcome_service_url.hashCode());
		result = prime * result + ((lis_result_sourcedid == null) ? 0 : lis_result_sourcedid.hashCode());
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
		BasicOutcome other = (BasicOutcome) obj;
		if (lis_outcome_service_url == null) {
			if (other.lis_outcome_service_url != null)
				return false;
		} else if (!lis_outcome_service_url.equals(other.lis_outcome_service_url))
			return false;
		if (lis_result_sourcedid == null) {
			if (other.lis_result_sourcedid != null)
				return false;
		} else if (!lis_result_sourcedid.equals(other.lis_result_sourcedid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicOutcome [lis_result_sourcedid=" + lis_result_sourcedid + ", lis_outcome_service_url="
				+ lis_outcome_service_url + "]";
	}

}
