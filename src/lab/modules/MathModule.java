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
            right = left - (left - right) * function.solve(left) /(function.solve(left) - function.solve(right));
        }
        return right;
    }

    public static double chordMethod2(IFunc function, double left, double right, double eps) {
        double c = 0;
        while(Math.abs(function.solve(right)-function.solve(left))>eps){
            c=(function.solve(right)*left-function.solve(left)*right)/(function.solve(right)-function.solve(left));
            if((function.solve(left)*function.solve(c)) > 0) left=c;
            else right=c;
        }
        return c;
    }

    public static double chordMethod3(IFunc function, double left, double right, double eps) {
        double t = 0;
        while (Math.abs(right - left) >= eps)
        {
            t = left + (function.solve(right) * (right - left)) / (function.solve(right) - function.solve(left));

            if (function.solve(left) * function.solve(t) < 0)
            {
                right = t;
            }
            else if (function.solve(t) * function.solve(right) < 0)
            {
                left = t;
            }
            else
                return t;
        }
        return t;
    }

//    public static double chordMethod(IFunc function, double left, double right, double eps) {
//        while(Math.abs(function.solve(right)) > eps)
//        {
//            left = right - ((right - left) * function.solve(right))/(function.solve(right) - function.solve(left));
//            right = left - ((left - right) * function.solve(left))/(function.solve(left) - function.solve(right));
//        }
//        return right;
//    }


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

    static double f(double x) {
        return Math.pow(2.0,x) - 2*Math.pow(x, 2.0) - 1;
        //y = 2^x-2x^2-1
    }

    public static double g(double x){
        return x + 0.5*f(x);
        //x + 0.5y
    }

    public static double iterMetod2(ISysFunc func, double x, double eps){
        for(double iter = 1; eps < Math.abs(f(x)); iter = iter + 1)
        {
            //*Итераций может быть очень много, поэтому рекомендую забыть
            //о целых а использовать дабл как счётчик, хотя в принципе если
            //решение не нашли за 10-100 итераций то решения для данного коэффициента
            //при f(x) в g(x) нет и надо его менять
//            cout<<"Iteration : "<<setprecision(0)<<iter<<endl;
//            cout<<"x    = "<<x   <<endl;
//            cout<<"g(x) = "<<g(x)<<endl;
//            cout<<"f(x) = "<<f(x)<<endl;
            x = g(x);
        }
        return x;
    }
    public static boolean pointChecker(double left, double right, double point) {
        System.out.println(left+":"+right+":"+point);
        return point >= left && point <= right;
    }
}
