package lab;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

public class MathModule {
    private static Printer pr = new Printer();
    private static double[][] val;
    public MathModule() {
    }

    public static void findSolution(Matrix matrix, double eps){
        if (checkDiagonal(matrix.getMatrix(),matrix.getSize())){
            ResultSet rs = solve(matrix, eps);
//            System.out.println(rs.getTable());
            return;
        }
        permuteMatrixHelper(matrix,0);
        if (val!= null){
            Matrix matrix1 = new Matrix(val);
            pr.printMatrix(matrix1);
            ResultSet rs = solve(matrix1, eps);
//            System.out.println(rs.getTable());
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
        }
    }

    private static void solveGause(double[][] matrix, int size, double esp) {
        int countIter = 0;
        double [] pogr = new double[size];
        double[] previousValues = new double[size];
        Arrays.fill(previousValues, 0.0);
        double error;
        while (true) {
            double[] values = new double[size];
            for (int i = 0; i < size; i++) {
                values[i] = matrix[i][size];
                for (int j = 0; j < size; j++) {
                    if (j < i) {
                        values[i] = values[i] - matrix[i][j] * values[j];
                    }
                    if (j > i) {
                        values[i] = values[i] - matrix[i][j] * previousValues[j];
                    }
                }
                values[i] = values[i] / matrix[i][i];
            }
            error = 0.0;
            for (int i = 0; i < size; i++) {
                error = error + abs(values[i] - previousValues[i]);
                pogr[i] = abs(values[i] - previousValues[i]);
            }
            if (error < esp) {
                break;
            }
            previousValues = values;
            countIter++;

        }
        pr.printResult(previousValues,countIter,pogr);
    }

    private static ResultSet solve(Matrix matrix, double eps) {
        double[] x= new double[matrix.getSize()];
        double norma = 0, sum, t;
        do
        {
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
                if (abs(x[i] - t) > norma)
                    norma = abs(x[i] - t);
                System.out.println("x[" + (i+1) +"] = " + x[i]);
            }
        }
        while(norma > eps);
        System.out.println("Решение системы:");

        for(int i = 0; i < matrix.getSize(); i++)
        {
            System.out.println("x[" + (i+1) +"] = " + x[i]);
        }

        //Проверка
        System.out.println("Вектор невязки:");
        float S=0;
        double[] r = new double[matrix.getSize()];
        for(int i = 0; i < matrix.getSize(); i++)
        {
            for(int j = 0; j < matrix.getSize(); j++)
            {
                S += matrix.getMatrix()[i][j] * x[j] ;
            }
            r[i] = S - matrix.getVector()[i];
            System.out.println("r[" + (i+1) +"] = " + r[i]);

            S=0;
        }
        return null;
    }

    private static ResultSet solveold(Matrix matrix, double eps){
        ResultSet resultSet = new ResultSet();
        pr.printMatrix(matrix);
        double[] result = new double[matrix.getSize()];
        for (int i = 0; i < matrix.getSize(); i++)
        {
            result[i] = matrix.getVector()[i] / matrix.getMatrix()[i][i];
        }
        double[] Xn = new double[matrix.getSize()];

        int iterator = 0;
        do {
            for (int i = 0; i < matrix.getSize(); i++) {
                Xn[i]=  matrix.getVector()[i] / matrix.getMatrix()[i][i];
                for (int j = 0; j < matrix.getSize(); j++) {
                    if (i == j)
                        continue;
                    else {
                        Xn[i] = matrix.getMatrix()[i][j] / matrix.getMatrix()[i][i] * result[j];
                    }
                }
            }

            boolean flag = true;
            for (int i = 0; i < matrix.getSize() - 1; i++) {
                if (abs(Xn[i] - result[i]) > eps) {
                    flag = false;
                    break;
                }
            }

            for (int i = 0; i < matrix.getSize(); i++) {
                result[i] = Xn[i];
            }
            iterator++;
            resultSet.addIter(Xn);
            if (flag || iterator>100) break;
        } while (true);

        System.out.println("Количество итераций:"+iterator);

        resultSet.setResult(result);

        //Проверка
        ArrayList<Double> disp = new ArrayList<>();
//      System.out.println("Вектор невязки:");
        float S=0;
        for(int i = 0; i < matrix.getSize(); i++)
        {
            for(int j = 0; j < matrix.getSize(); j++)
            {
                S += matrix.getMatrix()[i][j] * result[j];
            }
//          System.out.println("delta x" + (i + 1) + " = " + (S - matrix.getVector()[i]));
            disp.add(S - matrix.getVector()[i]);
            S=0;
        }
        resultSet.setDiscrepancies(disp);
        return resultSet;
    }

    public double correctEps(double eps){
        if (eps < 0 || eps > 1) eps = 0.1;
        return eps;
    }

}
