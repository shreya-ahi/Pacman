package basecode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This Board class extends JPanel and implements the ActionListener. It
 * initializes and updates the elements on the board.
 *
 */
public class Board extends JPanel implements ActionListener {

    private Dimension d;
    private int livesLeft;
    private int livesLeft2;
    private Timer timer;

    static int counter;
    private int countPauseTime = 0;
    private String status = "start";
    private int level = 1;
    private Maze maze = new Maze();
    private int score;
    private int numOfFood = maze.getNumOfFood();
    private Pacman pacman;
    private Pacman pacman2;
    private List<Ghost> ghosts = new ArrayList<>();
    private Image lives = new ImageIcon("src/images/pacman_right_0.png")
            .getImage();
    private List<int[]> ghostPos = new ArrayList<>();
    private boolean stopImage = false;
    private boolean stopImage2 = false;

    /**
     * This method defines the board with specified weight and height. It also
     * initialize few elements.
     * 
     * @param w the width of the board
     * @param h the height of the board
     */
    public Board(int w, int h) {

        d = new Dimension(w, h);
        livesLeft = 3;
        livesLeft2 = 3;
        addKeyListener(new Keyboard());
        setFocusable(true);
        setBackground(Color.white);

        timer = new Timer(40, this);
        counter = 0;
        countPauseTime = 0;
    }

    @Override
    public void addNotify() {
        super.addNotify();
    }

    /**
     * This method restarts the game with all elements of the game relaunched.
     * 
     * @param num number of players
     */
    public void restart(int num) {
        timer.start();
        if (!status.contains("levelUp")) {
            level = 1;
        }
        if (num == 1) {
            pacman = new Pacman(10, 10);
            livesLeft = 3;
        } else if (num == 2) {
            pacman = new Pacman(345, 10);
            livesLeft = 3;
            pacman2 = new Pacman(10, 10);
            livesLeft2 = 3;
        }
        ghosts.clear();
        ghostPos.clear();
        for (int i = 0; i < level + 2; i++) {
            if (i < 10) { // Max number of ghosts is 10.
                Ghost ghost = new Ghost(i);
                ghosts.add(ghost);
                ghostPos.add(new int[] { ghost.getPosX(), ghost.getPosY() });
            }
        }
        maze = new Maze();
        counter = 0;
        countPauseTime = 0;
        stopImage = false;
        stopImage2 = false;
        livesLeft = 3;
        livesLeft2 = 3;
    }

    class Keyboard extends KeyAdapter {

        // Stores all the keys that are pressed.
        List<Integer> keys = new ArrayList<>();

        @Override
        public void keyPressed(KeyEvent e) {
            // Add the keys to the list if not in the list.
            if (!keys.contains(Integer.valueOf(e.getKeyCode()))) {
                keys.add(Integer.valueOf(e.getKeyCode()));
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_LEFT))
                    && status.contains("game")) {
                pacman.moveLeft();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_RIGHT))
                    && status.contains("game")) {
                pacman.moveRight();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_UP))
                    && status.contains("game")) {
                pacman.moveUp();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_DOWN))
                    && status.contains("game")) {
                pacman.moveDown();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_A))
                    && status.contains("game")) {
                pacman2.moveLeft();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_D))
                    && status.contains("game")) {
                pacman2.moveRight();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_W))
                    && status.contains("game")) {
                pacman2.moveUp();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_S))
                    && status.contains("game")) {
                pacman2.moveDown();
            }
            if (keys.contains(Integer.valueOf(KeyEvent.VK_ESCAPE))
                    && status.contains("game")) {
                status = "end";
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_P))) {
                // Pause the game if it is not already paused, and resume if it
                // is.
                if (status.equals("game")) {
                    timer.stop();
                    status = "pause";
                    counter = 0;
                    countPauseTime = 0;
                } else if (status.equals("pause")) {
                    timer.start();
                    status = "game";
                } else if (status.equals("game2")) {
                    timer.stop();
                    status = "pause2";
                    counter = 0;
                    countPauseTime = 0;
                } else if (status.equals("pause2")) {
                    timer.start();
                    status = "game2";
                }
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_S))
                    && (status.equals("start") || status.equals("end"))) {
                status = "choose";
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_S))
                    && status.equals("levelUp")) {
                restart(1);
                status = "game";
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_S))
                    && status.equals("levelUp2")) {
                restart(2);
                status = "game2";
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_1))
                    && (status.equals("choose") || status.equals("lose"))) {
                restart(1);
                status = "game";
            } else if (keys.contains(Integer.valueOf(KeyEvent.VK_2))
                    && (status.equals("choose") || status.equals("lose"))) {
                restart(2);
                status = "game2";
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (keys.contains(Integer.valueOf(e.getKeyCode()))) {
                keys.remove(keys.indexOf(Integer.valueOf(e.getKeyCode())));
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font smallFont = new Font("Chalkboard", Font.BOLD, 14);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(252, 157, 154));
        g2d.fillRect(0, 0, d.width, d.height);
        g2d.setFont(smallFont);
        g2d.setColor(Color.white);
        // Switch to end status after pressing Esc.
        if (status.equals("end")) {
            g2d.drawString("Do you want to continue?", 110, 180);
            g2d.drawString("- Press S to restart the game.", 90, 210);
        } // Switch to start page.
        else if (status.equals("start")) {
            g2d.drawString("Press s or S to start the game!", 90, 200);
        } // Switch to game page.
        else if (status.equals("game")) {
            maze.drawWalls(g2d);
            maze.drawFood(g2d);
            // Draw all ghosts.
            for (Ghost ghost : ghosts) {
                g2d.drawImage(ghost.getImage(), ghost.getPosX(),
                        ghost.getPosY(), this);
            }
            // Draw pacman.
            g2d.drawImage(pacman.getImage(), pacman.getPosX(), pacman.getPosY(),
                    this);
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + score, 276, 415);
            g2d.drawString("Level: " + level, 276, 435);
            g2d.drawString("Lives Left: ", 10, 422);

            for (short i = 0; i < livesLeft; i++) {
                g2d.drawImage(lives, i * 28 + 90, 405, this);
            }
        } else if (status.equals("game2")) {
            maze.drawWalls(g2d);
            maze.drawFood(g2d);
            // Draw all ghosts.
            for (Ghost ghost : ghosts) {
                g2d.drawImage(ghost.getImage(), ghost.getPosX(),
                        ghost.getPosY(), this);
            }
            // Draw pacman.
            if (!stopImage) {
                g2d.drawImage(pacman.getImage(), pacman.getPosX(),
                        pacman.getPosY(), this);
            }
            if (!stopImage2) {
                g2d.drawImage(pacman2.getImage(), pacman2.getPosX(),
                        pacman2.getPosY(), this);
            }
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + score, 276, 415);
            g2d.drawString("Level: " + level, 276, 435);
            g2d.drawString("Lives Left (1): " + livesLeft, 10, 415);
            g2d.drawString("Lives Left (2): " + livesLeft2, 10, 435);
        }
        // Switch to pause page.
        else if (status.contains("pause")) {
            g2d.drawString("- Game paused -", 140, 180);
        } // Switch to level up page.
        else if (status.contains("levelUp")) {
            timer.stop();
            g2d.setFont(new Font("Chalkboard", Font.BOLD, 20));
            g2d.drawString("Level Up!", 145, 180);
            g2d.setFont(smallFont);
            g2d.drawString("- Press S to continue -", 110, 210);
        } // Switch to lose page.
        else if (status.equals("lose")) {
            g2d.drawString("You lose...", 150, 120);
            g2d.drawString("To restart the game,", 120, 150);
            g2d.drawString("you need to choose a mode:", 100, 180);
            g2d.drawString("- One-player : Press 1", 110, 210);
            g2d.drawString("- Two-player : Press 2", 110, 240);
        } else if (status.equals("choose")) {
            g2d.drawString("Please choose a mode:", 115, 160);
            g2d.drawString("- One-player : Press 1", 110, 190);
            g2d.drawString("- Two-player : Press 2", 110, 220);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
        if (counter % 14 == 0) {
            // Mouth animation.
            pacman.mouthAnimation();
            if (status == "game2") {
                pacman2.mouthAnimation();
            }
        }
        
        // Update score.
        score = maze.getScore() + (level - 1) * numOfFood;
        // Level up if all food pellets are eaten.
        if (score / numOfFood == level) {
            level++;
            if (status.equals("game")) {
                status = "levelUp";
            } else if (status.equals("game2")) {
                status = "levelUp2";
            }
            livesLeft = 3;
            livesLeft2 = 3;
        }

        for (Ghost ghost : ghosts) {
            // Pause the ghosts for few seconds after half of the food is eaten.
            if (score > (level - 0.5) * numOfFood) {
                countPauseTime++;
            }
            if (countPauseTime > 300 || countPauseTime == 0) {
                ghost.moveRandom();
            }
            // Check collisions between pacman and the ghosts.
            if (status == "game") {
                if (pacman.checkCollide(ghost.getPosX(), ghost.getPosY())) {
                    livesLeft--;
                    pacman = new Pacman(10, 10);
                    // Reset the position of the ghost after collision.
                    ghosts.set(ghosts.indexOf(ghost),
                            new Ghost(ghosts.indexOf(ghost)));
                }
            }
            if (status == "game2") {
                if (pacman.checkCollide(ghost.getPosX(), ghost.getPosY())
                        && !stopImage) {
                    livesLeft--;
                    pacman = new Pacman(345, 10);
                    // Reset the position of the ghost after collision.
                    ghosts.set(ghosts.indexOf(ghost),
                            new Ghost(ghosts.indexOf(ghost)));
                }
                if (pacman2.checkCollide(ghost.getPosX(), ghost.getPosY())
                        && !stopImage2) {
                    livesLeft2--;
                    pacman2 = new Pacman(10, 10);
                    // Reset the position of the ghost after collision.
                    ghosts.set(ghosts.indexOf(ghost),
                            new Ghost(ghosts.indexOf(ghost)));
                }
            }
        }
        // Check if no lives left.
        if (status == "game") {
            if (livesLeft == 0) {
                status = "lose";
            }
        } else {
            if (livesLeft == 0) {
                stopImage = true;
            }
            if (livesLeft2 == 0) {
                stopImage2 = true;
            }
            if (livesLeft == 0 && livesLeft2 == 0) {
                status = "lose";
            }
        }
        // Update the food pellets in the maze.
        if (!stopImage) {
            maze.updateFood(pacman.getPosX(), pacman.getPosY());
        }
        if (status == "game2" && !stopImage2) {
            maze.updateFood(pacman2.getPosX(), pacman2.getPosY());
        }

        repaint();
    }
}
