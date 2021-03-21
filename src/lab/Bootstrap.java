package lab;

import lab.Commands.ConsoleCommand;
import lab.Commands.FileCommand;
import lab.Commands.ICommand;
import java.util.ArrayList;

public class Bootstrap {
    public static void main(String[] args) {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new ConsoleCommand());
        commands.add(new FileCommand());
        Menu menu = new Menu(commands);
        menu.execute();
    }
}
