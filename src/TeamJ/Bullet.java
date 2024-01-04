package TeamJ;

/**
 * This is the Bullet class which contains two private
 * variables, setters, getters and a constructor.
 */
public class Bullet {
    private int x; // variable for x coordinate
    private int y; // variable for y coordinate

    // constructor
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}