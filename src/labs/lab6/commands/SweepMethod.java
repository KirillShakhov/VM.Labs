package labs.lab6.commands;

import labs.lab6.SweepMethodMath;
import labs.models.ICommand;
import labs.models.IFuncX;
import labs.models.Point;
import labs.modules.GraphModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SweepMethod implements ICommand {
    @Override
    public String getMessage() {
        return "Решение краевой задачи для линейного ДУ второго порядка вида y'' + p(x)y' + q(x)y = f(x) разностным методом (метод прогонки).";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<IFuncX> fp = new ArrayList<>();
        ArrayList<IFuncX> fg = new ArrayList<>();
        ArrayList<IFuncX> ff = new ArrayList<>();
        System.out.println("Выберите уравнение:");
        // 1
        System.out.println("1. y'' +  x^(-2) y' + x^3 y = e^x");
        fp.add(x -> 1 / Math.pow(x, 2));
        fg.add(x -> Math.pow(x, 3));
        ff.add(x -> Math.pow(Math.E, x));
        // 2
        System.out.println("2. y'' + x^2 y' + 2y  = 2x - 1");
        fp.add(x -> Math.pow(x, 2));
        fg.add(x -> 1.0);
        ff.add(x -> 2 * x - 1);
        //
        IFuncX p, g, f;
        while (true) {
            try {
                int t = Integer.parseInt(scanner.nextLine())-1;
                p = fp.get(t);
                g = fg.get(t);
                f = ff.get(t);
                break;
            } catch (Exception e) {
                System.out.println("Нет такого уравнения");
            }
        }
        System.out.println("Введите концы промежутка через пробел (хa xb)");
        String[] x = scanner.nextLine().split(" ");
        double xa = Double.parseDouble(x[0]);
        double xb = Double.parseDouble(x[1]);
        System.out.println("Введите начальные условия через пробел (ya уb)");
        String[] y = scanner.nextLine().split(" ");
        double ya = Double.parseDouble(y[0]);
        double yb = Double.parseDouble(y[1]);
        System.out.println("Введите точность");
        double h = Double.parseDouble(scanner.nextLine());
        ArrayList<Point> xy = SweepMethodMath.solve(p, g, f, xa, xb, ya, yb, h);
        Map<String, ArrayList<Point>> funcs = new HashMap<>();
        funcs.put("Результат", xy);
        new GraphModule(funcs);
    }
}
