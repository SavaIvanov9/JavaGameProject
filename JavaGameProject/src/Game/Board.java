package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private  Timer timer;

    private Map map;

    public Board(){
        map = new Map();

        timer = new Timer(25, this);
        timer.start();
    }

    public void actionPerformed (ActionEvent e){
        repaint();
    }

    public void paint (Graphics g){
        super.paint(g);
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++) {
                if (map.getMap(x, y).equals("g")) {
                    g.drawImage(map.getGrass(), x * 30, y * 30, null);
                }
                if (map.getMap(x,y).equals("w")){
                    g.drawImage(map.getWall(), x * 30, y * 30, null);
                }
            }
        }
    }
}
