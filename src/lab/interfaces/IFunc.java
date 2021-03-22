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
            pr.print("Введите точность:");
            eps = Double.parseDouble(scanner.nextLine());
            break;
        }
//        if (doubChecker(first, left, right)) {
//            print("Уравнение решений не имеет в рамках данного диапазона/метода")
//            paintGraph(first)
//            else:
//        }
        double point1 = MathModule.doubMetod(func, left, right, eps);
        pr.print("Ответ:");
        double point2 = MathModule.doubMetod(func, left, right, eps);
        new GraphModule(func, new Point(MathModule.doubMetod(func, left, right, eps), 0), new Point(MathModule.doubMetod(func, left, right, eps), 0), left, right);
    }
}
