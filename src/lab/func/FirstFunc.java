package lab.func;

import lab.interfaces.ICommand;
import lab.interfaces.IFunc;
import lab.modules.GraphModule;
import lab.modules.MathModule;
import lab.modules.PrinterModule;

import java.util.Scanner;

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
