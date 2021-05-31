package labs;
import labs.lab5.commands.CauchyProblem;
import labs.modules.MenuModule;

public class Bootstrap {
    public static void main(String[] args) {
        MenuModule menuModule = new MenuModule();
        menuModule.addCommand(new CauchyProblem());
        menuModule.execute();
    }
}
