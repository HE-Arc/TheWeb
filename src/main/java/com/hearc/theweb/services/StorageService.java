package com.hearc.theweb.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * StorageService Interface used for file management
 * 
 * @author kim.biloni
 *
 */
public interface StorageService {

	void init();

	void store(MultipartFile file);

	void storeCardPicture(MultipartFile file, long cardId);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
}
