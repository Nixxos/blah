package app.gui;

import app.ciphers.Caesar;
import app.util.CipherPanel;
import app.util.RestrictedLengthDocument;

import javax.swing.*;
import java.awt.*;

public class CaesarPanel extends JPanel implements CipherPanel {
	private JLabel caption;
	private JTextField textBox;
	
	public CaesarPanel() {
        super(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(500, 70));

		caption = new JLabel("Enter the shift number.");
		caption.setPreferredSize(new Dimension(500, 30));
        caption.setHorizontalAlignment(JLabel.CENTER);

		textBox = new JTextField(2);
		textBox.setDocument(new RestrictedLengthDocument(2));
        textBox.setPreferredSize(new Dimension(500, 30));

		add(caption);
        add(textBox);
	}

	private int getOffset() {
		int offs = 0;

		try {
			offs = Integer.parseInt(textBox.getText());
		} catch(NullPointerException e) {
		    e.printStackTrace();
		}

		return offs;
	}

	@Override
	public String cypherize(String message) {
		return Caesar.encrypt(message, getOffset());
	}

	@Override
	public String decipherize(String encryptedMessage) {
		return Caesar.decrypt(encryptedMessage, getOffset());
	}
}
