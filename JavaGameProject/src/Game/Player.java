package Game;

import javax.swing.*;
import java.awt.*;

public class Player {
    private int tileX,tileY;
    private Image player;
    public Player (){
        ImageIcon img = new ImageIcon("resources\\Player1.png");
        player = img.getImage();

        tileX = 11;
        tileY = 11;
    }

    public Image getPlayer (){
        return player;
    }
    public int getTileX (){
        return tileX;
    }
    public int getTileY (){
        return tileY;
    }

    public void move(int tx, int ty){
        tileX += tx;
        tileY += ty;
    }
}