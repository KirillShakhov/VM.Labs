package labs.сommands;
import labs.lab1.commands.SystemsOfLinearAlgebraicEquations;
import labs.lab2.commands.NonlinearEquationsMenu;
import labs.lab2.commands.SystemNonlinearEquations;
import labs.lab5.commands.CauchyProblem;
import labs.models.ICommand;
import labs.modules.MenuModule;
import labs.lab3.commands.Integration;
import labs.lab4.commands.LagrangeInterpolation;
import java.util.ArrayList;

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
        //
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
