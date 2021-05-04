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
        System.out.println("Интерполирование многочленом Лагранжа:");
        Map<String, IFuncXY> funcs = new HashMap<>();
        // 1
        funcs.put("??", (x, y) -> 2 * x);
        // 2
        funcs.put("??", (x, y) -> 2 * x);
        //

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
        try {
            IFuncXY func1 = funcs.get(keys.get(Integer.parseInt(str) - 1));
            RungeKuttaMethodMath.solve(func1);
        } catch (Exception e) {
            System.out.println("Нет такого уравнения");
        }
    }
}
