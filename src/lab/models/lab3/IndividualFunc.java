package lab.models.lab3;
import lab.interfaces.ICommand;
import lab.interfaces.IFunc;
import lab.modules.CalcModule;
import lab.modules.MathModule;

import java.util.Scanner;

public class IndividualFunc implements IFunc, ICommand {
    private String xyz;
    @Override
    public Double solve(double x) {
        CalcModule calcModule = new CalcModule(xyz);
        return calcModule.execute(x);
    }

    @Override
    public String getMessage() {
        return "Ввести собственное уравнение";
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пример уравнения: 2*x\nВведите уравнение:");
            this.xyz = scanner.nextLine();
            MathModule.Lab3.execute(this);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Произошла проблема.");
        }
    }
}
