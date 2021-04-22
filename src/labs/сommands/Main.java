package labs.сommands;
import labs.interfaces.ICommand;
import labs.modules.MenuModule;
import labs.сommands.integration.Integration;
import labs.сommands.interpolation.InterpolationLagrange;
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
        commands.add(new InterpolationLagrange());
        //
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
