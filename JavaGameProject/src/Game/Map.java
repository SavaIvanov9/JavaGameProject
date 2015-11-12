package Game;

import Game.Classes.Door;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Map {
    private Scanner m;

    private ArrayList<ArrayList<String>> Map = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();

    private Image grass,
            wall,
            doorOpened,
            doorClosed,
            teleportPointG,
            teleportPointR,
            player;


    public Map() {
        ImageIcon img = new ImageIcon("resources\\grass2.jpg");
        grass = img.getImage();
        img = new ImageIcon("resources\\wall4.jpg");
        wall = img.getImage();
        doorOpened = new ImageIcon("resources\\doorOpened.png").getImage();
        doorClosed = new ImageIcon("resources\\doorClosed.png").getImage();
        teleportPointR = new ImageIcon("resources\\r1.png").getImage();
        teleportPointG = new ImageIcon("resources\\g1.png").getImage();

        img = new ImageIcon("resources\\player1.png");
        player = img.getImage();


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

    public Image getTeleportPointR() {
        return teleportPointR;
    }
    public Image getTeleportPointG() {
        return teleportPointG;
    }


    public Image getPlayer () { return player; }

    public ArrayList<ArrayList<String>> getMap() {
        //String index = Map[y].substring(x, x + 1);
        return this.Map;
    }

    public void moveDoors(int groupId){
        for (Door door : doors ){
            if (door.groupID == groupId){
                door.isOpen = !door.isOpen;
            }
        }
    }

    public ArrayList<Door> getDoors(){
        return this.doors;
    }

    public boolean isAvailable(int row, int col){
        // Check if row or col are out of the matrix
        if(row < 0 || col < 0 || row > this.Map.size() || col > this.Map.get(0).size()){
            return false;
        }

        String position = this.Map.get(row).get(col);

        for (Door door : doors){
            if (door.row == row && door.column == col && !door.isOpen){
                return false;
            }
        }

        switch(position){
            case "w":
                return false;
            default:
                return true;
        }
    }

    public void openFile() {
        try {
            m = new Scanner(new File("resources\\Map2.txt"));
        } catch (Exception e) {
            System.out.println("error loading map");
        }
    }

    public void readFile() {
        int currentRow = 0;
        int currentGroup = 0;
        while (m.hasNext()) {
            String rowStr = m.next();
            ArrayList<String> rowItems = new ArrayList<>();
            for (int col = 0; col < rowStr.length(); col++){
                String currentSymbol = Character.toString(rowStr.charAt(col));
                if (isNumeric(currentSymbol)) {
                    int number = Integer.parseInt(currentSymbol);
                    boolean isEven = number % 2 == 0;

                    if (isEven){
                        doors.add(createDoor(true, currentRow, col, currentGroup, number));
                    } else {
                        doors.add(createDoor(false, currentRow, col, currentGroup, number));
                    }
                }

                rowItems.add(currentSymbol);
            }

            this.Map.add(rowItems);
            currentRow++;
        }

        this.orderDoors();
    }

    private void orderDoors() {
        doors.sort(new Comparator<Door>() {
            @Override
            public int compare(Door o1, Door o2) {
                return Integer.compare(o1.doorNum, o2.doorNum);
            }
        });

        int currentGroup = 0;
        int largestNum = 2;
        for (Door door : doors){
            if (door.doorNum > largestNum){
                largestNum += 2;
                currentGroup++;
            }

            door.groupID = currentGroup;
        }
    }

    private Door createDoor(boolean isOpen, int row, int col, int groupId, int doorNum){
        Door newDoor = new Door();
        newDoor.row = row;
        newDoor.column = col;
        newDoor.isOpen = isOpen;
        newDoor.groupID = groupId;
        newDoor.doorNum = doorNum;
        return newDoor;
    }

    public void closeFile() {
        m.close();
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void moveExit(int groupId) {
        ArrayList<Door> exitDoors = new ArrayList<>();
        for (Door door : this.doors){
            if (door.groupID == groupId){
                door.isOpen = false;
                exitDoors.add(door);
            }
        }

        int maxIndex = exitDoors.size();
        Random rand = new Random();
        int index = rand.nextInt(maxIndex);
        exitDoors.get(index).isOpen = true;
    }
}