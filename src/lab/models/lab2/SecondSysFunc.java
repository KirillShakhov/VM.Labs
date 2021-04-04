package lab.models.lab2;
import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;
import java.util.ArrayList;

public class SecondSysFunc implements ISysFunc {
    @Override
    public double solvef(double x) {
        return Math.pow(x, 2)+Math.pow(solveg(x), 2) - 82;
    }
    @Override
    public double solveg(double x) {
        return 8 - x;
    }

    @Override
    public ArrayList<IFunc> getDraw() {
        ArrayList<IFunc> ar = new ArrayList<>();
        ar.add(x -> 8 - x);
        ar.add(x -> Math.pow(82 - Math.pow(x, 2), 0.5));
        return ar;
    }
    @Override
    public String getMessage() {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("x+y-8");
        ar.add("x^2+y^2-82");
        return ISysFunc.toString(ar);
    }
}
