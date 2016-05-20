package app.gui;

import app.util.CipherPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

import javax.swing.*;

public class Display extends JPanel {
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
	private JComboBox<String> selectionBox;
	private String[] ciphers = {"Caesar", "Hill", "Foursquare"};
	private JTextArea messageBox;
	private JButton cipherButton;
	private JButton decipherButton;
	private JPanel holder;
	private CardLayout layout;
	private CaesarPanel caesar;
	private FoursquarePanel f2;
	private HillPanel hill;

	public Display() {
		selectionBox = new JComboBox<String>(ciphers);
		selectionBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout) holder.getLayout();
				cl.show(holder, (String) e.getItem());
				resize();
			}
		});

        caesar = new CaesarPanel();
		f2 = new FoursquarePanel();
		hill = new HillPanel();

		layout = new CardLayout();
		holder = new JPanel(layout);
		holder.add(caesar, "Caesar");
		holder.add(f2, "Foursquare");
		holder.add(hill, "Hill");

		messageBox = new JTextArea(8, 40);
        messageBox.setLineWrap(true);
        JScrollPane messageHolder = new JScrollPane(messageBox);

		cipherButton = new JButton("Cipherize!");
		cipherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CipherPanel panel = (CipherPanel) holder.getComponent(0);
				putout(panel.cypherize(getMessage()), true);
			}
		});

		decipherButton = new JButton("Decipherize!");
		decipherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CipherPanel panel = (CipherPanel) holder.getComponent(0);
				putout(panel.decipherize(getMessage()), false);
			}
		});

		add(selectionBox, BorderLayout.NORTH);
		add(holder);
		add(messageHolder, BorderLayout.SOUTH);
		add(cipherButton, BorderLayout.SOUTH);
		add(decipherButton, BorderLayout.SOUTH);
	}

	private String getMessage() {
		String message = "";

		try {
			message = messageBox.getText();
		} catch(NullPointerException e) {
			e.printStackTrace();
		}

		return message;
	}

	public void resize() {
		JFrame root = (JFrame) SwingUtilities.getWindowAncestor(this);
		root.setVisible(true);
		holder.setPreferredSize(new Dimension(500, (int) getCurrentCard().getPreferredSize().getHeight()));
		setPreferredSize(new Dimension(WIDTH, getComponentsHeight()));
		root.pack();
		root.setLocationRelativeTo(null);
	}

	private void putout(String output, boolean cipherized) {
		try( PrintWriter outputStream = new PrintWriter(cipherized ? "Cipher Text.txt" : "Deciphered Text.txt") ) {
			outputStream.println(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getComponentsHeight() {
		return (int) (selectionBox.getHeight() + messageBox.getHeight() + cipherButton.getHeight()
                        + holder.getPreferredSize().getHeight() + 28);
	}

	private Component getCurrentCard() {
		for(Component c : holder.getComponents())
			if(c.isVisible())
				return c;

		return null;
	}
}
