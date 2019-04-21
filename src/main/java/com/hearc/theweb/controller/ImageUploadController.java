package com.hearc.theweb.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hearc.theweb.exception.StorageImageNotFoundException;
import com.hearc.theweb.services.StorageService;

@Controller
@RequestMapping(value = "/images")
public class ImageUploadController {
	private final StorageService storageService;

	// Source:
	// https://github.com/spring-guides/gs-uploading-files/blob/master/complete/src/main/java/hello/storage/FileSystemStorageService.java
	// Tuto https://spring.io/guides/gs/uploading-files/

	@Autowired
	public ImageUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		/*model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(ImageUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));*/
		// Don't needed cause upload is only for card
		return "/uploadform";
	}

	@GetMapping("/{filename:.+")
	@ResponseBody
	public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
		System.out.println("request "+ filename);
		Resource file = storageService.loadAsResource(filename);
		
		// Do we need it ? 
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/")
	public String handleImageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded" + file.getOriginalFilename() + " !");
		// How to use it for card ? 
		return "redirect:/";
	}

	@ExceptionHandler(StorageImageNotFoundException.class)
	public ResponseEntity<?> handleStorageImageNotFound(StorageImageNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}
