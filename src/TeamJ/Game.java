package TeamJ;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This is the Game class which extends JPanel also implements KeyListener
 * and ActionListener. This class defines how our program works.
 */
public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(10, this); // to create a timer object
    // variables
    private long timeInGame = 0;
    private int firedBullet = 0;
    private int score = 0;
    private final BufferedImage backgroundImage;
    private final BufferedImage spaceShipImage;
    private final BufferedImage enemyImage;
    private final BufferedImage bulletImage;
    private  HashSet<Enemy> enemyList = new HashSet<>(); // a list for enemies
    private  HashSet<Bullet> bulletList = new HashSet<>(); // a list for bullets
    private int spaceShipX = 0;
    private  int spaceShipDirectionX = 20;
    private  int enemyDirectionX = 2;
    private int enemyDirectionY = 2;
    private  int bulletDirectionY = 5;

    /**
     * This is the constructor of the Game class.
     * @throws IOException on input error
     */
    public Game() throws IOException {

        // to read images
        backgroundImage = ImageIO.read(new FileImageInputStream(new File("space.png")));
        spaceShipImage = ImageIO.read(new FileImageInputStream(new File("ship.png")));
        bulletImage = ImageIO.read(new FileImageInputStream(new File("bullet.png")));
        enemyImage = ImageIO.read(new FileImageInputStream(new File("enemy.png")));

        timer.start();

        timeInGame = Calendar.getInstance().getTime().getTime();

    }

    /**
     * This is the checkExplosion method which checks if any of the enemies have been shot
     * by the bullets of the spaceship and then repaints. If it is shot the enemy object is
     * removed and score is increased. There are two enhanced for loops.
     */
    public void checkExplosion() {
        for (Enemy enemy : enemyList) {
            for (Bullet bullet : bulletList) {
                if (new Rectangle(bullet.getX(), bullet.getY(), 10, 20)
                        .intersects(new Rectangle(enemy.getX(), enemy.getY(), 50, 40   ) )) {
                    enemyList.remove(enemy);
                    score +=10;
                }
                repaint();
            }
        }
    }

    /**
     * This is the checkGameIsOver method which checks if any of the enemies have crashed into
     * the spaceship using an enhanced for loop.
     * @return true or false
     */
    public boolean checkGameIsOver() {
        for (Enemy enemy : enemyList)
            if (new Rectangle(spaceShipX, 490, spaceShipImage.getWidth() / 10, spaceShipImage.getHeight() / 10)
                    .intersects(new Rectangle(enemy.getX(), enemy.getY(), 50, 40))) {
                return true;
            }
        return false;
        }

    /**
     * This is the paint method which paints and demonstrates everything to
     * the user.
     * @param g to draw images
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, this);

        enemyList.removeIf(enemy -> enemy.getY() <= 0);

        for (Enemy enemy : enemyList) {

            g.drawImage(enemyImage, enemy.getX(), enemy.getY(),
                    enemyImage.getWidth() / 10, enemyImage.getHeight() / 10,
                    this);
            g.drawImage(enemyImage, enemy.getX(), enemy.getY(),
                    enemyImage.getWidth() / 10, enemyImage.getHeight() / 10,
                    this);

        }

        g.drawImage(spaceShipImage, spaceShipX, 490,
                spaceShipImage.getWidth() / 10, spaceShipImage.getHeight() / 10,
                this);

        bulletList.removeIf(bullet -> bullet.getY() <= 0);

        for (Bullet bullet : bulletList) {

            g.drawImage(bulletImage, bullet.getX(), bullet.getY(),
                    bulletImage.getWidth() / 20, bulletImage.getHeight() / 20,
                    this);
            g.drawImage(bulletImage, bullet.getX(), bullet.getY(),
                    bulletImage.getWidth() / 20, bulletImage.getHeight() / 20,
                    this);

        }


        if (checkGameIsOver()) {

            timer.stop();
            long endTime = Calendar.getInstance().getTime().getTime();

            String message = "Game Over!\n" + "Your time in game: "
                    + (endTime - timeInGame) / 1000. + "\n"
                    + "Fired bullet numbers: " + firedBullet + "\n"
                    + "Score: " + score;

            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        checkExplosion();
    }

    /**
     * This is the actionPerformed listener which allows bullets and enemies
     * to move.
     * @param e unused
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Bullet bullet : bulletList) {

            bullet.setY(bullet.getY() - bulletDirectionY);
        }

        for (Enemy enemy : enemyList) {

            enemy.setY(enemy.getY()+enemyDirectionY);

            if (enemy.getY() <= 0) {
                enemyDirectionY *= -1;
            }
        }
        repaint();
    }

    /**
     * keyTyped listener
     * @param e unused
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * This is the keyPressed method which works as a listener.
     * This allows spaceship to move and bullet, enemy object
     * to be created.
     * @param e to assign to a keyboard key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT) {

            if (spaceShipX <= 0) {
                spaceShipX = 0;
            }
            else {
                spaceShipX -= spaceShipDirectionX;
            }

        } else if (c == KeyEvent.VK_RIGHT) {

            if (spaceShipX >= 712) {
                spaceShipX = 712;

            } else {
                spaceShipX += spaceShipDirectionX;
            }

        } else if (c == KeyEvent.VK_SPACE) {
            Random rand = new Random(); // creating a new random object for location of the enemies

            bulletList.add(new Bullet(spaceShipX + 17, 475));
            int xCoordinate = rand.nextInt(650);
            int xCoordinate1 = rand.nextInt(650);
            enemyList.add((new Enemy(xCoordinate,10)));
            enemyList.add((new Enemy(xCoordinate1,10)));

            firedBullet += 1;
        }
    }

    /**
     * keyReleased listener
     * @param e unused
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}