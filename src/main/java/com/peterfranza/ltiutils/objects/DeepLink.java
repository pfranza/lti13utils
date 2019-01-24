package com.peterfranza.ltiutils.objects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("com.googlecode.jsonschema2pojo")

/*
  "https://purl.imsglobal.org/spec/lti-dl/claim/deep_linking_settings": {
    "deep_link_return_url": "https://platform.example/deep_links",
    "accept_types": ["link", "file", "html", "ltiResourceLink", "image"],
    "accept_media_types": "image/:::asterisk:::,text/html",
    "accept_presentation_document_targets": ["iframe", "window", "embed"],
    "accept_multiple": true,
    "auto_create": true,
    "title": "This is the default title",
    "text": "This is the default text",
    "data": "csrftoken:c7fbba78-7b75-46e3-9201-11e6d5f36f53"
  }
 */
public class DeepLink {

	public static String ACCEPT_TYPE_LINK = "link";
	public static String ACCEPT_TYPE_FILE = "file";
	public static String ACCEPT_TYPE_LTILINK = "ltiResourceLink";
	public static String ACCEPT_TYPE_IMAGE = "image";


	public static String TARGET_IFRAME = "iframe";
	public static String TARGET_WINDOW = "window";
	public static String TARGET_EMBED = "embed";

	@Expose @SerializedName("deep_link_return_url")
	private String deep_link_return_url;
	@Expose @SerializedName("accept_types")
	private List<String> accept_types = new ArrayList<String>();
	@Expose @SerializedName("accept_media_types")
	private String accept_media_types;
	@Expose @SerializedName("accept_presentation_document_targets")
	private List<String> accept_presentation_document_targets = new ArrayList<String>();
	@Expose @SerializedName("accept_multiple")
	private Boolean accept_multiple;
	@Expose @SerializedName("auto_create")
	private Boolean auto_create;
	@Expose @SerializedName("title")
	private String title;
	@Expose @SerializedName("text")
	private String text;
	@Expose @SerializedName("data")
	private String data;

	public String getDeep_link_return_url() {
		return deep_link_return_url;
	}

	public void setDeep_link_return_url(String deep_link_return_url) {
		this.deep_link_return_url = deep_link_return_url;
	}

	public List<String> getAccept_types() {
		return accept_types;
	}

	public void setAccept_types(List<String> accept_types) {
		this.accept_types = accept_types;
	}

	public String getAccept_media_types() {
		return accept_media_types;
	}

	public void setAccept_media_types(String accept_media_types) {
		this.accept_media_types = accept_media_types;
	}

	public List<String> getAccept_presentation_document_targets() {
		return accept_presentation_document_targets;
	}

	public void setAccept_presentation_document_targets(List<String> accept_presentation_document_targets) {
		this.accept_presentation_document_targets = accept_presentation_document_targets;
	}

	public Boolean getAccept_multiple() {
		return accept_multiple;
	}

	public void setAccept_multiple(Boolean accept_multiple) {
		this.accept_multiple = accept_multiple;
	}

	public Boolean getAuto_create() {
		return auto_create;
	}

	public void setAuto_create(Boolean auto_create) {
		this.auto_create = auto_create;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accept_media_types == null) ? 0 : accept_media_types.hashCode());
		result = prime * result + ((accept_multiple == null) ? 0 : accept_multiple.hashCode());
		result = prime * result + ((accept_presentation_document_targets == null) ? 0
				: accept_presentation_document_targets.hashCode());
		result = prime * result + ((accept_types == null) ? 0 : accept_types.hashCode());
		result = prime * result + ((auto_create == null) ? 0 : auto_create.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((deep_link_return_url == null) ? 0 : deep_link_return_url.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		DeepLink other = (DeepLink) obj;
		if (accept_media_types == null) {
			if (other.accept_media_types != null)
				return false;
		} else if (!accept_media_types.equals(other.accept_media_types))
			return false;
		if (accept_multiple == null) {
			if (other.accept_multiple != null)
				return false;
		} else if (!accept_multiple.equals(other.accept_multiple))
			return false;
		if (accept_presentation_document_targets == null) {
			if (other.accept_presentation_document_targets != null)
				return false;
		} else if (!accept_presentation_document_targets.equals(other.accept_presentation_document_targets))
			return false;
		if (accept_types == null) {
			if (other.accept_types != null)
				return false;
		} else if (!accept_types.equals(other.accept_types))
			return false;
		if (auto_create == null) {
			if (other.auto_create != null)
				return false;
		} else if (!auto_create.equals(other.auto_create))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (deep_link_return_url == null) {
			if (other.deep_link_return_url != null)
				return false;
		} else if (!deep_link_return_url.equals(other.deep_link_return_url))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeepLink [deep_link_return_url=" + deep_link_return_url + ", accept_types=" + accept_types
				+ ", accept_media_types=" + accept_media_types + ", accept_presentation_document_targets="
				+ accept_presentation_document_targets + ", accept_multiple=" + accept_multiple + ", auto_create="
				+ auto_create + ", title=" + title + ", text=" + text + ", data=" + data + "]";
	}

}
