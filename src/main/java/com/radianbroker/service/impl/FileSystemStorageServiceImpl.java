package com.radianbroker.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.radianbroker.exceptions.StorageException;
import com.radianbroker.service.FileSystemStorageService;

@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageService{
	
	private final Path rootLocation=null;
	
	
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
