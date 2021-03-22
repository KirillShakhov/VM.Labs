package lab.сommands;

import lab.modules.MenuModule;

import java.util.ArrayList;

public class SystemNonlinearEquations implements ICommand{
    @Override
    public String getMessage() {
        return "Решение систем нелинейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
//        commands.add();
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
