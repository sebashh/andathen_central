package nl.andathen.central.util.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class BufferedImageConverter implements AttributeConverter<BufferedImage, byte[]> {

	 @Override
	 public byte[] convertToDatabaseColumn(BufferedImage image) {
		if ((image == null) || (image.getHeight() < 2) || (image.getWidth() < 2)) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
			ImageIO.write(image, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
        byte[] bytes = baos.toByteArray();
        return bytes;
	 }
 
	 @Override
	 public BufferedImage convertToEntityAttribute(byte[] binaryFormat) {
		if ((binaryFormat == null) || (binaryFormat.length < 10)) return null;
        InputStream is = new ByteArrayInputStream(binaryFormat);
		try {
			return ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	 }
	 
	 
}

