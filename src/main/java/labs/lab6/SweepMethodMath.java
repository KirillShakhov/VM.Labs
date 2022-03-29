package labs.lab6;
import labs.models.IFuncX;
import labs.models.Point;
import java.util.ArrayList;
import java.util.Collections;

public class SweepMethodMath {
    public static ArrayList<Point> solve(IFuncX p, IFuncX q, IFuncX f, double x_a, double x_b, double y_a, double y_b, double eps) {
        ArrayList<Double> alpha = new ArrayList<>();
        alpha.add(0.0);
        ArrayList<Double> beta = new ArrayList<>();
        beta.add(y_a);
        double beg = x_a;
        ArrayList<Double> y_vals = new ArrayList<>();

        for (int i = 0; i < Math.ceil((x_b - x_a)/eps); i++) {
            double A = 1 - eps * p.solve(x_a)/2;
            double B = 2 - q.solve(x_a) * Math.pow(eps, 2);
            double C = 1 + eps * p.solve(x_a) / 2;
            double D = Math.pow(eps, 2) * f.solve(x_a);

            beta.add((D - A * beta.get(i)) / (A * alpha.get(i) - B));
            alpha.add(-C / (A * alpha.get(i) - B));
            x_a += eps;
        }

        for (int i = alpha.size()-2; i > 0; i--) {
            y_vals.add(alpha.get(i + 1) * y_b + beta.get(i + 1));
        }
        Collections.reverse(y_vals);

        ArrayList<Point> points = new ArrayList<>();
        double step = (x_b-beg)/y_vals.size();
        double x = beg;
        for(Double d : y_vals){
            points.add(new Point(x, d));
            x += step;
        }
        return points;
    }
}