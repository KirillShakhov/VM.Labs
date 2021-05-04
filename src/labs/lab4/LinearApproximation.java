package labs.lab4;

import labs.models.ICommand;
import labs.models.IFuncX;
import labs.models.Point;
import labs.modules.GraphModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinearApproximation implements ICommand {
    Scanner scanner = new Scanner(System.in);
    @Override
    public String getMessage() {
        return "Линейная Аппроксимация";
    }

    @Override
    public void execute() {
        String buffer = "";
        HashMap<Double, Double> xy = new HashMap<>();
        System.out.println("Вводите данные через запятую, используйте \"0\" после ввода данных:");
        System.out.println("Пример:\n1,2\n2,3\n4,5\n0");
        System.out.println("Вводите данные:");
        while (!buffer.equals("0")) {
            try {
                if (scanner.hasNext()) {
                    buffer = scanner.nextLine();
                    if (!buffer.equals("0")) {
                        String[] t = buffer.split(",");
                        xy.put(Double.valueOf(t[0]), Double.valueOf(t[1]));
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Завершершение работы");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Некоретные данные, введите строку повторно или используйте \"0\", чтобы закончить вводить данные.");
            }
        }
        Map<String, ArrayList<IFuncX>> map_func = new HashMap<>();
        Map<String, ArrayList<Point>> point_func = new HashMap<>();
        // Добавление функции на график
        ArrayList<IFuncX> funcs = new ArrayList<>();
        IFuncX result = approximation(xy);
        funcs.add(result);
        map_func.put("График функции, полученной аппроксимацией", funcs);
        // Добавление точек исходных данных на график
        ArrayList<Point> data_points = new ArrayList<>();
        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
            data_points.add(new Point(entry.getKey(), entry.getValue()));
        }
        point_func.put("Точки исходных данных", data_points);
        // Рисуем график
        new GraphModule(map_func, point_func);
    }
    private static IFuncX approximation(Map<Double, Double> xy) {
        ArrayList<Double> x = new ArrayList<>(), y = new ArrayList<>();
        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
            x.add(entry.getKey());
            y.add(entry.getValue());
        }
        return approximation(x, y);
    }

    public static IFuncX approximation(ArrayList<Double> x, ArrayList<Double> y){
        double SX=0, SY=0, SX2=0, SXY=0;
        if(x.size() != y.size()){
            return null;
        }
        for (int i=0; i < x.size(); i++)
        {
            SX += x.get(i);
            SY += y.get(i);
            SX2 += x.get(i) * x.get(i);
            SXY += x.get(i) * y.get(i);
        }
        double Det=SX2 * ((double)x.size())-SX*SX;
        double Dk=SXY *  ((double)x.size())-SX*SY;
        double Db=SX2*SY-SX*SXY;
        double k=Dk/Det;
        double b=Db/Det;
        return x1 -> k*x1+b;
    }
}
