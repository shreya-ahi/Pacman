package basecode;

import java.awt.Image;

/**
 * This interface should be implemented by characters in the game such as the
 * pacman and the ghost.
 *
 */
public interface Character {

    /**
     * This method moves the character to the left with fixed velocity. It will
     * bypass the obstacles and only move in a specified area of the board.
     */
    public void moveLeft();

    /**
     * This method moves the character to the right with fixed velocity. It will
     * bypass the obstacles and only move in a specified area of the board.
     */
    public void moveRight();

    /**
     * This method moves the character up with fixed velocity. It will bypass
     * the obstacles and only move in a specified area of the board.
     */
    public void moveUp();

    /**
     * This method moves the character down with fixed velocity. It will bypass
     * the obstacles and only move in a specified area of the board.
     */
    public void moveDown();

    /**
     * Gets the image of the character.
     * 
     * @return the Image instance variable of the character.
     */
    public Image getImage();

    /**
     * Gets the position of the character on x-axis.
     * 
     * @return the number representing the position of the character on x-axis.
     */
    public int getPosX();

    /**
     * Gets the position of the character on y-axis.
     * 
     * @return the number representing the position of the character on y-axis.
     */
    public int getPosY();

    /**
     * This method is used for setting the position on x-axis.
     * 
     * @param x the position on x-axis
     */
    public void setPosX(int x);

    /**
     * This method is used for setting the position on y-axis.
     * 
     * @param y the position on y-axis
     */
    public void setPosY(int y);

    /**
     * Checks for collisions between characters.
     * 
     * @param x x-axis position of the character
     * @param y y-axis position of the character
     * @return true if the there is a collision between the character and other
     *         character, otherwise it returns false.
     */
    public boolean checkCollide(int x, int y);

    /**
     * Gets the direction that the character is moving.
     * 
     * @return the number representing the direction that the character is
     *         moving.
     */
    public int getDirection();

}
