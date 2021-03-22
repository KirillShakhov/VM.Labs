package lab.interfaces;


import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.Scanner;

public interface IFunc {
    double solve(double val);
    static void execute(IFunc func){
        PrinterModule pr = new PrinterModule();
        Scanner scanner = new Scanner(System.in);
        double left = -5, right = 5, eps = 0.001;
        while(true){
            pr.print("Введите левую границу:");
            left = Double.parseDouble(scanner.nextLine());
            pr.print("Введите правую границу:");
            right = Double.parseDouble(scanner.nextLine());
            pr.print("Введите точность:");
            eps = Double.parseDouble(scanner.nextLine());
            break;
        }
        double point1 = MathModule.doubMetod(func, left, right, eps);
        double point2 = MathModule.doubMetod(func, left, right, eps);
        new GraphModule(func, point1, point2, left, right);
    }
}
