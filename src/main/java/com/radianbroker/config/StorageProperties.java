package com.radianbroker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	@Value("${app.storage.dir}")
	private String location;
	
	@Value("${app.storage.alias}")
	private String alias;
	

	public String getLocation() {
		if(!location.startsWith("/")) {
			location = "/" + location;
		}
		return location;
	}


	public String getAlias() {
		if(!alias.startsWith("/")) {
			alias = "/" + alias;
		}
		return alias;
	}

}

