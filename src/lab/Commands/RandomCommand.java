package lab.Commands;

import lab.Matrix;

public class RandomCommand implements ICommand{
    @Override
    public String getMessage() {
        return "Генерация рандомной матрицы";
    }

    @Override
    public void execute() {

    }

    public Matrix createRandomMatrix(int size) {
        try{
            if (size > 20 || size <= 0) {
                throw new Exception();
            }
            double[][] matrix = new double[size][size + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length + 1; j++) {
                    matrix[i][j] = Math.random() * 50 - 25;
                }
            }
            return new Matrix(matrix);
        } catch (Exception e) {
            System.out.println("Введена неверная размерность");
        }
        return null;
    }
}
