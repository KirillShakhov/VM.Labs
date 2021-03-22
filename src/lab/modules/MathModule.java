package lab.modules;

import lab.interfaces.IFunc;

import static java.lang.Math.abs;

public class MathModule {
    private static PrinterModule pr = new PrinterModule();

    //    private static double[][] val;
    public MathModule() {
    }

    public static double doubMetod(IFunc function, double start_x, double end_x, double eps) {
        if (function.solve(start_x) == 0){
            return start_x;
        }
        if (function.solve(end_x) == 0){
            return end_x;
        }
        double dx = 0, xi = 0;
        while (end_x - start_x > eps) {
            dx = (end_x - start_x) / 2.0;
            xi = start_x + dx;
            if (sign(function.solve(start_x)) != sign(function.solve(xi))){
                end_x = xi;
            }
            else{
                start_x = xi;
            }
        }
        return xi;
    }
    private static int sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        return 0;
    }

//    private static boolean pointChecker(function, double left, double right, double point1, double point2) {
//        if (resultIntervalChecker(left, right, point1)) {
//            pr.print(String.valueOf(point1));
//        }
//        else{
//            pr.print("Решение не удовлетворяет заданному интервалу");
//            point1 = 0;
//        }
//        if (resultIntervalChecker(left, right, point2)) {
//            pr.print(String.valueOf(point2));
//        }
//        else{
//            pr.print("Решение не удовлетворяет заданному интервалу");
//            point2 = 0;
//        }
////        paintSolve(function, point1, point2);
//        return
//    }
    private static boolean pointChecker(double left, double right, double point) {
        return left <= point && point <= right;
    }


//    public static void findSolution(Matrix matrix, double eps){
//        pr.printMatrix(matrix);
//        if (checkDiagonal(matrix.getMatrix(),matrix.getSize())){
//            ResultSet rs = solve(matrix, eps);
//            pr.print(rs.getTable());
//            pr.printVector("Решение системы: ", rs.getResult());
//            pr.printVector("Вектор невязки: ", rs.getResiduals());
//            return;
//        }
//        permuteMatrixHelper(matrix,0);
//        if (val!= null){
//            Matrix matrix1 = new Matrix(val);
//            pr.print("Матрица после перестановки строк");
//            pr.printMatrix(matrix1);
//            ResultSet rs = solve(matrix1, eps);
//            pr.print(rs.getTable());
//            pr.printVector("Решение системы: ", rs.getResult());
//            pr.printVector("Вектор невязки: ", rs.getResiduals());
//        } else {
//            pr.notDiagonalAll();
//        }
//    }
//
//    public static boolean checkDiagonal(double[][] matrix, int size) {
//        int i, j, k = 1;
//        double sum = 0;
//        for (i = 0; i < size;i++) {
//            sum = 0;
//            for (j = 0; j < size;j++) {
//                sum+= abs(matrix[i][j]);
//            }
//            sum -= abs(matrix[i][i]);
//            if (sum >= abs(matrix[i][i])) {
//                k = 0;
//            }
//        }
//        return (k == 1);
//    }
//
//    private static void permuteMatrixHelper(Matrix matrix, int index) {
//        if(index >= matrix.getMatrix().length - 1){
//            if (checkDiagonal(matrix.getMatrix(), matrix.getSize())){
//                val = new double[matrix.getSize()][matrix.getSize()+1];
//                for (int i = 0; i < matrix.getSize();i++){
//                    for (int j = 0; j < matrix.getSize()+1;j++){
//                        val[i][j] = matrix.getMatrix()[i][j];
//                    }
//                }
//            }
//            return;
//        } else {
//            for (int i = index; i < matrix.getMatrix().length; i++) {
//                double[] t = matrix.getMatrix()[index];
//                matrix.getMatrix()[index] = matrix.getMatrix()[i];
//                matrix.getMatrix()[i] = t;
//
//                permuteMatrixHelper(matrix, index + 1);
//
//                t = matrix.getMatrix()[index];
//                matrix.getMatrix()[index] = matrix.getMatrix()[i];
//                matrix.getMatrix()[i] = t;
//            }
//        }
//    }
//
//    private static ResultSet solve(Matrix matrix, double eps) {
//        ResultSet rs = new ResultSet();
//        double[] x= new double[matrix.getSize()];
//        double norma = 0, sum, t;
//        do
//        {
//            ArrayList<Double> esps = new ArrayList<>();
//            norma = 0;
//            //  k++;
//            for(int i = 0; i < matrix.getSize(); i++)
//            {
//                t = x[i];
//                sum = 0;
//
//                for(int j = 0; j < matrix.getSize(); j++)
//                {
//                    if(j != i)
//                        sum += matrix.getMatrix()[i][j] * x[j];
//                }
//                x[i] = (matrix.getVector()[i] - sum) / matrix.getMatrix()[i][i];
//                esps.add(abs(x[i] - t));
//                if (abs(x[i] - t) > norma)
//                    norma = abs(x[i] - t);
//            }
//            rs.addIter(x);
//            rs.addE(esps);
//        }
//        while(norma > eps);
//
//
//        rs.setResult(x);
//
//        //Проверка
//        ArrayList<Double> residuals = new ArrayList<>();
//        for(int i = 0; i < matrix.getSize(); i++)
//        {
//            float S=0;
//            for(int j = 0; j < matrix.getSize(); j++)
//            {
//                S += matrix.getMatrix()[i][j] * x[j] ;
//            }
//            residuals.add(S - matrix.getVector()[i]);
//        }
//        rs.setResiduals(residuals);
//        return rs;
//    }
}
