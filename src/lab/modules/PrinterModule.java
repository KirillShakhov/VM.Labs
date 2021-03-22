package lab.modules;

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
        for(int i = 0; i > list.size(); i++){
            System.out.println("["+(i+1)+"] = "+list.get(i));
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
