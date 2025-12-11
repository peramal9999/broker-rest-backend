package com.radianbroker.entity;

import java.io.Serializable;

public class PacsConfig implements Serializable {

	private static final long serialVersionUID = -6321992454161631336L;
	private String baseUrl;
	private String authToken;
	private String imageViewer;
	private String defaultPacsUser;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getImageViewer() {
		return imageViewer;
	}

	public void setImageViewer(String imageViewer) {
		this.imageViewer = imageViewer;
	}

	public String getDefaultPacsUser() {
		return defaultPacsUser;
	}

	public void setDefaultPacsUser(String defaultPacsUser) {
		this.defaultPacsUser = defaultPacsUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PacsConfig [baseUrl=" + baseUrl + ", authToken=" + authToken + ", imageViewer=" + imageViewer
				+ ", defaultPacsUser=" + defaultPacsUser + "]";
	}

}
