package labs.lab5.commands;

import labs.models.ICommand;
import labs.models.IFuncXY;

public class RungeKuttaMethod implements ICommand {
    @Override
    public String getMessage() {
        return "Метод Рунге-Кутта 4 го порядка";
    }

    @Override
    public void execute() {
        double x0 = 0, y = 1, x = 2, h = 0.2;
        // Пример дифференциального уравнения "dy / dx = (x - y) / 2"
        IFuncXY f = (x1, y1) -> (x1 - y1) / 2;
        System.out.println("The value of y at x is : " + rungeKutta(f, x0, y, x, h));
    }
    // Находит значение y для заданного x, используя размер шага h
    // и начальное значение y0 в x0.
    double rungeKutta(IFuncXY f, double x0, double y0, double x, double h) {
        // Подсчитать количество итераций, используя размер шага или
        // высота шага h
        int n = (int) ((x - x0) / h);
        double k1, k2, k3, k4, k5;
        // Итерация по количеству итераций
        double y = y0;
        for (int i = 1; i <= n; i++) {
            // Применить формулы Рунге Кутты, чтобы найти
            // следующее значение у
            k1 = h * f.solve(x0, y);
            k2 = h * f.solve(x0 + 0.5 * h, y + 0.5 * k1);
            k3 = h * f.solve(x0 + 0.5 * h, y + 0.5 * k2);
            k4 = h * f.solve(x0 + h, y + k3);
            // Обновить следующее значение y
            y = y + (1.0 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            ;
            // Обновляем следующее значение x
            x0 = x0 + h;
        }
        return y;
    }
}
