package com.radianbroker.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface FileSystemStorageService {

	Resource loadAsResource(String documentPath) throws Exception;
	Path load(String documentPath) throws Exception;
}
