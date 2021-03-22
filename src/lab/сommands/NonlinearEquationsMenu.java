package lab.сommands;

import lab.modules.MenuModule;

import java.util.ArrayList;

public class NonlinearEquationsMenu implements ICommand{
    @Override
    public String getMessage() {
        return "Решение нелинейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
//        commands.add();
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
