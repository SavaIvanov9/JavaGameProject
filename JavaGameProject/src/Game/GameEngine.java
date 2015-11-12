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
    private String Message2 = "";

    private Font font = new Font("Serif", Font.BOLD, 125);
    private Image EndScreen;
    private Image EndScreen2;

    private boolean isRunning = true;
    private boolean youLoose = false;

    private int Counter = 0;

    private Image red1;
    private Image red2;
    private Image red3;
    private Image green1;
    private Image green2;
    private Image green3;

    public GameEngine(){
        try {
            Sound.music(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        red1 = new ImageIcon("resources\\r1.png").getImage();
        red2 = new ImageIcon("resources\\r2.png").getImage();
        red3 = new ImageIcon("resources\\r3.png").getImage();

        green1 = new ImageIcon("resources\\g1.png").getImage();
        green2 = new ImageIcon("resources\\g2.png").getImage();
        green3 = new ImageIcon("resources\\g3.png").getImage();

        mapObj = new Map();
        setPlayer();
        Movement movement = new Movement(this.p, this.mapObj);

        this.addKeyListener(movement);
        setFocusable(true);

        timer = new Timer(25, this);
        timer.start();

        Timer innerDoorsTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveDoors(0);
            }
        });

        innerDoorsTimer.start();

        Timer innerDoorsTimer2 = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveDoors(1);
            }
        });

        innerDoorsTimer2.start();

        Timer innerDoorsTimer3 = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveDoors(2);
            }
        });

        innerDoorsTimer3.start();

        Timer outerDoorsTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mapObj.moveExit(3);
            }
        });

        outerDoorsTimer.start();

        Timer tick = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Counter++;
            }
        });
        tick.start();

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
                    }
                    else if (currPosition.equals("w")) {
                        currImg = mapObj.getWall();
                    }
                    else if (currPosition.equals("r")) {
                        currImg = mapObj.getTeleportPointR();
                    }
                    else if (currPosition.equals("g")) {
                        currImg = mapObj.getTeleportPointG();
                    }
                    else {
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
                if(Counter % 4 == 0){
                    g.drawImage(this.red1, 1 * 30, 1 * 30, null);
                    g.drawImage(this.red1, 21 * 30, 21 * 30, null);
                    g.drawImage(this.green1, 1 * 30, 21 * 30, null);
                    g.drawImage(this.green1, 21 * 30, 1 * 30, null);

                } else if (Counter % 3 ==0){
                    g.drawImage(this.red2, 1 * 30, 1 * 30, null);
                    g.drawImage(this.red2, 21 * 30, 21 * 30, null);
                    g.drawImage(this.green2, 1 * 30, 21 * 30, null);
                    g.drawImage(this.green2, 21 * 30, 1 * 30, null);
                } else if ((Counter % 2 == 0) && (Counter % 4 != 0)) {
                    g.drawImage(this.red3, 1 * 30, 1 * 30, null);
                    g.drawImage(this.red3, 21 * 30, 21 * 30, null);
                    g.drawImage(this.green3, 1 * 30, 21 * 30, null);
                    g.drawImage(this.green3, 21 * 30, 1 * 30, null);}
//                }else if ((Counter % 2 == 0) && (Counter % 4 != 0)){
//                    g.drawImage(this.red2, 1 * 30, 1 * 30, null);
//                    g.drawImage(this.red2, 21 * 30, 21 * 30, null);
//                    g.drawImage(this.green2, 1 * 30, 21 * 30, null);
//                    g.drawImage(this.green2, 21 * 30, 1 * 30, null);
//                }else{
//                    g.drawImage(this.red1, 1 * 30, 1 * 30, null);
//                    g.drawImage(this.red1, 21 * 30, 21 * 30, null);
//                    g.drawImage(this.green1, 1 * 30, 21 * 30, null);
//                    g.drawImage(this.green1, 21 * 30, 1 * 30, null);
//                }

                // Draw player on top
                g.drawImage(mapObj.getPlayer(), p.getCol() * 30, p.getRow() * 30, null);
                repaint();
            }
        }
        if ((p.getRow() == 0 && p.getCol() == 11) ||
                (p.getRow() == 11 && p.getCol() == 0) ||
                (p.getRow() == 11 && p.getCol() == 22) ||
                (p.getRow() == 22 && p.getCol() == 11)){
            timer.stop();
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
        Timer endGame = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                youLoose = true;
                isRunning = false;

            }
        });
        endGame.start();
        if (isRunning == false && youLoose) {
            Message2 = "Looser";
            EndScreen2 = new ImageIcon("resources\\SadNakov.png").getImage();
            g.drawImage(EndScreen2, 140, 350, null);

            g.setColor(Color.BLUE);
            g.setFont(font);
            g.drawString(Message2, 150, 200);
        }
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
