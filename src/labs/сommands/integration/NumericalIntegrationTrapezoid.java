package labs.сommands.integration;
import labs.models.ICommand;
import labs.models.IFunc;
import labs.lab3.TrapezoidIntegral;
import labs.lab3.models.IndividualFunc;
import labs.modules.MenuModule;
import labs.сommands.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NumericalIntegrationTrapezoid implements ICommand {
    @Override
    public String getMessage() {
        return "Нахождение интегралов методом Трапеций";
    }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        HashMap<String, IFunc> funcHashMap = new HashMap<>();
//        funcHashMap.put("x^2+x+2", x -> Math.pow(x, 2) + x + 2);
//        funcHashMap.put("3x^2-14x-5", x -> 3 * Math.pow(x, 2) - (14 * x) - 5);
//        funcHashMap.put("x^2+2x+1", x -> Math.pow(x, 2) + (2 * x) + 1);
//        funcHashMap.put("e^x-1", x -> Math.pow(Math.E, x) - 1);
        funcHashMap.put("2x", x -> 2 * x);
        funcHashMap.put("1/x", x -> 1/x);
        funcHashMap.put("sin(x)/x", x -> Math.sin(x)/x);

        for(Map.Entry<String, IFunc> entry : funcHashMap.entrySet()) {
            commands.add(new ICommand() {
                @Override
                public String getMessage() {
                    return entry.getKey();
                }
                @Override
                public void execute() {
                    TrapezoidIntegral.execute(entry.getValue());
                }
            });
        }
        commands.add(new IndividualFunc());
        commands.add(new Main());
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
