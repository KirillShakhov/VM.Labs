package labs.lab5;
import labs.lab4.LagrangianIntegrationMath;
import labs.models.IFuncX;
import labs.models.IFuncXY;
import labs.models.Point;
import labs.modules.GraphModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RungeKuttaMethodMath {
    public static void solve(IFuncXY f) {
        // Пример дифференциального уравнения "dy / dx = (x - y) / 2"
        // Пример дифференциального уравнения "y'  = (x - y) / 2"
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
        Map<String, ArrayList<IFuncX>> map_func = new HashMap<>();
        Map<String, ArrayList<Point>> point_func = new HashMap<>();
        //Получение исходных данных
        ArrayList<Point> xy = rungeKuttaSolve(f, x0, y0, end, eps);
        for(Point p : xy){
            System.out.println("x: ["+p.getX()+"] y: ["+p.getY()+"]");
        }
        point_func.put("Исходные данные", xy);
        // Интерполяция
        ArrayList<Point> interpolation = LagrangianIntegrationMath.Interpolation(xy, 100);
        point_func.put("Интерполяция", interpolation);
        // Рисуем график
        new GraphModule(map_func, point_func);
    }

    public static ArrayList<Point> rungeKutta(IFuncXY f, double x0, double y0, double x_end, double h) {
        ArrayList<Point> points = new ArrayList<>();
        // Подсчитать количество итераций, используя размер шага или
        // высота шага h
        int n = (int) ((x_end - x0) / h);
        double k1, k2, k3, k4;
        // Итерация по количеству итераций
        double y = y0;
        points.add(new Point(x0, y));
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
            points.add(new Point(x0, y));
        }
        return points;
    }
    // Находит значение y для заданного x, используя размер шага h
    // и начальное значение y0 в x0.
    public static double rungeKutta_eps(IFuncXY f, double x0, double y0, double x_end, double h) {
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
    public static ArrayList<Point> rungeKuttaSolve(IFuncXY f, double x0, double y0, double x_end, double eps){
        ArrayList<Point> result;
        double eps_y;
        double h = 1;
        do {
            result = rungeKutta(f, x0, y0, x_end, h);
            eps_y = Math.abs(rungeKutta_eps(f, x0, y0, x_end, h)-rungeKutta_eps(f, x0, y0, x_end, h/2));
            h /= 2;
        }while(eps_y > eps);
        return result;
    }
}
