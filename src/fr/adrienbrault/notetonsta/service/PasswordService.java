package fr.adrienbrault.notetonsta.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

public final class PasswordService {
	
	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
	
	private static String asHex(byte[] buf)
	{
	    char[] chars = new char[2 * buf.length];
	    for (int i = 0; i < buf.length; ++i)
	    {
	        chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
	        chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
	    }
	    return new String(chars);
	}
	
	static public String generateSalt() {
		Random r = new SecureRandom();
		
		byte [] saltBytes = new byte[64];
		r.nextBytes(saltBytes);
		
		return asHex(saltBytes);
	}
	
	static public String hashPassword(String password, String salt) throws NoSuchAlgorithmException, NoSuchProviderException {
		String finalString = password + salt;
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte [] digestBytes = md.digest(finalString.getBytes());
		
		return asHex(digestBytes);
	}

}
