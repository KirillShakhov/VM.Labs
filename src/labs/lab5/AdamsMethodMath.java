package labs.lab5;
import labs.models.Point;
import java.util.ArrayList;
import java.util.Scanner;

import static labs.lab5.RungeKuttaMethodMath.rungeKutta;


public class AdamsMethodMath {
/*
    public static double F(double x, double y) // y'
    {
        return 4 * Math.cos(x) + Math.sin(2 * x);
    }

    //Метод Рунге - Кутты 4 порядка
    public static ArrayList<Point> RungeKuttaMethod(double a1, double a2, double b, double h, int n)
    {
        double[] k1 = new double[n+1];
        double[] k2 = new double[n+1];
        double[] k3 = new double[n+1];
        double[] k4 = new double[n+1];
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        x[0] = a1; y[0] = a2;
        for (int i = 1; i <= n; i++)
        {
            x[i] = a1 + i * h;
            k1[i] = h * F(x[i - 1], y[i - 1]);
            k2[i] = h * F(x[i-1] + 0.5 * h, y[i-1] + 0.5 * k1[i]);
            k3[i] = h * F(x[i-1] + 0.5 * h, y[i-1] + 0.5 * k2[i]);
            k4[i] = h * F(x[i-1] + h, y[i-1] + k3[i]);
            y[i] = y[i-1] + (k1[i]+2 * k2[i]+2 * k3[i]+k4[i])/6;
        }
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            points.add(new Point(x[i], y[i]));
        }
        return points;
    }

    //Метод Адамса
    public static ArrayList<Point> AdamsMethod(double a1, double a2, double b, double h, int n)
    {
        double k1, k2, k3, k4;
        double[] f = new double[n+1];
        double[] X = new double[n+1], Y = new double[n+1];
        X[0] = a1; Y[0] = a2;
        f[0] = F(X[0], Y[0]);
        for (int i = 0; i <= n; i++)
        {
            X[i+1] = X[i] + h;
            f[i + 1] = F(X[i + 1], Y[i + 1]);
        }
        for (int i = 1; i < 4; i++)
        {
            k1 = h * f[i - 1];
            k2 = h * F(X[i - 1] + h / 2, Y[i - 1] + k1 / 2);
            k3 = h * F(X[i - 1] + h / 2, Y[i - 1] + k2 / 2);
            k4 = h * F(X[i - 1] + h, Y[i - 1] + k3);
            Y[i] = Y[i - 1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
        for (int i = 3; i < n; i++)
        {
            Y[i + 1] = Y[i] + h / 24 * (55.0 * f[i] - 59.0 * f[i - 1] + 37.0 * f[i - 2] - 9.0 * f[i - 3]);
        }
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            points.add(new Point(X[i], Y[i]));
        }
        return points;
    }

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начальное условие задачи Коши (y(0)):");
        double y0 = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите шаг:");
        double h = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите начало отрезка, на котором надо решить ДУ:");
        double x0 = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите конец отрезка:");
        double end = Double.parseDouble(scanner.nextLine());
        int n = (int)((end - x0) / h);
        System.out.println("Метод Рунге-Кутты 4 порядка:");
        ArrayList<Point> points1 = RungeKuttaMethod(x0, y0, end, h, n);
        for(Point p : points1){
            System.out.println("x: ["+p.getX()+"] y: ["+p.getY()+"]");
        }
        System.out.println("Метод Рунге-Кутты 4 порядка(2 вариант):");
        double ppp = rungeKutta((x, y) -> 4 * Math.cos(x) + Math.sin(2 * x), x0, y0, end, h);
        System.out.println(ppp);
        System.out.println("Метод Адамса:");
        ArrayList<Point> points2 = AdamsMethod(x0, y0, end, h, n);
        for(Point p : points2){
            System.out.println("x: ["+p.getX()+"] y: ["+p.getY()+"]");
        }
    }

 */
}
