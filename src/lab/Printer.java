package lab;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Printer {

    private final MathContext context = new MathContext(3, RoundingMode.HALF_UP);
    public Printer() {
    }

    public void printMatrix(Matrix matrix) {
        System.out.println("Матрица:");
        if (matrix != null) {
            for (int i = 0; i < matrix.getSize(); i++) {
                for (int j = 0; j < matrix.getSize() + 1; j++) {
                    System.out.print(new BigDecimal(matrix.getMatrix()[i][j], context));
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public void notDiagonalAll() {
        System.out.println("Отсутствие диагонального преобладания");
    }

    public void printResult(double [] result, int iterations, double [] localEps){

        System.out.println("Количество итераций:\n"+iterations);

        System.out.println("Ответ:");
        for (int i = 0; i < result.length;i++) {
            System.out.print("x"+i+": ");
            System.out.println(new BigDecimal(result[i], context));
        }

        System.out.print("Невязки:\n");
        for (int i = 0; i < result.length;i++) {
            System.out.print("x"+i+" +-");
            System.out.println(new BigDecimal(localEps[i], context));
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Printer";
    }
}
