/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author DELL DN
 */
public class Encryption {

    public static String toSHA1(String s) {

        // Make the password harder.
        String salt = "asdfghjqwrd@fdfdsfbbnd;pwks";
        String result = null;

        s = s + salt;

        try {
            byte[] dataBytes = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
        }
        return result;
    }
}
