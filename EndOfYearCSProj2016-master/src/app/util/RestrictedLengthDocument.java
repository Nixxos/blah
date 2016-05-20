package app.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class RestrictedLengthDocument extends PlainDocument {
    private final int max;

    public RestrictedLengthDocument(int maxCharacters) {
        max = maxCharacters;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str != null && getLength() + str.length() <= max)
            super.insertString(offs, str, a);
    }
}
