package Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Map {
    private int len = 22;
    private Scanner m;

    private String[] Map = new String[len];

    private Image grass,
            wall,
            door;

    public Map() {
        ImageIcon img = new ImageIcon("resources\\grass2.jpg");
        grass = img.getImage();
        img = new ImageIcon("resources\\wall4.jpg");
        wall = img.getImage();
        img = new ImageIcon("resources\\door.closed1.png");
        door = img.getImage();

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

    public Image getDoor () { return door; }

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