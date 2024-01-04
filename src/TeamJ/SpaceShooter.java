/**
 * <h1>Space Shooter Game</h1>
 * The space shooter game implements an application that
 * simply creates a GUI game using Java Swing.
 *
 * In this game the user controls the spaceship to shoot
 * enemy spaceships. If the user crashes into an enemy
 * spaceship the game ends showing time spent, bullets
 * fired and score of the user. The more you shoot the
 * more enemies show up so be careful with your bullets! *
 *
 * @authors
 * Barış Atakan Aktaş 20191701027
 * Elif Şanlıtürk     20191701003
 * Afife Koç          20191701021
 * @version 1.0
 * @since 2021-12-26
 */
package TeamJ;

import java.io.IOException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This is the SpaceShooter method which contains main method
 * inside to run the program.
 */
public class SpaceShooter {
    /**
     * This is the main method which runs the program by
     * initiating GUI.
     * @param args unused
     * @throws IOException on input error
     */
    public static void main(String[] args) throws IOException {
        GameScreen screen = new GameScreen(); // to create a GameScreen object

        screen.setFocusable(false);
        screen.setResizable(false); // to set frame resizeable false

        screen.setSize(800, 600); // to set screen size
        screen.setDefaultCloseOperation(EXIT_ON_CLOSE); // to close the program when pressed the exit button

        Game game = new Game(); // to initiate a game object

        game.requestFocus();

        game.addKeyListener(game); // to add key listener to the game

        game.setFocusable(true);

        game.setFocusTraversalKeysEnabled(false);

        screen.add(game); // to add game to the frame

        screen.setVisible(true); // to make frame visible
    }
}