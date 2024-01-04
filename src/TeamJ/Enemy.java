package TeamJ;

/**
 * This is the Enemy class which contains two private
 * variables, setters, getters and a constructor.
 */
public class Enemy {
    private int x; // variable for x coordinate
    private int y; // variable for y coordinate

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // constructor
    public Enemy(int x, int y){
        setX(x);
        setY(y);
    }
}