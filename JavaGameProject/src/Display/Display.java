package Display;

import Game.Board;

import javax.swing.*;


public class Display {
    private int width = 696;
    private int height = 718;
    private String title;

    public Display(String title) {
        this.title = title;
        createFrame();
    }

    public void createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(this.title);
        frame.add(new Board());
        frame.setSize(this.width, this.height);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
