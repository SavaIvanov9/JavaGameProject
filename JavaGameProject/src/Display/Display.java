package Display;

import Game.Board;

import javax.swing.*;
import java.awt.*;

public class Display {
    private int width = 666;
    private int height = 688;
    private String title;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title) {
        this.title = title;
        createFrame();
    }

    public void createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(this.title);
        frame.add(new Board());
        frame.setSize(this.width, this.height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
