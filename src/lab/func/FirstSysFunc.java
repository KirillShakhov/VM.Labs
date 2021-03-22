package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.ISysFunc;

import java.util.ArrayList;

public class FirstSysFunc implements ISysFunc, ICommand {
    @Override
    public ArrayList<Double> solve(double val1, double val2) {
        ArrayList<Double> ar = new ArrayList<>();
        ar.add(valStr1(val1, val2));
        ar.add(valStr2(val1, val2));
        return ar;
    }

    public static double valStr1(double val, double val1) {
        return val + val1 - 8;
    }
    public static double valStr2(double val, double val1) {
        return Math.pow(val, 2) + Math.pow(val1, 2) - 82;
    }

    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("x+y-8");
        ar.add("x^2+y^2-82");
        return ISysFunc.toString(ar);
    }

    @Override
    public void execute() {

    }
}
