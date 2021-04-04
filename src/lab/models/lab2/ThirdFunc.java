package lab.models.lab2;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;

public class ThirdFunc implements IFunc, ICommand {
    @Override
    public double solve(double val) {
        return Math.pow(val, 2) + (2 * val) + 1;
    }

    @Override
    public String getMessage() {
        return "x^2+2x+1";
    }

    @Override
    public void execute() {
        IFunc.execute(this);
    }
}
