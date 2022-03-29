package labs.lab2.models;
import labs.models.IFuncX;

import java.util.ArrayList;

public interface ISysFunc {
    ArrayList<IFuncX> getDraw();
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
