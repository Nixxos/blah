package app.gui;

import app.ciphers.Hill;
import app.util.CipherPanel;

import javax.swing.*;
import java.awt.*;

public class HillPanel extends JPanel implements CipherPanel {
    private JLabel caption;
    private JTextField keyField;

    public HillPanel() {
        super(new BorderLayout());

        caption = new JLabel("Enter the letters to be used as a key matrix.");
        caption.setHorizontalAlignment(JLabel.CENTER);

        keyField = new JTextField(40);

        add(caption);
        add(keyField, BorderLayout.SOUTH);
    }

    private String getKey() {
        String key = "";

        try {
            key = keyField.getText();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        return key;
    }

    @Override
    public String cypherize(String message) {
//        Hill hill = new Hill();
//        String key = getKey();
//        int keyLen = (int) Math.sqrt(key.length());
//
//        if(hill.check(key, keyLen)) {
//            hill.partition(message, keyLen);
//            hill.cofactor(hill.keyMatrix, keyLen);
//        }

        return null;
    }

    @Override
    public String decipherize(String encryptedMessage) {
        return null;
    }
}
