package com.vognev.textilewebproject.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * textilewebproject_3  04/01/2022-9:19
 */

public final class  MyCookies  {

    private final static String NAME_COOKIE="textile-basket";

public static void deleteCookie(HttpServletRequest request, HttpServletResponse response){

    try{
        Cookie[] cookies = request.getCookies();

        Cookie cookie = null;

        if(cookies !=null) {

            for(Cookie c: cookies) {

                if(NAME_COOKIE.equals(c.getName())) {

                    c.setPath("/");
                    c.setValue("");
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
        }
    }catch(Exception ex){
        System.out.println("Error MyCookie deleteCookie");
        ex.printStackTrace();
    }
}


public static boolean setCookies(HttpServletResponse response,String name,String value){
    try{
        response.addCookie(new Cookie(name, value));
        return true;
    }catch(Exception ex){
        System.out.println("Error MyCookie setCookies");
        ex.printStackTrace();
        return false;
    }
}

public static String getMyCookies(HttpServletRequest request){
    try{
        Cookie[] cookies = request.getCookies();

        String cookieName = "";
        Cookie cookie=null;

        if(cookies !=null) {

            for(Cookie c: cookies) {

                if(NAME_COOKIE.equals(c.getName())) {

                    cookieName = c.getValue();
                    cookie=c;
                    break;
                }
            }
        }
//        System.out.println("getMyCookies  "+cookie.getMaxAge());
//        System.out.println("getMyCookies  "+cookie.getPath());
//        System.out.println("getMyCookies  "+cookie.getValue());
//        System.out.println("getMyCookies  "+cookie.getComment());
//        System.out.println("getMyCookies  "+cookie.getDomain());
//        System.out.println("getMyCookies  "+cookie.getSecure());
//        System.out.println("getMyCookies  "+cookie.getVersion());
       return cookieName;
    }catch(Exception ex){
        System.out.println("not cookie");
        return null;
    }
}
}
