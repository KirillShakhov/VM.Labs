package labs.lab3.models;
import labs.models.ICommand;
import labs.models.IFuncX;
import labs.lab3.TrapezoidIntegral;
import labs.modules.CalcModule;

import java.util.Scanner;

public class IndividualFuncX implements IFuncX, ICommand {
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
            TrapezoidIntegral.execute(this);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Произошла проблема.");
        }
    }
}
