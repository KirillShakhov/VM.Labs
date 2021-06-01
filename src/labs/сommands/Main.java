package labs.сommands;
import labs.models.ICommand;
import labs.modules.MenuModule;
import java.util.ArrayList;
import labs.lab1.commands.SystemsOfLinearAlgebraicEquations;
import labs.lab2.commands.NonlinearEquationsMenu;
import labs.lab2.commands.SystemNonlinearEquations;
import labs.lab3.commands.Integration;
import labs.lab4.commands.LagrangeInterpolation;
import labs.lab5.commands.CauchyProblem;
import labs.lab6.commands.SweepMethod;

public class Main implements ICommand {
    @Override
    public String getMessage() {
        return "Назад <--";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        //Lab1
        commands.add(new SystemsOfLinearAlgebraicEquations());
        //Lab2
        commands.add(new NonlinearEquationsMenu());
        commands.add(new SystemNonlinearEquations());
        //Lab3
        commands.add(new Integration());
        //Lab4
        commands.add(new LagrangeInterpolation());
        //Lab5
        commands.add(new CauchyProblem());
        //Lab6
        commands.add(new SweepMethod());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
