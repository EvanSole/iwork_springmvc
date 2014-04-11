package com.iwork.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {

	public static boolean equals(Object obj1, Object obj2) {
		if ((obj1 == null) && (obj2 == null)) {
			return true;
		} else if ((obj1 == null) || (obj2 == null)) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}

	public static boolean isAddress(String address) {
		if (isNull(address)) {
			return false;
		}

		String[] tokens = address.split(StringPool.AT);

		if (tokens.length != 2) {
			return false;
		}

		for (String token : tokens) {
			for (char c : token.toCharArray()) {
				if (Character.isWhitespace(c)) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isAscii(char c) {
		int i = c;

		if ((i >= 32) && (i <= 126)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if c is a letter between a-z and A-Z.
	 * 
	 * @param c
	 *            a character
	 * @return true if c is a letter between a-z and A-Z
	 */
	public static boolean isChar(char c) {
		int x = c;

		if ((x >= _CHAR_BEGIN) && (x <= _CHAR_END)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if s is a string of letters that are between a-z and A-Z.
	 * 
	 * @param s
	 *            a string
	 * @return true if s is a string of letters that are between a-z and A-Z
	 */
	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		}

		for (char c : s.toCharArray()) {
			if (!isChar(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDate(int month, int day, int year) {
		return isGregorianDate(month, day, year);
	}

	/**
	 * Returns true if c is a digit between 0 and 9.
	 * 
	 * @param c
	 *            a character
	 * @return true if c is a digit between 0 and 9
	 */
	public static boolean isDigit(char c) {
		int x = c;

		if ((x >= _DIGIT_BEGIN) && (x <= _DIGIT_END)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if s is a string of letters that are between 0 and 9.
	 * 
	 * @param s
	 *            a string
	 * @return true if s is a string of letters that are between 0 and 9
	 */
	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}

		for (char c : s.toCharArray()) {
			if (!isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDomain(String domainName) {

		// See RFC-1034 (section 3), RFC-1123 (section 2.1), and RFC-952
		// (section B. Lexical grammar)

		if (isNull(domainName)) {
			return false;
		}

		if (domainName.length() > 255) {
			return false;
		}

		String[] domainNameArray = StringUtils.split(domainName,
				StringPool.PERIOD);

		for (String domainNamePart : domainNameArray) {
			char[] domainNamePartCharArray = domainNamePart.toCharArray();

			for (int i = 0; i < domainNamePartCharArray.length; i++) {
				char c = domainNamePartCharArray[i];

				if ((i == 0) && (c == CharPool.DASH)) {
					return false;
				} else if ((i == (domainNamePartCharArray.length - 1))
						&& (c == CharPool.DASH)) {

					return false;
				} else if ((!isChar(c)) && (!isDigit(c))
						&& (c != CharPool.DASH)) {

					return false;
				}
			}
		}

		return true;
	}

	public static boolean isEmailAddress(String emailAddress) {
		Boolean valid = null;

		try {
			Matcher matcher = _emailPattern.matcher(emailAddress);
			valid = matcher.matches();
		} catch (Exception e) {
			if (_logger.isWarnEnabled()) {
				_logger.warn(e.getMessage());
			}
		}

		if (valid == null) {
			return false;
		} else {
			return valid.booleanValue();
		}
	}

	public static boolean isEmailAddressSpecialChar(char c) {

		// LEP-1445

		for (int i = 0; i < _EMAIL_ADDRESS_SPECIAL_CHAR.length; i++) {
			if (c == _EMAIL_ADDRESS_SPECIAL_CHAR[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean isGregorianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {

				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isHex(String s) {
		if (isNull(s)) {
			return false;
		}

		return true;
	}

	public static boolean isHTML(String s) {
		if (isNull(s)) {
			return false;
		}

		if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
				&& ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

			return true;
		}

		return false;
	}

	public static boolean isIPAddress(String ipAddress) {
		Matcher matcher = _ipAddressPattern.matcher(ipAddress);

		return matcher.matches();
	}

	public static boolean isJulianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if ((year % 4) == 0) {
				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isLUHN(String number) {
		if (number == null) {
			return false;
		}

		number = StringUtils.reverse(number);

		int total = 0;

		for (int i = 0; i < number.length(); i++) {
			int x = 0;

			if (((i + 1) % 2) == 0) {
				x = Integer.parseInt(number.substring(i, i + 1)) * 2;

				if (x >= 10) {
					String s = String.valueOf(x);

					x = Integer.parseInt(s.substring(0, 1))
							+ Integer.parseInt(s.substring(1, 2));
				}
			} else {
				x = Integer.parseInt(number.substring(i, i + 1));
			}

			total = total + x;
		}

		if ((total % 10) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isName(String name) {
		if (isNull(name)) {
			return false;
		}

		for (char c : name.trim().toCharArray()) {
			if (((!isChar(c)) && (!Character.isWhitespace(c)))
					|| (c == CharPool.COMMA)) {

				return false;
			}
		}

		return true;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	public static boolean isNotNull(Object[] array) {
		return !isNull(array);
	}

	public static boolean isNull(Object obj) {
		if (obj instanceof Long) {
			return isNull((Long) obj);
		} else if (obj instanceof String) {
			return isNull((String) obj);
		} else if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(Long l) {
		if ((l == null) || l.longValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}

		int counter = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == CharPool.SPACE) {
				continue;
			} else if (counter > 3) {
				return false;
			}

			if (counter == 0) {
				if (c != CharPool.LOWER_CASE_N) {
					return false;
				}
			} else if (counter == 1) {
				if (c != CharPool.LOWER_CASE_U) {
					return false;
				}
			} else if ((counter == 2) || (counter == 3)) {
				if (c != CharPool.LOWER_CASE_L) {
					return false;
				}
			}

			counter++;
		}

		return true;
	}

	public static boolean isNull(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNumber(String number) {
		if (isNull(number)) {
			return false;
		}

		for (char c : number.toCharArray()) {
			if (!isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isPassword(String password) {
		if (isNull(password)) {
			return false;
		}

		if (password.length() < 4) {
			return false;
		}

		for (char c : password.toCharArray()) {
			if (!isChar(c) && !isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		return isNumber(StringUtils.extractDigits(phoneNumber));
	}

	public static boolean isVariableTerm(String s) {
		if (s.startsWith(_VARIABLE_TERM_BEGIN)
				&& s.endsWith(_VARIABLE_TERM_END)) {

			return true;
		} else {
			return false;
		}
	}

	public static boolean isWhitespace(char c) {
		int i = c;

		if ((i == 0) || Character.isWhitespace(c)) {
			return true;
		} else {
			return false;
		}
	}

	private static final int _CHAR_BEGIN = 65;

	private static final int _CHAR_END = 122;

	private static final int _DIGIT_BEGIN = 48;

	private static final int _DIGIT_END = 57;

	private static final char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[] { '.',
			'!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^',
			'_', '`', '{', '|', '}', '~' };

	private static final String _VARIABLE_TERM_BEGIN = "[$";

	private static final String _VARIABLE_TERM_END = "$]";

	private static Logger _logger = LoggerFactory.getLogger(StringUtils.class);

	private static Pattern _ipAddressPattern = Pattern.compile("\\b"
			+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
			+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
			+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
			+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])" + "\\b");

	private static Pattern _emailPattern = Pattern
			.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}" +
					"\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))" +
					"([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
}