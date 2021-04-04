package lab.сommands;

import lab.func.FirstSysFunc;
import lab.func.SecondSysFunc;
import lab.interfaces.ICommand;
import lab.modules.MenuModule;

import java.util.ArrayList;

public class SystemNonlinearEquations implements ICommand {
    @Override
    public String getMessage() {
        return "Решение систем нелинейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new FirstSysFunc());
        commands.add(new SecondSysFunc());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
