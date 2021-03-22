package lab.func;

public class Point {
    private double x;
    private double y;
    Point(double x){
        this.x = x;
        this.y = 0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
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
}
