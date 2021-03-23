package lab.interfaces;


import lab.func.Point;
import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.ArrayList;
import java.util.Scanner;

import static lab.modules.MathModule.pointChecker;

public interface IFunc {
    double solve(double x);
    static void execute(IFunc func){
        PrinterModule pr = new PrinterModule();
        Scanner scanner = new Scanner(System.in);
        double left = -5, right = 5, eps = 0.001;
        while(true){
            pr.print("Введите левую границу:");
            left = Double.parseDouble(scanner.nextLine());
            pr.print("Введите правую границу:");
            right = Double.parseDouble(scanner.nextLine());
            if (left > right){
                double t = left;
                left = right;
                right = t;
            }
            pr.print("Введите точность:");
            eps = Double.parseDouble(scanner.nextLine());
            break;
        }
        if (MathModule.doubChecker(func, left, right)) {
            ArrayList<Point> points = new ArrayList<>();
            double point1 = MathModule.doubMetod(func, left, right, eps);
            double point2 = MathModule.chordMethod(func, left, right, eps);
            if (pointChecker(left, right, point1)) {
                pr.print("Результат метода деления пополам: "+point1);
                points.add(new Point(point1, 0));
            }else{
                pr.print("Результат метода деления пополам: решение не удовлетворяет заданному интервалу");
            }
            if (pointChecker(left, right, point2)) {
                pr.print("Результат метода хорд: "+point2);
                points.add(new Point(point2, 0));
            }else{
                pr.print("Результат метода хорд: решение не удовлетворяет заданному интервалу");
            }
            new GraphModule(func, points, left, right);
        } else {
            pr.print("Уравнение решений не имеет в рамках данного диапазона/метода");
            new GraphModule(func, left, right);
        }
    }
}
