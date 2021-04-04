package lab.сommands;

import lab.interfaces.ICommand;
import lab.models.lab1.*;
import lab.modules.MenuModule;

import java.util.ArrayList;

public class SystemsOfLinearAlgebraicEquations implements ICommand {
    @Override
    public String getMessage() {
        return "Решение систем линейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new ConsoleCommand());
        commands.add(new FileCommand());
        commands.add(new RandomCommand());
        commands.add(new DiagonalRandomCommand());
        commands.add(new TestCommand());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
