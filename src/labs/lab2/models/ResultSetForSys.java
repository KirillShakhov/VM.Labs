package labs.lab2.models;


import labs.models.Point;

import java.util.ArrayList;

public class ResultSetForSys {
    private Point point;
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();
    ArrayList<Double> d1 = new ArrayList<>();
    ArrayList<Double> d2 = new ArrayList<>();

    public void print(){
        for(int i = 0; i< x.size(); i++){
            System.out.println("Iteration: " + i);
            System.out.println("x = "+x.get(i)+" | y = "+y.get(i));
            System.out.println("abs(f1): "+d1.get(i));
            System.out.println("abs(f2): "+d2.get(i));
        }
    }

    public void addIter(double x, double y, double d1, double d2) {
        this.x.add(x);
        this.y.add(y);
        this.d1.add(d1);
        this.d2.add(d2);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
