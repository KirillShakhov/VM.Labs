package lab.models.lab2;

import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;

import java.util.ArrayList;

public class SecondSysFunc implements ISysFunc {
    @Override
    public ArrayList<IFunc> getDraw() {
        ArrayList<IFunc> ar = new ArrayList<>();
        ar.add(this::g_y);
        ar.add(x -> (x>=-2 && x<=2) ? -Math.abs(Math.pow(9*(1-(Math.pow(x, 2)/4)), 0.5)) : null);
        ar.add(x -> (x>=-2 && x<=2) ? Math.abs(Math.pow(9*(1-(Math.pow(x, 2)/4)), 0.5)) : null);
        return ar;
    }

    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("3x - y = -2");
        ar.add("x^2/4 + y^2/9 = 1");
        return ISysFunc.toString(ar);
    }

    @Override
    public double g_x(double y) { return Math.pow(4*(1-(Math.pow(y, 2)/9)), 0.5); }
    @Override
    public double g_y(double x) { return 3*x+2; }
    @Override
    public double f1(double x, double y) { return 3*x-y+2; }
    @Override
    public double f2(double x, double y) { return (Math.pow(x, 2)/4)+(Math.pow(y, 2)/9)-1; }
}
