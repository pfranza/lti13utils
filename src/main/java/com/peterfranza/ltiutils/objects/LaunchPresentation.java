package com.peterfranza.ltiutils.objects;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
    "https:\/\/purl.imsglobal.org\/spec\/lti\/claim\/launch_presentation": {
        "document_target": "iframe",
        "height": 320,
        "width": 240,
        "return_url": "https:\/\/lti-ri.imsglobal.org\/platforms\/7\/returns"
    },
 */
public class LaunchPresentation {

	@Expose @SerializedName("document_target")
	private String document_target = "iframe";
	@Expose @SerializedName("height")
	private Integer height;
	@Expose @SerializedName("width")
	private Integer width;
	@Expose @SerializedName("return_url")
	private String return_url;

	@Expose @SerializedName("css_url")
	private String css_url;

	public String getDocument_target() {
		return document_target;
	}

	public void setDocument_target(String document_target) {
		this.document_target = document_target;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getCss_url() {
		return css_url;
	}

	public void setCss_url(String css_url) {
		this.css_url = css_url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((css_url == null) ? 0 : css_url.hashCode());
		result = prime * result + ((document_target == null) ? 0 : document_target.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((return_url == null) ? 0 : return_url.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		LaunchPresentation other = (LaunchPresentation) obj;
		if (css_url == null) {
			if (other.css_url != null)
				return false;
		} else if (!css_url.equals(other.css_url))
			return false;
		if (document_target == null) {
			if (other.document_target != null)
				return false;
		} else if (!document_target.equals(other.document_target))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (return_url == null) {
			if (other.return_url != null)
				return false;
		} else if (!return_url.equals(other.return_url))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LaunchPresentation [document_target=" + document_target + ", height=" + height + ", width=" + width
				+ ", return_url=" + return_url + ", css_url=" + css_url + "]";
	}

}
