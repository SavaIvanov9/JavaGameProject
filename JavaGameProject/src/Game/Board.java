package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
    private Timer timer;

    private Map map;
    private Player p;

    public Board() {
        map = new Map();
        p = new Player();

        addKeyListener(new Moves());
        setFocusable(true);

        timer = new Timer(25, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++) {
                if (map.getMap(x, y).equals("g")) {
                    g.drawImage(map.getGrass(), x * 30, y * 30, null);
                }
                if (map.getMap(x, y).equals("w")) {
                    g.drawImage(map.getWall(), x * 30, y * 30, null);
                }
                if (map.getMap(x, y).equals("d")) {
                    g.drawImage(map.getDoor(), x * 30, y * 30, null);
                }
            }
        }
        g.drawImage(p.getPlayer(), p.getX(), p.getY(), null);
        repaint();
    }

    public class Moves extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_UP) {
                p.move(0, -30, 0, -1);
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                p.move(0, 30, 0, 1);
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                p.move(-30, 0, -1, 0);
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                p.move(30, 0, 1, 0);
            }
        }

        public void keyRelased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }
    }
}
