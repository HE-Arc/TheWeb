package com.hearc.theweb.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hearc.theweb.exception.StorageException;
import com.hearc.theweb.exception.StorageImageNotFoundException;
import com.hearc.theweb.properties.StorageProperties;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public void storeCardPicture(MultipartFile file, long cardId) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
			String cardFilename = new StringBuilder().append(getFilenameOfCardPicture(cardId)).append(extension)
					.toString();
			Path cardPath = this.rootLocation.resolve(cardFilename);

			file.transferTo(cardPath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Path loadCardPicture(long cardId) throws IOException {
		System.out.println("try to load card picture");
		String filename = getFilenameOfCardPicture(cardId);
		System.out.println("constructu filename as " + filename);
		Stream<Path> stream = Files.find(rootLocation, 1, (path, basicFilesAttributes) -> {
			File file = path.toFile();
			System.out.println("found file:" + file.toString());
			return !file.isDirectory() && file.getName().contains(filename);
		});
		Path file = null;
		try {
			file = stream.findFirst().get();
			System.out.println("found file: " + file.toString());
			return file;
		} finally {
			stream.close();
		}
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageImageNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new StorageImageNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public Resource loadCardPictureAsResource(String filename) {
		try {
			System.out.println("try to load card picture as resource");
			System.out.println("loadCardPictureAsResource: " + filename);
			Path file = this.rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageImageNotFoundException("Could not find picture of card: " + filename);
			}
		} catch (Exception e) {
			throw new StorageImageNotFoundException("Could not find picture of card: " + filename, e);
		}
	}
	
	@Override
	public void deleteCardPicture(long cardId) {
		try {
		Path filepath = loadCardPicture(cardId);
		if (filepath.equals(rootLocation))
			// No card found
			return;
		FileSystemUtils.deleteRecursively(filepath);
		} catch (Exception e) {
			System.out.println("An error has occured when deleting card " + cardId + ": " + e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		System.out.println("start storage service");
		try {
			Files.createDirectories(rootLocation);

		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	private static String getFilenameOfCardPicture(long cardId) {
		return new StringBuilder().append("card-").append(cardId).append(".").toString();
	}
}
