package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Player {
    private int tileX,tileY;
    private Image player;
    public Player (){
        try{
            player = ImageIO.read(new File("resources\\Player.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

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