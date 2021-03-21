package lab;

import lab.Commands.ICommand;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ArrayList<ICommand> commands;

    public Menu(ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    public void setCommands(ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    public void addCommand(ICommand command){
        this.commands.add(command);
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Меню:");
                int i = 1;
                for(ICommand command : commands){
                   System.out.println(i++ + ". " + command.getMessage());
                }
                if(scanner.hasNext()) {
                    String result = scanner.nextLine();
                    if(isNumeric(result) && Integer.parseInt(result) > 0 && Integer.parseInt(result) <= commands.size()){
                        commands.get(Integer.parseInt(result)-1).execute();
                    }
                    else{
                        boolean isBreak = false;
                        for(ICommand command : commands){
                            if(command.getMessage().equals(result)){
                                command.execute();
                                isBreak = true;
                                break;
                            }
                        }
                        if(!isBreak){
                            System.out.println("Такого варианта нет. Попробуйте ещё раз");
                        }
                    }
                }
                else{
                    System.out.println("Завершение работы");
                    System.exit(0);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static boolean isNumeric(String str) {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
