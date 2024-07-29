/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hungp
 */
public class PasswordCreator {

    public static String createRandomPassword() {
        // Define character sets
        String numbers = "0123456789";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        // Create a SecureRandom instance
        SecureRandom random = new SecureRandom();

        // Ensure the password includes at least one of each required character
        StringBuilder password = new StringBuilder(8);
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Fill the remaining characters randomly from all character sets
        String allCharacters = numbers + upperCaseLetters + lowerCaseLetters + specialCharacters;
        for (int i = 3; i < 8; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        
        // Convert the password to a list to shuffle it
        List<Character> passwordList = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            passwordList.add(c);
        }
        Collections.shuffle(passwordList);

        // Convert the list back to a string
        StringBuilder shuffledPassword = new StringBuilder(8);
        for (char c : passwordList) {
            shuffledPassword.append(c);
        }

        return shuffledPassword.toString();
    }

    public static void main(String[] args) {
        // Test the password generator
        String password = createRandomPassword();
        System.out.println("Generated Password: " + password);
    }
}
