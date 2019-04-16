package com.hearc.theweb.properties;

import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";
	private String imagesLocation = "images";
	private String cardsLocation = "cards";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
        this.location = location;
    }

	public String getImagesLocation() {
		return Paths.get(location, imagesLocation).toString();
	}

	public void setImagesLocation(String imagesLocation) {
		this.imagesLocation = imagesLocation;
	}

	public String getCardsLocation() {
		return Paths.get(location, imagesLocation, cardsLocation).toString();
	}

	public void setCardsLocation(String cardsLocation) {
		this.cardsLocation = cardsLocation;
	}
}
