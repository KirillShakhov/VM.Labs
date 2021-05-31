package labs.lab6;
import labs.models.IFuncX;
import java.util.ArrayList;
import java.util.Collections;

public class SweepMethodMath {


    public static void solve(IFuncX p, IFuncX g, IFuncX f, double xa, double xb, double ya, double yb, double h) {
        ArrayList<Double> alpha = new ArrayList<>();
        alpha.add(0.0);
        ArrayList<Double> beta = new ArrayList<>();
        beta.add(ya);
        double beg = xa;
        ArrayList<Double> y_vals = new ArrayList<>();

        for (int i = 0; i < Math.ceil((xb - xa)/h); i++) {
            double A = 1 - h * p.solve(xa)/2;
            double B = 2 - g.solve(xa) * Math.pow(h, 2);
            double C = 1 + h * p.solve(xa) / 2;
            double D = Math.pow(h, 2) * f.solve(xa);

            beta.add((D - A * beta.get(i)) / (A * alpha.get(i) - B));
            alpha.add(-C / (A * alpha.get(i) - B));
            xa += h;
        }

        for (int i = alpha.size()-2; i > 0; i--) {
            y_vals.add(alpha.get(i + 1) * yb + beta.get(i + 1));
            Collections.reverse(y_vals);
        }
        //return ;
        //get_graph(beg, xb, y_vals);
    }
}