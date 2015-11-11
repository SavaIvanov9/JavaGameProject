package Game;

import Gfx.ImageLoader;

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
            winnerEnd,
            loserEnd;

    public Map() {
        grass = ImageLoader.loadImage("resources\\grass2.jpg");
        wall = ImageLoader.loadImage("resources\\wall4.jpg");
        doorOpened = ImageLoader.loadImage("resources\\doorClosed.png");
        doorClosed = ImageLoader.loadImage("resources\\doorClosed.png");
        teleportPoint = ImageLoader.loadImage("resources\\teleport.jpg");
        finish = ImageLoader.loadImage("resources\\doorClosed.png");
        winnerEnd = ImageLoader.loadImage("resources\\nakich.png");
        loserEnd = ImageLoader.loadImage("resources\\nakovv.jpg");

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

    public Image getWinnerEnd(){
        return winnerEnd;
    }
    public Image getLoserEnd(){
        return loserEnd;
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
    public void openFileTwo() {
        try {
            m = new Scanner(new File("resources\\Map2.txt"));
        } catch (Exception e) {
            System.out.println("error loading map");
        }
    }
    public void openFileThree() {
        try {
            m = new Scanner(new File("resources\\Map3.txt"));
        } catch (Exception e) {
            System.out.println("error loading map");
        }
    }
    public void openFileFour() {
        try {
            m = new Scanner(new File("resources\\Map4.txt"));
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