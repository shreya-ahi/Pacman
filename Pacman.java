package basecode;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class implements the Character interface and overrides all of its
 * abstract methods.
 *
 */
public class Pacman implements Character {

    private int posX;
    private int posY;
    private Image pacman;
    private int velocity = 12; // set it 8,12,24
    private Maze maze = new Maze();
    private int width = PacmanGame.getWindowWidth();
    private int height = PacmanGame.getWindowHeight();
    // 0 for left, 1 for right, 2 for up, 3 for down
    private int direction;

    /**
     * The constructor assigns value to the position of the pacman and the image
     * of it.
     * 
     * @param x
     * @param y
     */
    public Pacman(int x, int y) {
        posX = x;
        posY = y;
        pacman = new ImageIcon("src/images/pacman_right_0.png").getImage();
    }

    @Override
    public void moveRight() {
        if (direction != 0) {
            pacman = new ImageIcon("src/images/pacman_right_0.png").getImage();
        }
        if (posX < width - 50 && !maze.checkWalls(posX + velocity, posY)) {
            posX = posX + velocity;
        }
        direction = 0;
    }

    @Override
    public void moveLeft() {
        if (direction != 1) {
            pacman = new ImageIcon("src/images/pacman_left_0.png").getImage();
        }
        if (posX > 10 && !maze.checkWalls(posX - velocity, posY)) {
            posX = posX - velocity;
        }
        direction = 1;
    }

    @Override
    public void moveUp() {
        if (direction != 2) {
            pacman = new ImageIcon("src/images/pacman_up_0.png").getImage();
        }
        if (posY > 10 && !maze.checkWalls(posX, posY - velocity)) {
            posY = posY - velocity;
        }
        direction = 2;
    }

    @Override
    public void moveDown() {
        if (direction != 3) {
            pacman = new ImageIcon("src/images/pacman_down_0.png").getImage();
        }
        if (posY < height - 130 && !maze.checkWalls(posX, posY + velocity)) {
            posY = posY + velocity;
        }
        direction = 3;
    }

    @Override
    public Image getImage() {
        return pacman;
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
    public int getDirection() {
        return direction;
    }

    @Override
    public void setPosX(int x) {
        posX = x;
    }

    @Override
    public void setPosY(int y) {
        posY = y;
    }

    /**
     * check if there is any collide between the pacman and all of the ghosts.
     * 
     * @param x x-position of character 2
     * @param y y-position of character 2
     * @return true if pacman collides with the ghost; otherwise return false
     */
    @Override
    public boolean checkCollide(int x, int y) {
        if ((posX - 23) < x && x < (posX + 23) && (posY - 23) < y
                && y < (posY + 23)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method loops through four pictures of every direction to create the
     * mouth opening/closing animation.
     */
    public void mouthAnimation() {
        if (direction == 0) {
            if (pacman == new ImageIcon("src/images/pacman_right_0.png")
                    .getImage()) {
                pacman = new ImageIcon("src/images/pacman_right_1.png")
                        .getImage();
            } else {
                pacman = new ImageIcon("src/images/pacman_right_0.png")
                        .getImage();
            }
        } else if (direction == 1) {
            if (pacman == new ImageIcon("src/images/pacman_left_0.png")
                    .getImage()) {
                pacman = new ImageIcon("src/images/pacman_left_1.png")
                        .getImage();
            } else {
                pacman = new ImageIcon("src/images/pacman_left_0.png")
                        .getImage();
            }
        } else if (direction == 2) {
            if (pacman == new ImageIcon("src/images/pacman_up_0.png")
                    .getImage()) {
                pacman = new ImageIcon("src/images/pacman_up_1.png").getImage();
            } else {
                pacman = new ImageIcon("src/images/pacman_up_0.png").getImage();
            }
        } else if (direction == 3) {
            if (pacman == new ImageIcon("src/images/pacman_down_0.png")
                    .getImage()) {
                pacman = new ImageIcon("src/images/pacman_down_1.png")
                        .getImage();
            } else {
                pacman = new ImageIcon("src/images/pacman_down_0.png")
                        .getImage();
            }
        }

    }

}
