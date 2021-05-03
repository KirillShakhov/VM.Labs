package labs.models;

import java.awt.*;

public class Point {
    private double x;
    private double y;
    private Color color;

    Point(double x){
        this.x = x;
        this.y = 0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
        this.color = Color.green;
    }

    public Point(double x, double y, Color color){
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
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}