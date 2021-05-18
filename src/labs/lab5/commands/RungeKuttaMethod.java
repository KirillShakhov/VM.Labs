package labs.lab5.commands;

import labs.lab5.RungeKuttaMethodMath;
import labs.models.ICommand;
import labs.models.IFuncXY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RungeKuttaMethod implements ICommand {
    Scanner scanner = new Scanner(System.in);
    @Override
    public String getMessage() {
        return "Метод Рунге-Кутта 4 го порядка";
    }
    @Override
    public void execute() {
        System.out.println("Рунге-Курта метод:");
        Map<String, IFuncXY> funcs = new HashMap<>();
        // 1
        funcs.put("y' + xy - x^2 = 0", (x, y) -> -(x*y)+Math.pow(x, 2));
        // 2
        funcs.put("yy' - x = 0", (x, y) -> x/y);
        //
        funcs.put("xy' + y - e^x = 0", (x, y) -> (-y+Math.pow(Math.E, x))/2);
        /*
        Вывод и обработка ввода. Не трогать.
        */
        int i = 1;
        ArrayList<String> keys = new ArrayList<>();
        for (Map.Entry<String, IFuncXY> entry : funcs.entrySet()) {
            System.out.println((i++) + ". " + entry.getKey());
            keys.add(entry.getKey());
        }
        String str = scanner.nextLine();
        IFuncXY func1 = null;
        try {
            func1 = funcs.get(keys.get(Integer.parseInt(str) - 1));
        } catch (Exception e) {
            System.out.println("Нет такого уравнения");
        }
        if(func1 != null) {
            try {
                RungeKuttaMethodMath.solve(func1);
            } catch (Exception e) {
                System.out.println("Произошла ошибка");
                e.printStackTrace();
            }
        }
    }
}
