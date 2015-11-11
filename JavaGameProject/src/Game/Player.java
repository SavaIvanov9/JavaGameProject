package Game;

import javax.swing.*;
import java.awt.*;

public class Player {
    private int row, col;
    public Player (int row ,int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void movePlayer(int row, int col){
        this.row = row;
        this.col = col;
    }
}