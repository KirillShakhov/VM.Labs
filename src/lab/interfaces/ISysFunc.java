package lab.interfaces;

import lab.func.Point;
import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.ArrayList;
import java.util.Scanner;

public interface ISysFunc {
    ArrayList<Double> solve(double val1, double val2);
    ArrayList<IFunc> getDraw();
    static String toString(ArrayList<String> arrayList) {
        StringBuilder result = new StringBuilder("|");
        for(String it : arrayList){
            result.append(it).append("\n   |");
        }
        result.delete(result.length()-5, result.length());
        return result.toString();
    }
    static void execute(ISysFunc func){
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
//        double point1 = MathModule.doubMetod(func, left, right, eps);
//        double point2 = MathModule.doubMetod(func, left, right, eps);
        new GraphModule(func.getDraw(), left, right);
    }
}
