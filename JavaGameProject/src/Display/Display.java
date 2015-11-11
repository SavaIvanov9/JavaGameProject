package Display;

import Game.GameEngine;

import javax.swing.*;


public class Display {
    private int width = 705;
    private int height = 728;
    private String title;

    public Display(String title) {
        this.title = title;
        createFrame();
    }

    public void createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(this.title);
        frame.add(new GameEngine());
        frame.setSize(this.width, this.height);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
