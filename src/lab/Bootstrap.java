package lab;

import lab.modules.MenuModule;
import lab.сommands.*;

import java.util.ArrayList;

public class Bootstrap {
    public static void main(String[] args) {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new NonlinearEquationsMenu());
        commands.add(new SystemNonlinearEquations());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
