package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;
import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.Scanner;

public class FirstFunc implements IFunc, ICommand {
    PrinterModule pr = new PrinterModule();
    Scanner scanner = new Scanner(System.in);
    @Override
    public double solve(double val) {
        return Math.pow(val, 2) + val + 2;
    }

    @Override
    public String getMessage() {
        return "x^2+x+2";
    }

    @Override
    public void execute() {
        double left = -5, right = 5, eps = 0.001;
//        while(true){
//            if (scanner.hasNext()) {
//                pr.print("Введите левую границу:");
//                left = Double.parseDouble(scanner.nextLine());
//                pr.print("Введите правую границу:");
//                right = Double.parseDouble(scanner.nextLine());
//                pr.print("Введите точность:");
//                eps = Double.parseDouble(scanner.nextLine());
//                break;
//            }
//            else{
//                pr.print("Завершение работы:");
//                System.exit(0);
//            }
//        }
        double point1 = MathModule.doubMetod(this, left, right, eps);
        double point2 = MathModule.doubMetod(this, left, right, eps);
        GraphModule gm = new GraphModule(this, point1, point2, left, right);
    }
}
