package basecode;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class extends from the JFrame and it creates the board object. It also
 * defines the size of the window.
 *
 */
public class PacmanGame extends JFrame {

    private final static int WINDOW_WIDTH = 391;
    private final static int WINDOW_HEIGHT = 475;
    private Board myBoard = new Board(WINDOW_WIDTH, WINDOW_HEIGHT);

    /**
     * This method adds the defined board to JFrame and sets it visible.
     */
    public PacmanGame() {

        setTitle("PacmanGame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        // set where the window to show up on the screen
        setLocationRelativeTo(null);
        add(myBoard, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Gets the width of the window.
     * 
     * @return the width of the window
     */
    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    /**
     * Gets the height of the window.
     * 
     * @return the height of the window
     */
    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    /**
     * @param args no arguments
     */
    public static void main(String[] args) {
        PacmanGame myGame = new PacmanGame();

    }
}
