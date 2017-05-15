package com.sdi.infrastructure.model.encrypt;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AttributeConverterImpl implements AttributeConverter<String, String> {

//	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
//	private static final byte[] KEY = "StringKey".getBytes();
	@Override
	public String convertToDatabaseColumn(String pass) {
		return String.valueOf(pass.hashCode());
//		Key key = new SecretKeySpec(KEY, "AES");
//		try {
//	         Cipher c = Cipher.getInstance(ALGORITHM);
//	         c.init(Cipher.ENCRYPT_MODE, key);
//	         return Base64.encodeBytes(c.doFinal(pass.getBytes()));
//	      } catch (Exception e) {
//	         throw new RuntimeException(e);
//	      }
	}

	@Override
	public String convertToEntityAttribute(String pass) {
		return pass;
//		Key key = new SecretKeySpec(KEY, "AES");
//	      try {
//	        Cipher c = Cipher.getInstance(ALGORITHM);
//	        c.init(Cipher.DECRYPT_MODE, key);
//	        return new String(c.doFinal(Base64.decode(pass)));
//	      } catch (Exception e) {
//	        throw new RuntimeException(e);
//	      }
	}

}
