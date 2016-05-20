package app.ciphers;

import java.util.ArrayList;

public class Dvorak {

    private final static char[] dvorak = "`1234567890[]',.pyfgcrl/=aoeuidhtns-;qjkxbmwvz~!@#$%^&*(){}<>PYFGCRL?+|AOEUIDHTNS_:QJKXBMWVZ ".toCharArray();
    private final static char[] qwerty = "`1234567890-=qwertyuiop[]asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>? ".toCharArray();


    public static String QWERTYtoDVORAK(String message) {
        StringBuilder encrypted = new StringBuilder();

        ArrayList<Character> QWERTY = new ArrayList<Character>();
        ArrayList<Character> DVORAK = new ArrayList<Character>();

        for (int x = 0; x < qwerty.length; x++)
            QWERTY.add(qwerty[x]);
        for (int x = 0; x < dvorak.length; x++)
            DVORAK.add(dvorak[x]);

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                encrypted.append(' ');
            else {
                int oldChar = QWERTY.indexOf(message.charAt(i));
                encrypted.append(DVORAK.get(oldChar));
            }
        }

        return encrypted.toString();
    }

    public static String DVORAKtoQWERTY(String message) {
        StringBuilder decrypted = new StringBuilder();

        ArrayList<Character> Qwerty = new ArrayList<Character>();
        ArrayList<Character> Dvorak = new ArrayList<Character>();

        for (int x = 0; x < qwerty.length; x++)
            Qwerty.add(qwerty[x]);
        for (int x = 0; x < dvorak.length; x++)
            Dvorak.add(dvorak[x]);


        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                decrypted.append(' ');
            else {
                int oldChar = Dvorak.indexOf(message.charAt(i));
                decrypted.append(Qwerty.get(oldChar));
            }
        }

        return decrypted.toString();
    }
}
