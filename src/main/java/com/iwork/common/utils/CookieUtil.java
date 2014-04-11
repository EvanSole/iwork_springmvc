package com.iwork.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookieUtil {

	public static String get(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];

			String cookieName = GetterUtil.getString(cookie.getName());

			if (cookieName.equalsIgnoreCase(name)) {
				return cookie.getValue();
			}
		}

		return null;
	}

}