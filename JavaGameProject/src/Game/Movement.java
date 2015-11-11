package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Movement extends KeyAdapter {
    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String RIGHT = "RIGHT";
    private final String LEFT = "LEFT";

    private Player p;
    private Map map;

    public Movement(Player player, Map map){
        this.p = player;
        this.map = map;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int currPlayerRow = this.p.getRow();
        int currPLayerCol = this.p.getCol();
        if (keyCode == KeyEvent.VK_UP) {
            boolean canMove = this.canMove(UP);
            if(canMove){
                this.p.movePlayer(currPlayerRow - 1, currPLayerCol);
            }
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            boolean canMove = this.canMove(DOWN);
            if(canMove){
                this.p.movePlayer(currPlayerRow + 1, currPLayerCol);
            }
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            boolean canMove = this.canMove(LEFT);
            if(canMove){
                this.p.movePlayer(currPlayerRow, currPLayerCol - 1);
            }
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            boolean canMove = this.canMove(RIGHT);
            if(canMove){
                this.p.movePlayer(currPlayerRow, currPLayerCol + 1);
            }
        }
    }

    public boolean canMove(String direction){
        int currPlayerRow = this.p.getRow();
        int currPlayerCol = this.p.getCol();

        int rowToCheck = currPlayerRow;
        int colToCheck = currPlayerCol;

        switch(direction){
            case UP:
                rowToCheck--;
                break;
            case DOWN:
                rowToCheck++;
                break;
            case LEFT:
                colToCheck--;
                break;
            case RIGHT:
                colToCheck++;
                break;
        }

        return this.map.isAvailable(rowToCheck, colToCheck);
    }
}