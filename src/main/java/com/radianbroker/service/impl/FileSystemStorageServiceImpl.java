package com.radianbroker.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.radianbroker.config.StorageProperties;
import com.radianbroker.exceptions.StorageException;
import com.radianbroker.service.FileSystemStorageService;


@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageService{
	
	private final Path rootLocation;
	
	private String storageAlias;
	
	@Autowired
	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.storageAlias = properties.getAlias();
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.equals("win") || OS.startsWith("windows")) {
			this.rootLocation = Paths.get(properties.getLocation());
		}else if(OS.equals("mac") || OS.equals("linux")) {
//			String homeDir = System.getProperty("user.home") + properties.getLocation();
//			this.rootLocation = Paths.get(homeDir); 
			this.rootLocation = Paths.get(properties.getLocation());
		} else {
			throw new StorageException("OS not supported: " + OS);
		}
	}
	@Override
	public Resource loadAsResource(String documentPath)throws Exception {
		try {
			Path file = load(documentPath);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException("Could not read file at path: " + documentPath);
			}
		} catch (MalformedURLException e) {
			throw new StorageException("Could not read file at path: " + documentPath, e);
		}
	}

	@Override
	public Path load(String documentPath)throws Exception {
		return rootLocation.resolve(documentPath);
	}
	

}
