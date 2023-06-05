package nl.andathen.central.util.image;

import java.awt.image.BufferedImage;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;

public class BufferedImageTypeDescriptor extends AbstractTypeDescriptor<BufferedImage> {
	private static final long serialVersionUID = -404001079081186327L;

	public BufferedImageTypeDescriptor() {
		super(BufferedImage.class);
	}
	
	@Override
	public String toString(BufferedImage value) {
		return (value == null) ? null : value.toString();
	}
	
	@Override
	public BufferedImage fromString(String string) {
		if (string == null) {
			return null;
		}
		else {
			BufferedImageConverter converter = new BufferedImageConverter();
			return converter.convertToEntityAttribute(string.getBytes());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <X> X unwrap(BufferedImage value, Class<X> type, WrapperOptions options) {
		if (value == null) {
			return null;
		}
		else if (String.class.isAssignableFrom(type)) {
		      return (X)value.toString();
		}
		else {
			throw unknownUnwrap(type);
		}
	}
	
	@Override
	public <X> BufferedImage wrap(X value, WrapperOptions options) {
		if (value == null) {
			return null;
		}
		else if (String.class.isInstance(value)) {
			BufferedImageConverter converter = new BufferedImageConverter();
			return converter.convertToEntityAttribute(((String)value).getBytes());
		}
		else {
			throw unknownWrap(value.getClass());
		}	  
	}
}
