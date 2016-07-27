/* 
 * Package Name: mn.odi.scc.util
 * File Name: PasswordGenerator.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
 */
package mn.odi.labor.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Simple password generator.
 * 
 * @author marko haapala at aktagon com
 */
public class PasswordGenerator {
	public static final char[] HEX_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * To simplify removed '(', ')', '"', '=', '/', '%', '&', '#' couldnt login
	 * when using those chars;
	 */
	public static final char[] SECURE_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '!', '@', '$' };

	/**
	 * Generates an eight characters long password consisting of hexadecimal
	 * characters.
	 * 
	 * @return the generated password
	 */
	public static String generate() {
		return generate(HEX_CHARS, 8);
	}

	/**
	 * Generates a password consisting of hexadecimal characters.
	 * 
	 * @param length
	 *            of the password
	 * @return the generated password
	 */
	public static String generate(final int length) {
		return generate(HEX_CHARS, length);
	}

	/**
	 * Generates a password according to the given parameters.
	 * 
	 * @param characters
	 *            that make up the password
	 * @param length
	 *            of the password
	 * @return the generated password
	 */
	public static String generate(final char[] characters, final int length) {

		return RandomStringUtils.random(length, characters);
	}
}