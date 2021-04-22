package lab.lab2;
import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;
import lab.lab2.models.Point;
import lab.lab2.models.ResultSetForSys;
import java.util.ArrayList;
import java.util.Scanner;

public class MathModuleLab2 {
    public static void execute(IFunc func){
        //Для нелинейных уравнений
        Scanner scanner = new Scanner(System.in);
        double left = -5, right = 5, eps = 0.001;
        while(true){
            System.out.println("Введите левую границу:");
            left = Double.parseDouble(scanner.nextLine());
            System.out.println("Введите правую границу:");
            right = Double.parseDouble(scanner.nextLine());
            if (left > right){
                double t = left;
                left = right;
                right = t;
            }
            while(true){
                System.out.println("Введите точность:");
                eps = Double.parseDouble(scanner.nextLine());
                if(eps>0 && eps < 1){
                    break;
                }
                else{
                    System.out.println("Точность должна быть больше 0 и меньше 1.");
                }
            }
            break;
        }

        ArrayList<Point> points = new ArrayList<>();
        double point1, point2;
        if (doubChecker(func, left, right)) {
            point1 = doubMetod(func, left, right, eps);
            if (pointChecker(left, right, point1)) {
                System.out.println("Результат метода деления пополам: "+point1);
                points.add(new Point(point1, 0));
            }else{
                System.out.println("Результат метода деления пополам: решение не удовлетворяет заданному интервалу");
            }
        } else {
            System.out.println("Уравнение не имеет решений методом деления пополам.");
        }
        if (cordChecker(func, left, right)) {
            point2 = chordMethod(func, left, right, eps);
            if (pointChecker(left, right, point2)) {
                System.out.println("Результат метода хорд: "+point2);
                points.add(new Point(point2, 0));
            }else{
                System.out.println("Результат метода хорд: решение не удовлетворяет заданному интервалу");
            }
        } else {
            System.out.println("Уравнение не имеет решений методом хорд.");
        }
        new GraphModule(func, points, left, right);
    }

    private static boolean cordChecker(IFunc func, double left, double right) {
        return true;
    }

    public static void execute(ISysFunc func){
        // для систем нелинейных уравнений
        Scanner scanner = new Scanner(System.in);
        double eps, x, y;
        while(true){
            System.out.println("Введите приближение x:");
            x = Double.parseDouble(scanner.nextLine());
            System.out.println("Введите приближение y:");
            y = Double.parseDouble(scanner.nextLine());
            while(true){
                System.out.println("Введите точность:");
                eps = Double.parseDouble(scanner.nextLine());
                if(eps>0 && eps < 1){
                    break;
                }
                else{
                    System.out.println("Точность должна быть больше 0 и меньше 1.");
                }
            }
            break;
        }
        ArrayList<Point> points = new ArrayList<>();
        ResultSetForSys result = iterMetod(func, x, y, eps);
        points.add(result.getPoint());
        result.print();
        new GraphModule(func.getDraw(), points, -10, 10);
    }

    public static boolean doubChecker(IFunc function, double left, double right) {
        boolean value = false;
        for (double i = left; i < right; i += 0.5) {
            for (double j = left; j < right; j += 0.5) {
                if (function.solve(i) * function.solve(j) <= 0) {
                    value = true;
                }
            }
        }
        return value;
    }
    //Выбирается начальное приближение к отрезку [left, right], такое, что f(left)×f(right)<0
    public static double doubMetod(IFunc function, double left, double right, double eps) {
        double dx = 0, xi = 0;
        if (function.solve(left) == 0) {
            return left;
        }
        if (function.solve(right) == 0) {
            return right;
        }
        while (right - left > eps) {
            dx = (right - left) / 2.0;
            xi = left + dx;
            if (sign(function.solve(left)) == sign(function.solve(xi))) left = xi;
            else right = xi;
        }
        return xi;
    }
    private static int sign(double x) {
        if (x > 0) return 1;
        else if (x < 0) return -1;
        return 0;
    }

    public static double chordMethod(IFunc function, double left, double right, double eps) {
        while (Math.abs(right - left) > eps) {
            left = right - (right - left) * function.solve(right) / (function.solve(
                    right) - function.solve(left));
            right = left - (left - right) * function.solve(left) / (function.solve(left) - function.solve(right));
        }
        return right;
    }

    public static ResultSetForSys iterMetod(ISysFunc func, double x, double y, double eps) {
        ResultSetForSys result = new ResultSetForSys();
        double x0=x,y0=y,d1,d2;
        int i = 1;
        do
        {
            x= func.g_x(y0);
            y= func.g_y(x0);
            d1=func.f1(x, y);
            d2=func.f2(x, y);
            x0=x;
            y0=y;
            result.addIter(x, y, Math.abs(d1), Math.abs(d2));
        }while(Math.abs(d1)>eps || Math.abs(d2)>eps);
        result.setPoint(new Point(x, y));
        return result;
    }

    public static boolean pointChecker(double left, double right, double point) {
        return point >= left && point <= right;
    }
}
