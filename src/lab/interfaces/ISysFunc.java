package lab.interfaces;

import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.ArrayList;
import java.util.Scanner;

public interface ISysFunc {
//    ArrayList<IFunc> getDraw();
    static String toString(ArrayList<String> arrayList) {
        StringBuilder result = new StringBuilder("|");
        for(String it : arrayList){
            result.append(it).append("\n   |");
        }
        result.delete(result.length()-5, result.length());
        return result.toString();
    }
    String getMessage();
    double g_x(double y);
    double g_y(double x);
    double f1(double x, double y);
    double f2(double x, double y);
}
