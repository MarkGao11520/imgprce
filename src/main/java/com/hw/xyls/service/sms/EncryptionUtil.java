package com.hw.xyls.service.sms;

import java.security.MessageDigest;

public class EncryptionUtil {
	public static final String encrypt(String password, String algorithm) {
		try {
			MessageDigest md = (MessageDigest) MessageDigest.getInstance(
					algorithm).clone();
			md.update(password.getBytes());

			return convert(md.digest());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return password;
	}

	private static String convert(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(convertDigit(bytes[i] >> 4));
			sb.append(convertDigit(bytes[i] & 0xF));
		}
		return sb.toString();
	}

	private static char convertDigit(int value) {
		value &= 15;
		if (value >= 10) {
			return (char) (value - 10 + 97);
		}
		return (char) (value + 48);
	}

	public static void main(String[] args) {
		System.out.println("admin: " + encrypt("admin", "MD5"));
		System.out.println("default: " + encrypt("default", "MD5"));
		System.out.println("login: " + encrypt("login", "MD5"));
	}
}
