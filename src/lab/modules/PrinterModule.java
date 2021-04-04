package lab.modules;

import lab.models.lab1.Matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class PrinterModule {

    private final MathContext context = new MathContext(3, RoundingMode.HALF_UP);
    public PrinterModule() {
    }
    public void print(String message) {
        System.out.println(message);
    }


    public void printVector(String message, ArrayList<Double> list){
        System.out.println(message);
        for(int i = 0; i < list.size(); i++){
            System.out.println("["+(i+1)+"] = "+String.format("%.15f",list.get(i)));
        }
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
