package labs.lab3;
import labs.models.IFunc;
import labs.lab3.models.Separation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class TrapezoidIntegral {
    public static void execute(IFunc func) {
        Scanner scanner = new Scanner(System.in);
        double a, b, steps;
        while(true){
            System.out.println("Введите нижнюю границу:");
            a = Double.parseDouble(scanner.nextLine());
            System.out.println("Введите верхнюю границу:");
            b = Double.parseDouble(scanner.nextLine());
            System.out.println("Введите количество шагов:");
            steps = Double.parseDouble(scanner.nextLine());
            break;
        }
        solve(func, a, b, steps);
    }

    static void solve(IFunc func, double a, double b, double step_count){
        ArrayList<Separation> separations = findSeparations(func, a, b);

        double sum = 0;
        for (Separation separation : separations){
            Double result = integral(func, separation.getLeft(), separation.getRight(), step_count);
            sum += round(result, 8);
            System.out.println("Результат для промежутка["+String.format("%.8f",separation.getLeft())+","+String.format("%.8f",separation.getRight())+"]: " + String.format("%.8f", result));
            System.out.println("Погрешность: " + String.format("%.8f", Math.abs(result-integral(func, separation.getLeft(), separation.getRight(), step_count*2))));
        }
        System.out.println("Общая  площадь: " + String.format("%.8f", round(sum, 8)));
    }

    static ArrayList<Separation> findSeparations(IFunc func, double a, double b){
        ArrayList<Separation> array = new ArrayList<>();
        double eps = 0.00000001;
        int scale = 8; // Количество знаков после запятой.
        // Проверка первого элемента
        Double first = func.solve(round(a, scale));
        double left_now = first.isNaN() || first.isInfinite() ? a + eps : a;
        // Проверка элементов (a, b)
        if(a<=b){
            for(double i = a+0.0001; i < b; i+=0.0001){
                if (func.solve(round(i, scale)).isNaN() || func.solve(round(i, scale)).isInfinite()) {
                    array.add(new Separation(left_now, i-eps));
                    left_now = i+eps;
                }
            }
        }
        else{
            for(double i = a; i > b; i-=0.0001){
                if (func.solve(round(i, scale)).isNaN() || func.solve(round(i, scale)).isInfinite()) {
                    array.add(new Separation(left_now, i+eps));
                    left_now = i-eps;
                }
            }
        }
        // Проверка последнего элемента
        Double end = func.solve(round(b, scale));
        end = end.isNaN() || end.isInfinite() ? b - eps : b;
        array.add(new Separation(left_now, end));
        return array;
    }
    public static Double round(double x, int scale) {
        try {
            double rounded = (new BigDecimal(Double.toString(x))).setScale(scale, 4).doubleValue();
            return rounded == 0.0D ? 0.0D * x : rounded;
        } catch (NumberFormatException var6) {
            return Double.isInfinite(x) ? x : Double.NaN;
        }
    }
    static Double integral(IFunc func, double a, double b, double step_count) {
        double sum = 0, step;
        //Проверка шага
        if(step_count<0) { return null; }
        else if (0 == step_count) { return 0.0; }
        // Подсчет промежутка
        step = (b - a) / (step_count);
        //
        for (int i = 1; i < step_count; i++) {
            sum += func.solve(a + i * step);
        }
        //
        sum += (func.solve(a) + func.solve(b)) / 2;
        sum *= step;
        return sum;
    }
}
