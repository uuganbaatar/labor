package mn.odi.labor.util;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mn.odi.labor.dao.SccDAO;

public class StringUtil {
	// ~ Static fields/initializers
	// =============================================

	private final static Log log = LogFactory.getLog(StringUtil.class);

	// ~ Methods
	// ================================================================

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * 
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static int getClassIndex(char chr) {
		int ret = 0;
		int asci = chr;

		switch (asci) {
		case 1072:
			ret = 1; // ASCII OF а
			break;
		case 1073:
			ret = 2; // ASCII OF б
			break;
		case 1074:
			ret = 3; // ASCII OF в
			break;
		case 1075:
			ret = 4; // ASCII OF г
			break;
		case 1076:
			ret = 5; // ASCII OF д
			break;
		case 1077:
			ret = 6; // ASCII OF е
			break;
		case 1105:
			ret = 7; // ASCII OF ё
			break;
		case 1078:
			ret = 8; // ASCII OF ж
			break;
		case 1079:
			ret = 9; // ASCII OF з
			break;
		case 1080:
			ret = 10; // ASCII OF и
			break;
		case 1081:
			ret = 11; // ASCII OF й
			break;
		case 1082:
			ret = 12; // ASCII OF к
			break;
		case 1083:
			ret = 13; // ASCII OF л
			break;
		case 1084:
			ret = 14; // ASCII OF м
			break;
		case 1085:
			ret = 15; // ASCII OF н
			break;
		case 49:
			ret = 21; // ASCII OF 1
			break;
		case 50:
			ret = 22; // ASCII OF 2
			break;
		case 51:
			ret = 23; // ASCII OF 3
			break;
		case 52:
			ret = 24; // ASCII OF 4
			break;
		case 53:
			ret = 25; // ASCII OF 5
			break;
		case 54:
			ret = 26; // ASCII OF 6
			break;
		case 55:
			ret = 27; // ASCII OF 7
			break;
		case 56:
			ret = 28; // ASCII OF 8
			break;
		case 57:
			ret = 29; // ASCII OF 9
			break;
		}

		return ret;
	}

	/* Jun 16, 2010 S.Khishigbaatar Begin */
	public static String getHTMLFormated(String str) {

		str = str.replaceAll("\n", "<br/>");

		return str;
	}

	/* Jun 16, 2010 S.Khishigbaatar End */

	public static String toUpperCase(String str) {
		if (str != null && str.length() > 0) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}

		return str;
	}

	public static boolean isNotNullOrEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	public static Object convertStringToClass(String str, SccDAO dao) {

		Pattern pattern = Pattern.compile("^*=[0-9]{1,},");
		Matcher matcher = pattern.matcher(str);

		matcher.find();
		String match = matcher.group();

		String id = (String) match.subSequence(1, match.length() - 1);

		try {

			Object obj = dao.getObject(Class.forName(str.split("@")[0])
					.newInstance().getClass(), Long.valueOf(id));

			return obj;

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
