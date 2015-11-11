package game;

public class Launcher {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine("Maze Runner", 696, 696);
        gameEngine.start();
    }
}
