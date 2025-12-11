package com.radianbroker.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import com.radianbroker.exceptions.StorageException;
import com.radianbroker.service.AppStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AppStorageServiceImpl implements AppStorageService {

	@Value("${app.client.baseUrl}")
	private String baseUrl;

	private final String appStorageDirectory;
	
	private final Path rootLocation;

	@Autowired
	public AppStorageServiceImpl(@Value("${app.storage.dir}") final String appStorageDirectory) {
		this.appStorageDirectory = appStorageDirectory;
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("win") || OS.startsWith("windows")) {
			this.rootLocation = Paths.get(appStorageDirectory);
		}else if(OS.equals("mac") || OS.equals("linux")) {
			String homeDir = System.getProperty("user.home") + appStorageDirectory;
			this.rootLocation = Paths.get(homeDir); 
		} else {
			throw new StorageException("OS not supported: " + OS);
		}
	}

	public String getStorageDirectory() {
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("mac") || OS.equals("linux")) {
			return this.appStorageDirectory;
		}
		return this.rootLocation.toString();
	}


	@Override
	public String getHttpUrl(String documentFilePath) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl).append(getStorageDirectory()).append(File.separator).append(documentFilePath);
		String url = sb.toString();
		return url.replace(File.separator, "/");
	}

}
