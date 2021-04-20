package lab.modules;
import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;
import lab.models.lab1.Matrix;
import lab.models.lab1.ResultSet;
import lab.models.lab2.Point;
import lab.models.lab2.ResultSetForSys;
import lab.models.lab3.Separation;
import org.apache.commons.math3.util.Precision;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.abs;

public class MathModule {
    public static class Lab1{
        private static PrinterModule pr = new PrinterModule();
        private static double[][] val;
        public static void execute(Matrix matrix, double eps){
            pr.printMatrix(matrix);
            if (checkDiagonal(matrix.getMatrix(),matrix.getSize())){
                ResultSet rs = method_of_simple_iterations_SLAU(matrix, eps);
                pr.print(rs.getTable());
                pr.printVector("Решение системы: ", rs.getResult());
                pr.printVector("Вектор невязки: ", rs.getResiduals());
                return;
            }
            permuteMatrixHelper(matrix,0);
            if (val!= null){
                Matrix matrix1 = new Matrix(val);
                pr.print("Матрица после перестановки строк");
                pr.printMatrix(matrix1);
                ResultSet rs = method_of_simple_iterations_SLAU(matrix1, eps);
                pr.print(rs.getTable());
                pr.printVector("Решение системы: ", rs.getResult());
                pr.printVector("Вектор невязки: ", rs.getResiduals());
            } else {
                pr.notDiagonalAll();
            }
        }

        public static boolean checkDiagonal(double[][] matrix, int size) {
            int i, j, k = 1;
            double sum = 0;
            for (i = 0; i < size;i++) {
                sum = 0;
                for (j = 0; j < size;j++) {
                    sum+= abs(matrix[i][j]);
                }
                sum -= abs(matrix[i][i]);
                if (sum >= abs(matrix[i][i])) {
                    k = 0;
                }
            }
            return (k == 1);
        }

        private static void permuteMatrixHelper(Matrix matrix, int index) {
            if(index >= matrix.getMatrix().length - 1){
                if (checkDiagonal(matrix.getMatrix(), matrix.getSize())){
                    val = new double[matrix.getSize()][matrix.getSize()+1];
                    for (int i = 0; i < matrix.getSize();i++){
                        for (int j = 0; j < matrix.getSize()+1;j++){
                            val[i][j] = matrix.getMatrix()[i][j];
                        }
                    }
                }
                return;
            } else {
                for (int i = index; i < matrix.getMatrix().length; i++) {
                    double[] t = matrix.getMatrix()[index];
                    matrix.getMatrix()[index] = matrix.getMatrix()[i];
                    matrix.getMatrix()[i] = t;

                    permuteMatrixHelper(matrix, index + 1);

                    t = matrix.getMatrix()[index];
                    matrix.getMatrix()[index] = matrix.getMatrix()[i];
                    matrix.getMatrix()[i] = t;
                }
                pr.printMatrix(matrix);
            }
        }

        private static ResultSet method_of_simple_iterations_SLAU(Matrix matrix, double eps) {
            ResultSet rs = new ResultSet();
            double[] x= new double[matrix.getSize()];
            double norma = 0, sum, t;
            do
            {
                ArrayList<Double> esps = new ArrayList<>();
                norma = 0;
                //  k++;
                for(int i = 0; i < matrix.getSize(); i++)
                {
                    t = x[i];
                    sum = 0;

                    for(int j = 0; j < matrix.getSize(); j++)
                    {
                        if(j != i)
                            sum += matrix.getMatrix()[i][j] * x[j];
                    }
                    x[i] = (matrix.getVector()[i] - sum) / matrix.getMatrix()[i][i];
                    esps.add(abs(x[i] - t));
                    if (abs(x[i] - t) > norma)
                        norma = abs(x[i] - t);
                }
                rs.addIter(x);
                rs.addE(esps);
            }
            while(norma > eps);


            rs.setResult(x);

            //Проверка
            ArrayList<Double> residuals = new ArrayList<>();
            for(int i = 0; i < matrix.getSize(); i++)
            {
                float S=0;
                for(int j = 0; j < matrix.getSize(); j++)
                {
                    S += matrix.getMatrix()[i][j] * x[j] ;
                }
                residuals.add(S - matrix.getVector()[i]);
            }
            rs.setResiduals(residuals);
            return rs;
        }
    }
    public static class  Lab2 {
        public static void execute(IFunc func){
            //Для нелинейных уравнений
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
                while(true){
                    pr.print("Введите точность:");
                    eps = Double.parseDouble(scanner.nextLine());
                    if(eps>0 && eps < 1){
                        break;
                    }
                    else{
                        pr.print("Точность должна быть больше 0 и меньше 1.");
                    }
                }
                break;
            }

            ArrayList<Point> points = new ArrayList<>();
            double point1, point2;
            if (MathModule.Lab2.doubChecker(func, left, right)) {
                point1 = MathModule.Lab2.doubMetod(func, left, right, eps);
                if (pointChecker(left, right, point1)) {
                    pr.print("Результат метода деления пополам: "+point1);
                    points.add(new Point(point1, 0));
                }else{
                    pr.print("Результат метода деления пополам: решение не удовлетворяет заданному интервалу");
                }
            } else {
                pr.print("Уравнение не имеет решений методом деления пополам.");
            }
            if (MathModule.Lab2.cordChecker(func, left, right)) {
                point2 = MathModule.Lab2.chordMethod(func, left, right, eps);
                if (pointChecker(left, right, point2)) {
                    pr.print("Результат метода хорд: "+point2);
                    points.add(new Point(point2, 0));
                }else{
                    pr.print("Результат метода хорд: решение не удовлетворяет заданному интервалу");
                }
            } else {
                pr.print("Уравнение не имеет решений методом хорд.");
            }
            new GraphModule(func, points, left, right);
        }

        private static boolean cordChecker(IFunc func, double left, double right) {
            return true;
        }

        public static void execute(ISysFunc func){
            // для систем нелинейных уравнений
            PrinterModule pr = new PrinterModule();
            Scanner scanner = new Scanner(System.in);
            double eps, x, y;
            while(true){
                pr.print("Введите приближение x:");
                x = Double.parseDouble(scanner.nextLine());
                pr.print("Введите приближение y:");
                y = Double.parseDouble(scanner.nextLine());
                while(true){
                    pr.print("Введите точность:");
                    eps = Double.parseDouble(scanner.nextLine());
                    if(eps>0 && eps < 1){
                        break;
                    }
                    else{
                        pr.print("Точность должна быть больше 0 и меньше 1.");
                    }
                }
                break;
            }
            ArrayList<Point> points = new ArrayList<>();
            ResultSetForSys result = MathModule.Lab2.iterMetod(func, x, y, eps);
            points.add(result.getPoint());
            result.print();
            new GraphModule(func.getDraw(), points, -10, 10);
        }

        public static boolean doubChecker(IFunc function, double left, double right) {
            boolean value = false;
            for (double i = left; i < right; i += 0.5) {
                for (double j = left; j < right; j += 0.5) {
                    if (function.solve(i) * function.solve(j) <= 0) {
                        value = true;
                    }
                }
            }
            return value;
        }
        //Выбирается начальное приближение к отрезку [left, right], такое, что f(left)×f(right)<0
        public static double doubMetod(IFunc function, double left, double right, double eps) {
            double dx = 0, xi = 0;
            if (function.solve(left) == 0) {
                return left;
            }
            if (function.solve(right) == 0) {
                return right;
            }
            while (right - left > eps) {
                dx = (right - left) / 2.0;
                xi = left + dx;
                if (sign(function.solve(left)) == sign(function.solve(xi))) left = xi;
                else right = xi;
            }
            return xi;
        }
        private static int sign(double x) {
            if (x > 0) return 1;
            else if (x < 0) return -1;
            return 0;
        }

        public static double chordMethod(IFunc function, double left, double right, double eps) {
            while (Math.abs(right - left) > eps) {
                left = right - (right - left) * function.solve(right) / (function.solve(
                        right) - function.solve(left));
                right = left - (left - right) * function.solve(left) / (function.solve(left) - function.solve(right));
            }
            return right;
        }

        public static ResultSetForSys iterMetod(ISysFunc func, double x, double y, double eps) {
            ResultSetForSys result = new ResultSetForSys();
            double x0=x,y0=y,d1,d2;
            int i = 1;
            do
            {
                x= func.g_x(y0);
                y= func.g_y(x0);
                d1=func.f1(x, y);
                d2=func.f2(x, y);
                x0=x;
                y0=y;
                result.addIter(x, y, Math.abs(d1), Math.abs(d2));
            }while(Math.abs(d1)>eps || Math.abs(d2)>eps);
            result.setPoint(new Point(x, y));
            return result;
        }

        public static boolean pointChecker(double left, double right, double point) {
            return point >= left && point <= right;
        }
    }
    public static class Lab3{
        public static void execute(IFunc func) {
            PrinterModule pr = new PrinterModule();
            Scanner scanner = new Scanner(System.in);
            double a, b, steps;
            while(true){
                pr.print("Введите нижнюю границу:");
                a = Double.parseDouble(scanner.nextLine());
                pr.print("Введите верхнюю границу:");
                b = Double.parseDouble(scanner.nextLine());
                pr.print("Введите количество шагов:");
                steps = Double.parseDouble(scanner.nextLine());
                break;
            }
            solve(func, a, b, steps);
        }

        static void solve(IFunc func, double a, double b, double step_count){
            ArrayList<Separation> separations = findSeparation(func, a, b);

            double sum = 0;
            for (Separation separation : separations){
                double result = integral(func, separation.getLeft(), separation.getRight(), step_count);
                sum += Precision.round(result, 8);
                System.out.println("Результат для промежутка["+String.format("%.8f",separation.getLeft())+","+String.format("%.8f",separation.getRight())+"]: " + String.format("%.8f",integral(func, separation.getLeft(), separation.getRight(), step_count)));
                System.out.println("Погрешность: " + String.format("%.8f", Math.abs(integral(func, separation.getLeft(), separation.getRight(), step_count)-integral(func, separation.getLeft(), separation.getRight(), step_count*2))));
            }
            System.out.println("Общая  площадь: " + Precision.round(sum, 8));
        }

        static ArrayList<Separation> findSeparation(IFunc func, double a, double b){
            // TODO доделать для промежутка [0, 5], где точка 0 разрыв и для [0, 5], где точка 5 разрыв.
            ArrayList<Separation> array = new ArrayList<>();
            double eps = 0.00000001;
            int scale = 8; // Количество знаков после запятой.
            if(a<=b){
                double left_now = a;
                for(double i = a; i <= b; i+=0.0001){
                    if (func.solve(round(i, scale)).isNaN() || func.solve(round(i, scale)).isInfinite()) {
//                    System.out.println("Разрыв в точке: " + Precision.round(i, 8));
                        array.add(new Separation(left_now, i-eps));
                        left_now = i+eps;
                    }
                }
                array.add(new Separation(left_now, b));
            }
            else{
                double left_now = a;
                for(double i = a; i >= b; i-=0.0001){
                    if (func.solve(round(i, scale)).isNaN() || func.solve(round(i, scale)).isInfinite()) {
//                    System.out.println("Разрыв в точке: " + Precision.round(i, 8));
                        array.add(new Separation(left_now, i+eps));
                        left_now = i-eps;
                    }
                    else if(round(i, scale)==b){
                        array.add(new Separation(left_now, i));
                    }
                }
            }
            return array;
        }
        public static double round(double x, int scale){
            return round(x,scale,4);
        }
        public static double round(double x, int scale, int roundingMethod) {
            try {
                double rounded = (new BigDecimal(Double.toString(x))).setScale(scale, roundingMethod).doubleValue();
                return rounded == 0.0D ? 0.0D * x : rounded;
            } catch (NumberFormatException var6) {
                return Double.isInfinite(x) ? x : 0.0D / 0.0;
            }
        }

        static Double integral(IFunc func, double a, double b, double step_count) {
            double sum = 0, step;
            //Проверка шага
            if(step_count<0) { return null; }
            else if (0 == step_count) { return 0.0; }

            // Подсчет промежутка
            step = (b - a) / (step_count);


            for (int i = 1; i < step_count; i++) {
                sum += func.solve(a + i * step);
            }


            sum += (func.solve(a) + func.solve(b)) / 2;
            sum *= step;
            return sum;
        }
    }
}
