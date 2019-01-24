package com.peterfranza.ltiutils.objects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
    "https:\/\/purl.imsglobal.org\/spec\/lti-ags\/claim\/endpoint": {
        "scope": [
            "https:\/\/purl.imsglobal.org\/spec\/lti-ags\/scope\/result.readonly",
            "https:\/\/purl.imsglobal.org\/spec\/lti-ags\/scope\/score",
            "https:\/\/purl.imsglobal.org\/spec\/lti-ags\/scope\/lineitem.readonly",
            "https:\/\/purl.imsglobal.org\/spec\/lti-ags\/scope\/lineitem",
     ],
        "lineitems": "https:\/\/lti-ri.imsglobal.org\/platforms\/7\/contexts\/6\/line_items",
        "lineitem": "https:\/\/lti-ri.imsglobal.org\/platforms\/7\/contexts\/6\/line_items\/9"
    },
 */
public class Endpoint {

	/**
	 * Tool can access the results for its line items
	 */
	public static String SCOPE_RESULT_READONLY = "https://purl.imsglobal.org/spec/lti-ags/scope/result.readonly";
	/**
	 * Tool can publish score updates to the line items
	 */
	public static String SCOPE_SCORE = "https://purl.imsglobal.org/spec/lti-ags/scope/score";
	/**
	 * Tool can fully manage its line items including, adding and removing line items
	 */
	public static String SCOPE_LINEITEM = "https://purl.imsglobal.org/spec/lti-ags/scope/lineitem";
	/**
	 * Tool can query its line line items - no modification allowed
	 */
	public static String SCOPE_LINEITEM_READONLY = "https://purl.imsglobal.org/spec/lti-ags/scope/lineitem.readonly";

	@Expose @SerializedName("scope")
	private List<String> scope = new ArrayList<String>();
	@Expose @SerializedName("lineitems")
	private String lineitems;
	@Expose @SerializedName("lineitem")
	private String lineitem;

	public List<String> getScope() {
		return scope;
	}

	public void setScope(List<String> scope) {
		this.scope = scope;
	}

	public String getLineitems() {
		return lineitems;
	}

	public void setLineitems(String lineitems) {
		this.lineitems = lineitems;
	}

	public String getLineitem() {
		return lineitem;
	}

	public void setLineitem(String lineitem) {
		this.lineitem = lineitem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineitem == null) ? 0 : lineitem.hashCode());
		result = prime * result + ((lineitems == null) ? 0 : lineitems.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
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
		Endpoint other = (Endpoint) obj;
		if (lineitem == null) {
			if (other.lineitem != null)
				return false;
		} else if (!lineitem.equals(other.lineitem))
			return false;
		if (lineitems == null) {
			if (other.lineitems != null)
				return false;
		} else if (!lineitems.equals(other.lineitems))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endpoint [scope=" + scope + ", lineitems=" + lineitems + ", lineitem=" + lineitem + "]";
	}

}
