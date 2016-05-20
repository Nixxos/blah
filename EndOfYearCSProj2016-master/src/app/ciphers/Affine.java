package app.ciphers;

import app.util.Alphabet;

public class Affine {

    public static String encrypt(String messagein, int offset, int multiplicity) {
        StringBuilder encrypted = new StringBuilder();
        String message = messagein.toLowerCase();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                encrypted.append(' ');
            else {
                int oldChar = Alphabet.indexOf(message.charAt(i));
                int newChar = (multiplicity * oldChar + offset) % 26;
                encrypted.append(Alphabet.charAt(newChar));
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String messagein, int offset, int multiplicity) {
        StringBuilder decrypted = new StringBuilder();
        String message = messagein.toLowerCase();
        int mult = 26 - multiplicity;

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                decrypted.append(' ');
            else {
                int oldChar = Alphabet.indexOf(message.charAt(i));
                int middleman = mult * (oldChar - offset);

                while (middleman < 0)
                    middleman = middleman + 26;

                int newChar = middleman % 26;
                decrypted.append(Alphabet.charAt(newChar));
            }
        }
        return decrypted.toString();
    }
}
