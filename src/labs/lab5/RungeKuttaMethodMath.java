package labs.lab5;
import labs.models.IFuncX;
import labs.models.IFuncXY;
import labs.models.Point;
import labs.modules.GraphModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static labs.lab4.LagrangianIntegrationMath.Extrapolation;
import static labs.lab4.LagrangianIntegrationMath.Interpolation;

public class RungeKuttaMethodMath {
    public static void solve(IFuncXY f) {
        //double x0 = 0, y = 1, x = 2, h = 0.2;
        // Пример дифференциального уравнения "dy / dx = (x - y) / 2"
        // IFuncXY f = (x1, y1) -> (x1 - y1) / 2;
        //f = (x1, y1) -> (x1 - y1) / 2;
        //System.out.println("The value of y at x is : " + rungeKutta(f, x0, y, x, h));

        //Задается ОбДифУр вида y’ + f(x,y) = 0 ,
        // пользователь задает начальные условия (x0, y0), конец отрезка и точность.
        double x0, y0, end, eps;
        while (true){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите x0:");
                x0 = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите y0:");
                y0 = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите конец отрезка:");
                end = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите точность:");
                eps = Double.parseDouble(scanner.nextLine());
                break;
            }catch (Exception ignored){}
        }
        /*
        Получение исходных данных
         */
        ArrayList<Point> xy = new ArrayList<>();
        for(double i = x0; i <= end; i++){
            xy.add(new Point(i, rungeKuttaSolve(f, x0, y0, i, eps)));
        }
        /*
        Формирование графика
         */
        Map<String, ArrayList<IFuncX>> map_func = new HashMap<>();
        Map<String, ArrayList<Point>> point_func = new HashMap<>();
        // Добавление функции на график
//        ArrayList<IFuncXY> funcs = new ArrayList<>();
//        funcs.add(f);
//        map_func.put("График функции", funcs);
        // Добавление точек исходных данных на график
        point_func.put("Точки исходных данных", xy);
        // Интерполяция
        ArrayList<Point> interpolation = Interpolation(xy, 10);
        point_func.put("Точки Интерполяции", interpolation);
        // Экстраполяция
        ArrayList<Point> extrapolation = Extrapolation(xy);
        point_func.put("Точки вне исходных данных, посчитанные Лагранжем", extrapolation);
        // Рисуем график
        new GraphModule(map_func, point_func);
    }

    // Находит значение y для заданного x, используя размер шага h
    // и начальное значение y0 в x0.
    public static double rungeKutta(IFuncXY f, double x0, double y0, double x_end, double h) {
        // Подсчитать количество итераций, используя размер шага или
        // высота шага h
        int n = (int) ((x_end - x0) / h);
        double k1, k2, k3, k4;
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
            // Обновляем следующее значение x
            x0 += h;
        }
        return y;
    }
    public static double rungeKuttaSolve(IFuncXY f, double x0, double y0, double x_end, double eps){
        double result, result_eps;
        double h = 1;
        do {
            result = rungeKutta(f, x0, y0, x_end, h);
            result_eps = rungeKutta(f, x0, y0, x_end, h/2);
            h /= 10;
        }while(result-result_eps > eps);
        return result;
    }
}
