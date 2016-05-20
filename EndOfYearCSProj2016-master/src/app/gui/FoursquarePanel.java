package app.gui;

import app.ciphers.FourSquare;
import app.util.CipherPanel;

import javax.swing.*;
import java.awt.*;

public class FoursquarePanel extends JPanel implements CipherPanel {
    private JLabel caption;
    private JTextField input1, input2;

    public FoursquarePanel() {
        super(new BorderLayout(0, 5));
        setPreferredSize(new Dimension(500, 76));

        caption = new JLabel("Enter the 2 encryption phrases.");
        caption.setHorizontalAlignment(JLabel.CENTER);

        input1 = new JTextField(40);
        input2 = new JTextField(40);

        add(caption, BorderLayout.NORTH);
        add(input1);
        add(input2, BorderLayout.SOUTH);
    }

    private String getInput1() {
        String input = "";

        try {
            input = input1.getText();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        return input;
    }

    private String getInput2() {
        String input = "";

        try {
            input = input2.getText();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        return input;
    }

    @Override
    public String cypherize(String message) {
        return FourSquare.encrypt(message, getInput1(), getInput2());
    }

    @Override
    public String decipherize(String encryptedMessage) {
        return FourSquare.decrypt();
    }
}
