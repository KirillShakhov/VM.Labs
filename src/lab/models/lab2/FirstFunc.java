package lab.models.lab2;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;

public class FirstFunc implements IFunc, ICommand {
    @Override
    public double solve(double val) {
        return Math.pow(val, 2) + val + 2;
    }

    @Override
    public String getMessage() {
        return "x^2+x+2";
    }

    @Override
    public void execute() {
        IFunc.execute(this);
    }
}
