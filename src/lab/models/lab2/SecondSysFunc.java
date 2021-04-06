package lab.models.lab2;

import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;

import java.util.ArrayList;

public class SecondSysFunc implements ISysFunc {
    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("sin(x-0.6)-y=1.6");
        ar.add("3x-cos(y)=0.9");
        return ISysFunc.toString(ar);
    }

    @Override
    public double g_x(double y) { return (0.9+Math.cos(y))/3; }
    @Override
    public double g_y(double x) { return Math.sin(x-0.6)-1.6; }
    @Override
    public double f1(double x, double y) { return Math.sin(x-0.6)-y-1.6; }
    @Override
    public double f2(double x, double y) { return 3*x-Math.cos(y)-0.9; }
}
