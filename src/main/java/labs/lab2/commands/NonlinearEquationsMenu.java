package labs.lab2.commands;
import labs.models.ICommand;
import labs.models.IFuncX;
import labs.modules.MenuModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import labs.lab2.models.IndividualFuncX;
import labs.lab2.MathModuleLab2;
import labs.сommands.Main;

public class NonlinearEquationsMenu implements ICommand {
    @Override
    public String getMessage() {
        return "Решение нелинейных уравнений";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        HashMap<String, IFuncX> funcHashMap = new HashMap<>();
        funcHashMap.put("x^2+x+2", x -> Math.pow(x, 2) + x + 2);
        funcHashMap.put("3x^2-14x-5", x -> 3 * Math.pow(x, 2) - (14 * x) - 5);
        funcHashMap.put("x^2+2x+1", x -> Math.pow(x, 2) + (2 * x) + 1);
        funcHashMap.put("e^x-1", x -> Math.pow(Math.E, x) - 1);

        for(Map.Entry<String, IFuncX> entry : funcHashMap.entrySet()) {
            commands.add(new ICommand() {
                @Override
                public String getMessage() {
                    return entry.getKey();
                }
                @Override
                public void execute() {
                    MathModuleLab2.execute(entry.getValue());
                }
            });
        }
        commands.add(new IndividualFuncX());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
