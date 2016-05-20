package app.util;

import java.util.List;
import java.util.ArrayList;

/**
 * The {@code Alphabet} class represents the standard English alphabet and the space character.
 * The letters are all lowercase and the space is at the end of the alphabet.
 */
public class Alphabet {
    private final static char[] al = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
    
    private Alphabet() {}

    /**
     * Gets the alphabet as represented by a {@code char} array.
     *
     * @return the standard alphabet, plus the space character.
     */
    public static char[] alphabet() { return al; }

    /**
     * Gets the alphabet as represented by a {@code List}.
     *
     * @return the standard alphabet, plus the space character.
     */
    public static List<Character> alphabetAsList() {
        List<Character> alpha = new ArrayList<Character>();
        
        for(char c : al)
            alpha.add(c);
        
        return alpha;
    }

    /**
     * Gets the index of the specified {@code char}. If the {@code target} is the space character,
     * the returned value will be 26. If the {@code target} isn't in the alphabet, the return will be
     * 25 < r < 0, where r is the returned value.
     *
     * @param target the char whose index you want to know.
     * @return the index of the target char.
     */
    public static int indexOf(char target) {
        if(target == ' ')
            return 26;

        return target - 97;
    }

    /**
     * Gets the character of the alphabet at the given index. If the index is outside of the alphabet
     * defined by this class, the {@code null} character will be returned.
     *
     * @param index the index of the character.
     * @return the {@code char} at the specified {@code index}, or the {@code null} character.
     */
    public static char charAt(int index) {
        if(index >= 0 && index <= 26)
            return al[index];

        return '\u0000';
    }

}
