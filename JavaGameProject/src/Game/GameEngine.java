package Game;

import Game.Classes.Door;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameEngine extends JPanel implements ActionListener {
    private  Timer timer;

    private Map mapObj;
    private Player p;
    private String Message = "";
    private Font font = new Font("Serif", Font.BOLD, 125);
    private Image EndScreen;
    private boolean isRunning = true;

    public GameEngine(){
        try {
            Sound.music(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mapObj = new Map();
        setPlayer();
        Movement movement = new Movement(this.p, this.mapObj);

        this.addKeyListener(movement);
        setFocusable(true);

        timer = new Timer(25, this);
        timer.start();

        Timer innerDoorsTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveDoors(0);
            }
        });

        innerDoorsTimer.start();

        Timer outerDoorsTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveExit(1);
            }
        });

        outerDoorsTimer.start();


    }

    public void actionPerformed (ActionEvent e){
        repaint();
    }

    public void paint (Graphics g){
        super.paint(g);
        if(isRunning) {
            ArrayList<ArrayList<String>> map = this.mapObj.getMap();


            for (int x = 0; x < map.size(); x++) {
                for (int y = 0; y < map.get(0).size(); y++) {
                    String currPosition = map.get(x).get(y);
                    Image currImg = null;
                    if (currPosition.equals("-")) {
                        currImg = mapObj.getGrass();
                        g.drawImage(currImg, y * 30, x * 30, null);
                    } else if (currPosition.equals("w")) {
                        currImg = mapObj.getWall();

                    } else if (currPosition.equals("t")) {
                        currImg = mapObj.getTeleportPoint();

                    } else {
                        currImg = mapObj.getGrass();
                        g.drawImage(currImg, y * 30, x * 30, null);
                    }


                    g.drawImage(currImg, y * 30, x * 30, null);
                }
                //draw doors
                for (Door door : mapObj.getDoors()) {
                    Image img = null;
                    if (door.isOpen) {
                        img = mapObj.getDoorOpened();
                    } else {
                        img = mapObj.getDoorClosed();
                    }

                    g.drawImage(img, door.column * 30, door.row * 30, null);
                }

                // Draw player on top
                g.drawImage(mapObj.getPlayer(), p.getCol() * 30, p.getRow() * 30, null);
                repaint();
            }
        }
        if ((p.getRow() == 0 && p.getCol() == 11) ||
                (p.getRow() == 11 && p.getCol() == 0) ||
                (p.getRow() == 11 && p.getCol() == 22) ||
                (p.getRow() == 22 && p.getCol() == 11)){

            Message = "Winner";
            isRunning = false;
            EndScreen = new ImageIcon("resources\\nakich.png").getImage();
            g.drawImage(EndScreen, 70, 30, null);

            g.setColor(Color.BLUE);
            g.setFont(font);
            g.drawString(Message, 150, 200);

            try {
                Sound.music(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Timer endGame = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message = "Looser";
                isRunning = false;
                EndScreen = new ImageIcon("resources\\SadNakov.png").getImage();
                g.drawImage(EndScreen, 70, 30, null);

                g.setColor(Color.BLUE);
                g.setFont(font);
                g.drawString(Message, 150, 200);
            }
        });
        endGame.start();
    }

    private void setPlayer() {
        ArrayList<ArrayList<String>> map = this.mapObj.getMap();
        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(0).size(); y++) {
                if (map.get(x).get(y).equals("p")) {
                    this.p = new Player(x, y);
                    return;
                }
            }
        }
    }
}
