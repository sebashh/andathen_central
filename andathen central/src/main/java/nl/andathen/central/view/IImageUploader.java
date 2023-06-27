package nl.andathen.central.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import jakarta.servlet.http.Part;

public interface IImageUploader {
	public Part getUploadedFile();
	public void setImage(BufferedImage img);
	public default void upload() {
		Paths.get(getUploadedFile().getSubmittedFileName()).getFileName().toString();
	    try (InputStream input = getUploadedFile().getInputStream()) {
	        byte[] fileContents = input.readAllBytes();
	        if (fileContents.length <= 10485760) {
	        	BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileContents));
	        	setImage(img);
	        }
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}
