package com.augenstern.controller.interceptor.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookieUtil {

    public static String GetCookie(HttpServletRequest req, String verbal) {
        try {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if (name.equals(verbal)) {
                    return cookie.getValue();
                }
            }
        } catch (Exception ignored) {
            return null;
        }
        return null;
    }
}
