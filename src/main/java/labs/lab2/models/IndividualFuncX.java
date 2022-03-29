package labs.lab2.models;
import labs.models.ICommand;
import labs.models.IFuncX;
import labs.modules.CalcModule;
import java.util.Scanner;
import labs.lab2.MathModuleLab2;

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
            System.out.println("Пример уравнения: x^2-cos(x)-x\nВведите уравнение:");
            this.xyz = scanner.nextLine();
            MathModuleLab2.execute(this);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Произошла проблема.");
        }
    }
}
