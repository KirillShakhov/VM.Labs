package lab;

import lab.Commands.*;

import java.util.ArrayList;

public class Bootstrap {
    public static void main(String[] args) {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new ConsoleCommand());
        commands.add(new FileCommand());
        commands.add(new RandomCommand());
        commands.add(new DiagonalRandomCommand());
        commands.add(new TestCommand());
        Menu menu = new Menu(commands);
        menu.execute();
    }
}
