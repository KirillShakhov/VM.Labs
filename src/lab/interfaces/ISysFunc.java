package lab.interfaces;

import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.ArrayList;
import java.util.Scanner;

public interface ISysFunc {
    double solvef(double x);
    double solveg(double x);
    ArrayList<IFunc> getDraw();
    static String toString(ArrayList<String> arrayList) {
        StringBuilder result = new StringBuilder("|");
        for(String it : arrayList){
            result.append(it).append("\n   |");
        }
        result.delete(result.length()-5, result.length());
        return result.toString();
    }
    String getMessage();
}
