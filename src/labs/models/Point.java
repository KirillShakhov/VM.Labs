package labs.models;

public class Point {
    private double x;
    private double y;
    private COLORS color;

    Point(double x){
        this.x = x;
        this.y = 0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, COLORS color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public COLORS getColor() { return color; }
    public void setColor(COLORS color) { this.color = color; }
}

enum COLORS{
    RED,
    GREEN,
}