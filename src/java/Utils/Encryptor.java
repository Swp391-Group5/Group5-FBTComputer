/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Admin;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Admin
 */
public class Encryptor {
    public static String toSHA1(String s) {

        // Make the password harder.
        String salt = "asdfghjqwrd@fdfdsfbbnd;pwks";
        String result = null;

        s = s + salt;

        try {
            byte[] dataBytes = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (NoSuchAlgorithmException e) {
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(Encryptor.toSHA1("Hung1234!"));
    }
            

}
