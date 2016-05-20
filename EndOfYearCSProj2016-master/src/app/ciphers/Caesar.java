package app.ciphers;

import app.util.Alphabet;

public class Caesar {
    
    public static String encrypt(String message, int offset) {
        StringBuilder encrypted = new StringBuilder();
        
        for(int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == ' ')
                encrypted.append(' ');
            else {
                int oldChar = Alphabet.indexOf(message.charAt(i));
                int newChar = (oldChar + offset) % 26;
                encrypted.append(Alphabet.charAt(newChar));
            }
        }
        
        return encrypted.toString();
    }

    public static String decrypt(String message, int offset) {
        StringBuilder decrypted = new StringBuilder();

        for(int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == ' ')
                decrypted.append(' ');
            else {
                int oldChar = Alphabet.indexOf(message.charAt(i));
                int newChar = (offset > oldChar) ? 26 - (offset - oldChar) : oldChar - offset;
                decrypted.append(Alphabet.charAt(newChar));
            }
        }

        return decrypted.toString();
    }
}