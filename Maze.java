package basecode;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class defines the maze of the game. It contains the food pellets and the
 * walls. It also checks the interaction between some character and the elements
 * in the maze.
 *
 */
public class Maze {

    private int gridSize = 24;
    private int numOfGrid = 15;
    private int[][] grids;
    private int score = 0;

    /**
     * The constructor initializes the grids and assign them walls and food
     * pellets. 0 for food, 1 for walls, -1 for no food.
     */
    public Maze() {
        grids = new int[][] { { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1 }, };
    }

    /**
     * This method draw rectangles to represent walls using Graphics2D.
     * 
     * @param g
     */
    public void drawWalls(Graphics2D g) {
        g.setColor(new Color(254, 67, 101));
        // Fill all the grids of 1 with rectangles.
        for (int i = 0; i < numOfGrid; i++) {
            for (int j = 0; j < numOfGrid; j++) {
                if (grids[i][j] == 1) {
                    g.fillRect(i * gridSize + 10, j * gridSize + 10, gridSize,
                            gridSize);
                }
            }
        }
    }

    /**
     * This method draw circles with radius of 5 to represent food pellets.
     * 
     * @param g
     */
    public void drawFood(Graphics2D g) {
        g.setColor(new Color(249, 205, 173));
        // Fill all the grids of 0 with circles.
        for (int i = 0; i < numOfGrid; i++) {
            for (int j = 0; j < numOfGrid; j++) {
                if (grids[i][j] == 0) {
                    g.fillOval(i * gridSize + 20, j * gridSize + 20, 5, 5);
                }
            }
        }
    }

    /**
     * check if there is obstacle that characters shouldn't go through.
     * 
     * @param x the x-axis position of the character
     * @param y the y-axis position of the character
     * @return true means that there's wall ahead, false means no obstacles
     */
    public boolean checkWalls(int x, int y) {
        boolean isWall = false;
        int i = (x - 10) / gridSize;
        int j = (y - 10) / gridSize;
        int i2 = (x + 23 - 10) / gridSize;
        int j2 = (y + 23 - 10) / gridSize;

        if (i < 15 && j < 15 && i2 < 15 && j2 < 15) {
            if (grids[i][j] == 1 || grids[i][j2] == 1 || grids[i2][j] == 1
                    || grids[i2][j2] == 1) {
                isWall = true;
            }
        } else {
            isWall = true;
        }
        return isWall;
    }

    /**
     * Check if the food position overlaps with the a pacman. If so, update the
     * status of the grid to "no food".
     * 
     * @param x the x-axis position of pacman
     * @param y the y-axis position of pacman
     */
    public void updateFood(int x, int y) {
        int i = (x) / gridSize;
        int j = (y) / gridSize;

        if (grids[i][j] == 0) {
            grids[i][j] = -1;
            score++;
        }
    }

    /**
     * The getter method gets the score of the pacman.
     * 
     * @return the number of score.
     */
    public int getScore() {
        return score;
    }

    /**
     * This method gets the number of food pellets initially in the maze.
     * 
     * @return the number of initial food pellets in the maze.
     */
    public int getNumOfFood() {
        int numOfFood = 0;
        for (int i = 0; i < numOfGrid; i++) {
            for (int j = 0; j < numOfGrid; j++) {
                if (grids[i][j] == 0) {
                    numOfFood++;
                }
            }
        }
        return numOfFood;
    }

}
