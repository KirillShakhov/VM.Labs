package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;

public class FourthFunc implements IFunc, ICommand{
    @Override
    public double solve(double val) {
        return Math.pow(Math.E, val) - 1;
    }

    @Override
    public String getMessage() {
        return "e^x-1";
    }

    @Override
    public void execute() {
        IFunc.execute(this);
    }
}
