package lab.сommands;


import lab.interfaces.ICommand;
import lab.modules.MenuModule;

import java.util.ArrayList;

public class NumericalIntegration implements ICommand {
    @Override
    public String getMessage() {
        return "Численное интегрирование";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
//        commands.add(new FirstFunc());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
