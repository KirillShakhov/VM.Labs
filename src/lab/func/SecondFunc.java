package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;

public class SecondFunc implements IFunc, ICommand {
    @Override
    public double solve(double val) {
        return 3 * Math.pow(val, 2) - (14 * val) - 5;
    }

    @Override
    public String getMessage() {
        return "3x^2-14x-5";
    }

    @Override
    public void execute() {
        IFunc.execute(this);
    }
}
