package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

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
        g.setColor(Color.BLUE);
        g.fillRect(45,60,32,32);

    }
}
