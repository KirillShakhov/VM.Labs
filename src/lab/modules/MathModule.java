package lab.modules;

import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;

public class MathModule {

    public MathModule() {
    }

    public static boolean doubChecker(IFunc function, double left, double right) {
        boolean value = false;
        for (double i = left; i < right; i+=0.5){
            for (double j = left; j < right; j+=0.5) {
                if (function.solve(i) * function.solve(j) <= 0) {
                    value = true;
                }
            }
        }
        return value;
    }
    public static double doubMetod(IFunc function, double left, double right, double eps) {
        if (function.solve(left) == 0){
            return left;
        }
        if (function.solve(right) == 0){
            return right;
        }
        double dx = 0, xi = 0;
        while (right - left > eps) {
            dx = (right - left) / 2.0;
            xi = left + dx;
            if (sign(function.solve(left)) != sign(function.solve(xi))){
                right = xi;
            }
            else{
                left = xi;
            }
        }
        return xi;
    }
    public static double chordMethod(IFunc function, double left, double right, double eps) {
        while (Math.abs(right - left) > eps) {
            left = right - (right - left) * function.solve(right) / (function.solve(
                    right) - function.solve(left));
            right = left - (left - right) * function.solve(left) / (function.solve(
                    left) - function.solve(right));
        }

        return right;
    }

    private static int sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        return 0;
    }

    public static double iterMetod(ISysFunc func, double eps){
        double x = 0;
        for(double iter = 1; eps < Math.abs(func.solvef(x)); iter = iter + 1) {
            x = func.solveg(x);
        }
        return x;
    }
    private static boolean pointChecker(double left, double right, double point) {
        return left <= point && point <= right;
    }
}
