package Display;

import javax.swing.*;
import java.awt.*;

public class Display {
    private int width = 800;
    private int height = 600;
    private String title;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createFrame();
    }

    public void createFrame() {
        frame = new JFrame(this.title);
        frame.setSize(this.width, this.height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        canvas.setMaximumSize(new Dimension(this.width, this.height));
        canvas.setMinimumSize(new Dimension(this.width, this.height));

        frame.add(canvas);

        frame.pack();
    }
}
