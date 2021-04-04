package lab.modules;

import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;
import lab.models.lab1.Matrix;
import lab.models.lab1.Printer;
import lab.models.lab1.ResultSet;
import lab.models.lab2.Point;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class MathModule {
    private static Printer pr = new Printer();
    private static double[][] val;
    public MathModule() {
    }
    public static class Lab1{
        public static void findSolution_method_of_simple_iterations_SLAU(Matrix matrix, double eps){
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
    public static class Lab2 {
        public static void execute(IFunc func){
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
            if (MathModule.Lab2.doubChecker(func, left, right)) {
                ArrayList<Point> points = new ArrayList<>();
                double point1 = MathModule.Lab2.doubMetod(func, left, right, eps);
                double point2 = MathModule.Lab2.chordMethod(func, left, right, eps);
                if (pointChecker(left, right, point1)) {
                    pr.print("Результат метода деления пополам: "+point1);
                    points.add(new Point(point1, 0));
                }else{
                    pr.print("Результат метода деления пополам: решение не удовлетворяет заданному интервалу");
                }
                if (pointChecker(left, right, point2)) {
                    pr.print("Результат метода хорд: "+point2);
                    points.add(new Point(point2, 0));
                }else{
                    pr.print("Результат метода хорд: решение не удовлетворяет заданному интервалу");
                }
                new GraphModule(func, points, left, right);
            } else {
                pr.print("Уравнение решений не имеет в рамках данного диапазона/метода");
                new GraphModule(func, left, right);
            }
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

        public static double doubMetod(IFunc function, double left, double right, double eps) {
            if (function.solve(left) == 0) {
                return left;
            }
            if (function.solve(right) == 0) {
                return right;
            }
            double dx = 0, xi = 0;
            while (right - left > eps) {
                dx = (right - left) / 2.0;
                xi = left + dx;
                if (sign(function.solve(left)) != sign(function.solve(xi))) {
                    right = xi;
                } else {
                    left = xi;
                }
            }
            return xi;
        }

        public static double chordMethod(IFunc function, double left, double right, double eps) {
            while (Math.abs(right - left) > eps) {
                left = right - (right - left) * function.solve(right) / (function.solve(
                        right) - function.solve(left));
                right = left - (left - right) * function.solve(left) / (function.solve(left) - function.solve(right));
            }
            return right;
        }

        public static double chordMethod2(IFunc function, double left, double right, double eps) {
            double c = 0;
            while (Math.abs(function.solve(right) - function.solve(left)) > eps) {
                c = (function.solve(right) * left - function.solve(left) * right) / (function.solve(right) - function.solve(left));
                if ((function.solve(left) * function.solve(c)) > 0) left = c;
                else right = c;
            }
            return c;
        }

        public static double chordMethod3(IFunc function, double left, double right, double eps) {
            double t = 0;
            while (Math.abs(right - left) >= eps) {
                t = left + (function.solve(right) * (right - left)) / (function.solve(right) - function.solve(left));

                if (function.solve(left) * function.solve(t) < 0) {
                    right = t;
                } else if (function.solve(t) * function.solve(right) < 0) {
                    left = t;
                } else
                    return t;
            }
            return t;
        }

//    public static double chordMethod(IFunc function, double left, double right, double eps) {
//        while(Math.abs(function.solve(right)) > eps)
//        {
//            left = right - ((right - left) * function.solve(right))/(function.solve(right) - function.solve(left));
//            right = left - ((left - right) * function.solve(left))/(function.solve(left) - function.solve(right));
//        }
//        return right;
//    }


        private static int sign(double x) {
            if (x > 0)
                return 1;
            else if (x < 0)
                return -1;
            return 0;
        }

        public static double iterMetod(ISysFunc func, double eps) {
            double x = 0;
            for (double iter = 1; eps < Math.abs(func.solvef(x)); iter = iter + 1) {
                x = func.solveg(x);
            }
            return x;
        }

        static double f(double x) {
            return Math.pow(2.0, x) - 2 * Math.pow(x, 2.0) - 1;
            //y = 2^x-2x^2-1
        }

        public static double g(double x) {
            return x + 0.5 * f(x);
            //x + 0.5y
        }

        public static double iterMetod2(ISysFunc func, double x, double eps) {
            for (double iter = 1; eps < Math.abs(f(x)); iter = iter + 1) {
                //*Итераций может быть очень много, поэтому рекомендую забыть
                //о целых а использовать дабл как счётчик, хотя в принципе если
                //решение не нашли за 10-100 итераций то решения для данного коэффициента
                //при f(x) в g(x) нет и надо его менять
//            cout<<"Iteration : "<<setprecision(0)<<iter<<endl;
//            cout<<"x    = "<<x   <<endl;
//            cout<<"g(x) = "<<g(x)<<endl;
//            cout<<"f(x) = "<<f(x)<<endl;
                x = g(x);
            }
            return x;
        }

        public static boolean pointChecker(double left, double right, double point) {
            System.out.println(left + ":" + right + ":" + point);
            return point >= left && point <= right;
        }
    }
    public static class Lab3{
        public static void execute(IFunc func) {
            PrinterModule pr = new PrinterModule();
            Scanner scanner = new Scanner(System.in);
            double a = 0, b = 0;
            int step = 0;
            while(true){
                pr.print("Введите верхнюю границу:");
                a = Double.parseDouble(scanner.nextLine());
                pr.print("Введите правую границу:");
                b = Double.parseDouble(scanner.nextLine());
                if (a > b){
                    double t = a;
                    a = b;
                    b = t;
                }
                pr.print("Введите шаг:");
                step = Integer.parseInt(scanner.nextLine());
                break;
            }
            Double result = integral(func, a, b, step);
            if(result != null){
                System.out.println("Результат: " + result);
            }
            else{
                System.out.println("Решение не найдено");
            }
        }

        static Double integral(IFunc func, double a, double b, int step_count) {
            if(step_count<0){
                return null;
            }
            double sum = .0, step;
            if (0 == step_count) return sum;

            step = (b - a) / (1.0 * step_count);
            for (int i = 1 ; i < step_count ; ++i ) {
                sum += func.solve(a + i * step);
            }
            sum += (func.solve(a) + func.solve(b)) / 2;
            sum *= step;
            return sum;
        }
        // double f (double x) {
        // return 2 * x;
        // }
        //
        // int main() {
        // printf ("\\int_0^10(x) = %f\n", integral(f, 0, 10, 15));
        // return 0;
        // }
    }
}
