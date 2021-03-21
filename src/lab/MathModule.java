package lab;

import java.util.Arrays;

public class MathModule {
    private static Printer pr = new Printer();
    private static double[][] val;
    public MathModule() {
    }

    public static void findSolution(Matrix matrix, double eps){
        if (checkDiagonal(matrix.getMatrix(),matrix.getSize())){
            solve(matrix.getMatrix(),matrix.getSize(),eps);
            return;
        }
        permuteMatrixHelper(matrix,0);
        if (val!= null){
            Matrix matrix1 = new Matrix(val);
            pr.printMatrix(matrix1);
            solve(matrix1.getMatrix(),matrix1.getSize(),eps);
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
                sum+=Math.abs(matrix[i][j]);
            }
            sum -= Math.abs(matrix[i][i]);
            if (sum >= Math.abs(matrix[i][i])) {
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

    private static void solve(double[][] matrix, int size, double esp) {
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
                error = error + Math.abs(values[i] - previousValues[i]);
                pogr[i] = Math.abs(values[i] - previousValues[i]);
            }
            if (error < esp) {
                break;
            }
            previousValues = values;
            countIter++;

        }
        pr.printResult(previousValues,countIter,pogr);
    }

    public double correctEps(double eps){
        if (eps < 0 || eps > 1) eps = 0.1;
        return eps;
    }

}
