package labs.lab2.models;

import labs.interfaces.IFunc;
import labs.interfaces.ISysFunc;

import java.util.ArrayList;

public class SecondSysFunc implements ISysFunc {
    @Override
    public ArrayList<IFunc> getDraw() {
        ArrayList<IFunc> ar = new ArrayList<>();
        ar.add(this::g_y);
        ar.add(x -> (x>=-Math.pow(3, 0.5) && x<=Math.pow(3, 0.5)) ? -Math.abs(Math.pow(3-Math.pow(x,2), 0.5)) : null);
        ar.add(x -> (x>=-Math.pow(3, 0.5) && x<=Math.pow(3, 0.5)) ? Math.abs(Math.pow(3-Math.pow(x,2), 0.5)) : null);
        return ar;
    }
    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("x^2+y^2=3");
        ar.add("sin(x-0.6)-y-1.6");
        return ISysFunc.toString(ar);
    }

    @Override
    public double g_x(double y) { return Math.pow(3-Math.pow(y, 2), 0.5); }
    @Override
    public double g_y(double x) { return Math.sin(x-0.6)-1.6; }
    @Override
    public double f1(double x, double y) { return Math.pow(x,2)+Math.pow(y,2)-3; }
    @Override
    public double f2(double x, double y) { return Math.sin(x-0.6)-y-1.6; }
}
