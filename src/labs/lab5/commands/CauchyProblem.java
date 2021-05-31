package labs.lab5.commands;
import labs.models.ICommand;
import labs.modules.MenuModule;
import java.util.ArrayList;

public class CauchyProblem implements ICommand {
    @Override
    public String getMessage() {
        return "Решение ОДУ(задача Коши)";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new RungeKuttaMethod());
        //
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
