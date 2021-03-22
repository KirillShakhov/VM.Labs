package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;
import lab.modules.GraphModule;

import java.util.ArrayList;

public class FirstSysFunc implements ISysFunc, ICommand {
    @Override
    public ArrayList<Double> solve(double val1, double val2) {
        ArrayList<Double> ar = new ArrayList<>();
        ar.add(valStr1(val1, val2));
        ar.add(valStr2(val1, val2));
        return ar;
    }

    public double valStr1(double x, double y) {
        return x + y - 8;
    }
    public double valStr2(double x, double y) {
        return Math.pow(x, 2) + Math.pow(y, 2) - 82;
    }
    public double valGr1(double x){ return 8 - x; }
    public double valGr2(double x){ return Math.pow(82 - Math.pow(x, 2), 0.5); }
    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("x+y-8");
        ar.add("x^2+y^2-82");
        return ISysFunc.toString(ar);
    }

    @Override
    public void execute() {
        ArrayList<IFunc> ar = new ArrayList<>();
        double point1x = 0, point1y = 0, point2x = 0, point2y = 0, left = 0, right = 10;
        new GraphModule(this, point1x, point1y, point2x, point2y, left, right);
    }
}
