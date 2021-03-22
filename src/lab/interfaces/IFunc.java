package lab.interfaces;


import lab.func.Point;
import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.Scanner;

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
            double point1 = MathModule.doubMetod(func, left, right, eps);
            double point2 = MathModule.chordMethod(func, left, right, eps);
            pr.print("Результат метода деления пополам: "+point1);
            pr.print("Результат метода хорд: "+point2);
            new GraphModule(func, new Point(point1, 0), new Point(point2, 0), left, right);
        } else {
            pr.print("Уравнение решений не имеет в рамках данного диапазона/метода");
            new GraphModule(func, left, right);

        }
    }
}
