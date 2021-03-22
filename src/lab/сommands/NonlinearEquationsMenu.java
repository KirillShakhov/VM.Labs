package lab.сommands;

import lab.func.*;
import lab.interfaces.ICommand;
import lab.modules.MenuModule;

import java.util.ArrayList;

public class NonlinearEquationsMenu implements ICommand {
    @Override
    public String getMessage() {
        return "Решение нелинейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new FirstFunc());
        commands.add(new SecondFunc());
        commands.add(new ThirdFunc());
        commands.add(new FourthFunc());
        commands.add(new Test());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
