package basecode;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The Ghost class implements the Character interface and overrides all of its
 * abstract methods. It controls the movement and animation of the ghosts.
 *
 */
public class Ghost implements Character {

    private int posX;
    private int posY;
    private Image ghost;
    private Maze maze = new Maze();
    private Random random = new Random();
    // The number represents the direction of the ghost.
    private int direction;
    private int velocity;
    private int width = PacmanGame.getWindowWidth();
    private int height = PacmanGame.getWindowHeight();
    private int time = random.nextInt(80 - 40) + 40;

    /**
     * The constructor assigns random position to the ghost and assigns the
     * image to it.
     * 
     * @param pic the number representing the picture of the ghost.
     */
    public Ghost(int pic) {
        do {
            posX = random.nextInt(345 - 100) + 100;
            posY = random.nextInt(345 - 100) + 100;
        } while (maze.checkWalls(posX, posY));

        ghost = new ImageIcon("src/images/ghost" + pic + ".png").getImage();
        direction = random.nextInt(4);
        do {
            velocity = random.nextInt(6 - 1) + 1;
        } while (24 % velocity != 0);
    }

    @Override
    public void moveLeft() {
        if (posX > 10 && !maze.checkWalls(posX - velocity, posY)
                && Board.counter % time != 0) {
            posX = posX - velocity;
        } else {
            do {
                direction = random.nextInt(4);
            } while (direction == 0);
        }
    }

    @Override
    public void moveRight() {
        if (posX < width - 50 && !maze.checkWalls(posX + velocity, posY)
                && Board.counter % time != 0) {
            posX = posX + velocity;
        } else {
            do {
                direction = random.nextInt(4);
            } while (direction == 1);
        }
    }

    @Override
    public void moveUp() {
        if (posY > 10 && !maze.checkWalls(posX, posY - velocity)
                && Board.counter % time != 0) {
            posY = posY - velocity;
        } else {
            do {
                direction = random.nextInt(4);
            } while (direction == 2);
        }
    }

    @Override
    public void moveDown() {
        if (posY < height - 130 && !maze.checkWalls(posX, posY + velocity)
                && Board.counter % time != 0) {
            posY = posY + velocity;
        } else {
            do {
                direction = random.nextInt(4);
            } while (direction == 3);
        }
    }

    /**
     * This method moves the ghost randomly in a direction.
     */
    public void moveRandom() {
        if (direction == 0) {
            moveLeft();
        } else if (direction == 1) {
            moveRight();
        } else if (direction == 2) {
            moveUp();
        } else if (direction == 3) {
            moveDown();
        }
    }

    @Override
    public Image getImage() {
        return ghost;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosX(int x) {
        posX = x;
    }

    @Override
    public void setPosY(int y) {
        posY = y;
    }

    @Override

    public int getDirection() {
        return direction;
    }

    @Override
    public boolean checkCollide(int x, int y) {
        return false;
    }

}
