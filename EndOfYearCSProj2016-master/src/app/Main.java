package app;

import app.gui.Display;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
		JFrame frame = new JFrame("Cyphers 'n Stuff");
		Display display = new Display();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(display);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		display.resize();
    }
}
