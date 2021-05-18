package labs.lab5;
import labs.models.Point;
import java.util.ArrayList;
import java.util.Scanner;


public class AdamsMethodMath {

    double F(double x, double y) // y'
    {
        return 4 * Math.cos(x) + Math.sin(2 * x);
    }

    //Метод Рунге - Кутты 4 порядка
    public ArrayList<Point> RungeKuttaMethod(double a1, double a2, double b, int h, int n)
    {
        double[] Y1 = new double[n];
        double[] Y2 = new double[n];
        double[] Y3 = new double[n];
        double[] Y4 = new double[n];
        double[] X = new double[n], Y = new double[n];
        X[0] = a1; Y[0] = a2;
        for (int i = 1; i <= n; i++)
        {
            X[i] = a1 + i * h;
            Y1[i] = h * F(X[i - 1], Y[i - 1]);
            Y2[i] = h * F(X[i - 1] + h / 2.0, Y[i - 1] + Y1[i] / 2.0);
            Y3[i] = h * F(X[i - 1] + h / 2, Y[i - 1] + Y2[i] / 2);
            Y4[i] = h * F(X[i - 1] + h, Y[i - 1] + Y3[i]);
            Y[i] = Y[i - 1] + (Y1[i] + 2 * Y2[i] + 2 * Y3[i] + Y4[i]) / 6;
        }
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            points.add(new Point(X[i], Y[i]));
        }
        return points;
    }

    //Метод Адамса
    ArrayList<Point> AdamsMethod(double a1, double a2, double b, double h, int n)
    {
        double k1, k2, k3, k4;
        double[] f = new double[n];
        double[] X = new double[n], Y = new double[n];
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

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начальное условие задачи Коши (y(0)):");
        double y0 = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите шаг:");
        int h = Integer.parseInt(scanner.nextLine());
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
        System.out.println("Метод Адамса:");
        ArrayList<Point> points2 = AdamsMethod(x0, y0, end, h, n);
        for(Point p : points2){
            System.out.println("x: ["+p.getX()+"] y: ["+p.getY()+"]");
        }
    }
}
