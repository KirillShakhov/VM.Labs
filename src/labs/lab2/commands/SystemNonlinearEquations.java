package labs.lab2.commands;
import java.util.ArrayList;
import labs.models.ICommand;
import labs.lab2.models.ISysFunc;
import labs.modules.MenuModule;
import labs.lab2.models.FirstSysFunc;
import labs.lab2.models.SecondSysFunc;
import labs.lab2.MathModuleLab2;
import labs.сommands.Main;

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
        sysFuncs.add(new SecondSysFunc());


        for (ISysFunc func : sysFuncs){
            commands.add(new ICommand() {
                @Override
                public String getMessage() {
                    return func.getMessage();
                }
                @Override
                public void execute() {
                    MathModuleLab2.execute(func);
                }
            });
        }
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
