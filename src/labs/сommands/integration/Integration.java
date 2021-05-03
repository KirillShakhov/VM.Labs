package labs.сommands.integration;
import labs.models.ICommand;
import labs.modules.MenuModule;
import java.util.ArrayList;

public class Integration implements ICommand {
    @Override
    public String getMessage() { return "Численное интегрирование"; }

    @Override
    public void execute() {
        ArrayList<ICommand> commands = new ArrayList<>();
        commands.add(new NumericalIntegrationTrapezoid());
        commands.add(new NumericalIntegrationRectangle());
        //
        MenuModule menu = new MenuModule(commands);
        menu.execute();
    }
}
