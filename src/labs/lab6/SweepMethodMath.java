//package labs.lab6;
//
//import labs.models.IFuncX;
//
//import java.util.ArrayList;
//
//public class SweepMethodMath {
//    public static solve(double xa, double xb, double ya, double yb, double h) {
//        ArrayList<Double> alpha = new ArrayList<Double>(1);
//        ArrayList<Double> beta = new ArrayList<>();
//        beta.add(ya);
//        double beg = xa;
//        ArrayList<Double> y_vals = new ArrayList<>();
//
//        for i in range(math.ceil((xb - xa)/h)) {
//            double A = 1 - h * get_p(xa, t) / 2
//            double B = 2 - get_g(xa, t) * math.pow(h, 2)
//            double C = 1 + h * get_p(xa, t) / 2
//            double D = Math.pow(h, 2) * get_f(xa, t)
//
//            beta.add((D - A * beta[i]) / (A * alpha[i] - B));
//            alpha.add(-C / (A * alpha[i] - B));
//
//            xa += h
//        }
//        for i in range(len(alpha)-2, 0, -1) {
//            y_vals.add(alpha[i + 1] * yb + beta[i + 1]);
//            y_vals.reverse();
//        }
//        return ;
//        get_graph(beg, xb, y_vals);
//    }
//}
