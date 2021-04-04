package lab;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        double x;
        double eps;
        System.out.println("Введите начальную точку");
        x = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите точность");
        eps = Double.parseDouble(scanner.nextLine());
        for(double iter = 1; eps < Math.abs(f(x)); iter = iter + 1)
        {
            //*Итераций может быть очень много, поэтому рекомендую забыть
            //о целых а использовать дабл как счётчик, хотя в принципе если
            //решение не нашли за 10-100 итераций то решения для данного коэффициента
            //при f(x) в g(x) нет и надо его менять
            System.out.println("Iter: " + iter);
            System.out.println("x: " + x);
            System.out.println("g(x): " + g(x));
            System.out.println("f(x): " + f(x));
            x = g(x);
        }
    }

//    static double f(double x)
//    {
//        return Math.pow(2.0,x) - 2*Math.pow(x, 2.0) - 1;
//    }
//
//    static double g(double x)
//    {
//        return x + 0.5*f(x);
//    }
    public static double f(double x) {
        return (Math.pow(x,2)-16)/(3*x);
    }
    public static double g(double x) {
        return 3*Math.pow(x, 2)+2*x*f(x)-Math.pow(f(x), 2);
    }
}
