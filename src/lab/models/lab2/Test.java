package lab.models.lab2;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;
import lab.modules.GraphModule;
import lab.modules.MathModule;

public class Test implements IFunc, ICommand {
    @Override
    public double solve(double val) {
        return Math.pow(val, 2) + val + 2;
    }

    @Override
    public String getMessage() {
        return "test";
    }

    @Override
    public void execute() {
        double left = -10, right = 10, eps = 0.001;
        double point1 = MathModule.doubMetod(this, left, right, eps);
        double point2 = MathModule.doubMetod(this, left, right, eps);
        new GraphModule(this, new Point(point1, 0), new Point(point2, 0), left, right);
    }
}
