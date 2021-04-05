package lab.сommands;

import lab.interfaces.ISysFunc;
import lab.models.lab2.FirstSysFunc;
import lab.interfaces.ICommand;
import lab.modules.MathModule;
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
        ArrayList<ISysFunc> sysFuncs = new ArrayList<>();


        sysFuncs.add(new FirstSysFunc());


        for (ISysFunc func : sysFuncs){
            commands.add(new ICommand() {
                @Override
                public String getMessage() {
                    return func.getMessage();
                }
                @Override
                public void execute() {
                    MathModule.Lab2.execute(func);
                }
            });
        }
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
