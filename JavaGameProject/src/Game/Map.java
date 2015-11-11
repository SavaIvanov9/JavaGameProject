package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Map {
    private int len = 23;
    private Scanner m;

    private String[] Map = new String[len];

    private Image grass,
            wall,
            doorOpened,
            doorClosed,
            teleportPoint,
            finish,
            end;

    public Map() {
        try {
            grass = ImageIO.read(new File("resources\\grass2.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            wall = ImageIO.read(new File("resources\\wall4.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            doorOpened = ImageIO.read(new File("resources\\doorOpened.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            doorClosed = ImageIO.read(new File("resources\\doorClosed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            teleportPoint = ImageIO.read(new File("resources\\teleport.jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            finish = ImageIO.read(new File("resources\\doorClosed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            end = ImageIO.read(new File("resources\\nakich.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        openFile();
        readFile();
        closeFile();
    }

    public Image getGrass() {
        return grass;
    }

    public Image getWall() {
        return wall;
    }

    public Image getDoorOpened() {
        return doorOpened;
    }

    public Image getDoorClosed() {
        return doorClosed;
    }

    public Image getTeleportPoint(){
        return teleportPoint;
    }

    public Image getFinish(){
        return finish;
    }

    public Image getEnd(){
        return end;
    }

    public String getMap(int x, int y) {
        String index = Map[y].substring(x, x + 1);
        return index;
    }

    public void openFile() {
        try {
            m = new Scanner(new File("resources\\Map1.txt"));
        } catch (Exception e) {
            System.out.println("error loading map");
        }
    }

    public void readFile() {
        while (m.hasNext()) {
            for (int i = 0; i < len; i++) {
                Map[i] = m.next();
            }
        }
    }

    public void closeFile() {
        m.close();
    }
}