package labs.lab6.commands;

import labs.models.ICommand;

import java.util.Scanner;

public class SweepMethod implements ICommand {
    @Override
    public String getMessage() {
        return "Решение краевой задачи для линейного ДУ второго порядка вида y'' + p(x)y' + q(x)y = f(x) разностным методом (метод прогонки).";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите уравнение:");
        System.out.println("1. y'' +  x^(-2) y' + x^3 y = e^x");
        System.out.println("2. y'' + x^2 y' + 2y  = 2x - 1");
        double t = Double.parseDouble(scanner.nextLine());
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
        //logic.runthrough(xa, xb, ya, yb, h, t)
    }
}
