package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEngine extends JPanel implements ActionListener {
    private Timer timer;

    private int mapNum = 1;
    private int steps = 0;

    private Map map;
    private Player p;

    private boolean isRunning = false;

    private String Message = "";
    private String Steps = "";
    private Font font = new Font("Serif", Font.CENTER_BASELINE, 100);


    public GameEngine() {
        Sound.music(true);
        map = new Map();
        p = new Player();

        addKeyListener(new Moves());
        setFocusable(true);

        timer = new Timer(25, this);
        timer.start();

    }

    public void actionPerformed(ActionEvent e) {

        if (map.getMap(p.getTileX(), p.getTileY()).equals("f")) {
            mapNum++;
            if (mapNum == 2) {
                map.openFileTwo();
                map.readFile();
                map.closeFile();
                p.move(0, 21);
            } else if (mapNum == 3) {
                map.openFileThree();
                map.readFile();
                map.closeFile();
                p.move(-21, 0);
            } else if (mapNum == 4) {
                map.openFileFour();
                map.readFile();
                map.closeFile();
                p.move(0, -21);
            } else if (mapNum == 5) {
                isRunning = true;
                String stepss = Integer.toString(steps);
                Message = "Winner!";
                Steps = "Steps: " + stepss;
                Sound.music(false);
            }
        }
        repaint();
    }

    public void paint(Graphics g) {

        super.paint(g);
        if (!isRunning) {


            for (int y = 0; y < 23; y++) {
                for (int x = 0; x < 23; x++) {
                    if (map.getMap(x, y).equals("f")) {
                        g.drawImage(map.getFinish(), x * 30, y * 30, null);
                    }
                    if (map.getMap(x, y).equals("-")) {
                        g.drawImage(map.getGrass(), x * 30, y * 30, null);
                    }
                    if (map.getMap(x, y).equals("w")) {
                        g.drawImage(map.getWall(), x * 30, y * 30, null);
                    }
                    if (map.getMap(x, y).equals("o")) {
                        g.drawImage(map.getDoorOpened(), x * 30, y * 30, null);
                    }
                    if (map.getMap(x, y).equals("c")) {
                        g.drawImage(map.getDoorClosed(), x * 30, y * 30, null);
                    }
                    if (map.getMap(x, y).equals("t")) {
                        g.drawImage(map.getTeleportPoint(), x * 30, y * 30, null);
                    }
                }
                g.drawImage(p.getPlayer(), p.getTileX() * 30, p.getTileY() * 30, null);
            }
        }
        if (isRunning) {
            if (steps <= 80) {
                g.drawImage(map.getWinnerEnd(), 70, 30, null);
                g.setColor(Color.BLUE);
                g.setFont(font);
                g.drawString(Message, 150, 150);
                g.drawString(Steps, 150, 250);
            } else {
                g.drawImage(map.getLoserEnd(), 70, 30, null);
                g.setColor(Color.BLUE);
                g.setFont(font);
                Message = "Loser!!!";
                g.drawString(Message, 150, 150);
                g.drawString(Steps, 150, 250);
            }

        }
    }

    public class Moves extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_UP) {
                if ((!map.getMap(p.getTileX(), p.getTileY() - 1).equals("w")) &&
                        (!map.getMap(p.getTileX(), p.getTileY() - 1).equals("c"))) {
                    p.move(0, -1);
                    steps++;
                }
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                if ((!map.getMap(p.getTileX(), p.getTileY() + 1).equals("w")) &&
                        (!map.getMap(p.getTileX(), p.getTileY() + 1).equals("c"))) {
                    p.move(0, 1);
                    steps++;
                }
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                if ((!map.getMap(p.getTileX() - 1, p.getTileY()).equals("w")) &&
                        (!map.getMap(p.getTileX() - 1, p.getTileY()).equals("c"))) {
                    p.move(-1, 0);
                    steps++;
                }
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                if ((!map.getMap(p.getTileX() + 1, p.getTileY()).equals("w")) &&
                        (!map.getMap(p.getTileX() + 1, p.getTileY()).equals("c"))) {
                    p.move(1, 0);
                    steps++;
                }
            }
            if (p.getTileX() == 1 && p.getTileY() == 1) {
                p.move(20, 20);
            } else if (p.getTileX() == 21 && p.getTileY() == 21) {
                p.move(-20, -20);
            } else if (p.getTileX() == 1 && p.getTileY() == 21) {
                p.move(20, -20);
            } else if (p.getTileX() == 21 && p.getTileY() == 1) {
                p.move(-20, 20);
            }
        }
    }
}


