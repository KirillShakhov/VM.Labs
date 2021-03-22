package lab.interfaces;

import java.util.ArrayList;

public interface ISysFunc {
    ArrayList<Double> solve(double val1, double val2);
    static String toString(ArrayList<String> arrayList) {
        StringBuilder result = new StringBuilder("|");
        for(String it : arrayList){
            result.append(it).append("\n   |");
        }
        result.delete(result.length()-5, result.length());
        return result.toString();
    }
}
