package game.entities;

public class Entity {

    protected double x;
    protected double y;
    protected int size;

    protected Entity(double x, double y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public int getSize(){
        return this.size;
    }

}
