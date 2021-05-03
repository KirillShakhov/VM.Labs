package labs.lab4;

import labs.models.IFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LagrangianIntegration {
    static Scanner scanner = new Scanner(System.in);
    public static void solve(IFunc func1){
        Map<Double, Double> xy = new HashMap<>();
        while (true){
            try{
                System.out.println("Выберите готовые данные, или введиет свои:");
                System.out.println("1. Готовые данные для sin(x)");
                System.out.println("2. Ввести свои данные");
                if(scanner.hasNext()) {
                    String s = scanner.nextLine();
                    if(s.equals("1")){
                        System.out.println("Данные для sin(x):");
                        xy = new HashMap<>();
                        xy.put(1.0, 1.0);
                        xy.put(2.0, 2.0);
                        xy.put(2.0000001, 5.0);
                        xy.put(4.0, 4.0);
                        for(Map.Entry<Double, Double> entry : xy.entrySet()) {
                            System.out.println("("+entry.getKey()+","+entry.getValue()+")");
                        }
                    }else if(s.equals("2")){
                        System.out.println("Данные для null:");
                    }else{
                        String buffer = "";
                        xy = new HashMap<>();
                        System.out.println("Вводите данные через запятую, используйте \"0\" после ввода данных:");
                        System.out.println("Пример:\n1,2\n2,3\n4,5\n0");
                        System.out.println("Вводите данные:");
                        while(buffer != "0"){
                            try{
                                if(scanner.hasNext()) {
                                    buffer = scanner.nextLine();
                                    if(buffer != "0") {
                                        String[] t = buffer.split(",");
                                        xy.put(Double.valueOf(t[0]), Double.valueOf(t[1]));
                                    }
                                    else{
                                        break;
                                    }
                                }else{
                                    System.out.println("Завершершение работы");
                                    System.exit(0);
                                }
                            }catch (Exception e){
                                System.out.println("Некоретные данные, введите строку повторно или используйте \"0\", чтобы закончить вводить данные.");
                            }
                        }
                    }
                }else{
                    System.out.println("Завершение работы");
                    System.exit(0);
                }
                break;
            }catch (Exception ignored){}
        }
        if(xy.isEmpty()){
            System.out.println("Что-то пошло не так. Массивы X и Y отсутствуют.");
        }
        else {
            System.out.println("Введите координату x искомой точки:");
            double t = Double.parseDouble(scanner.nextLine());
            System.out.println("Ln(" + t + ")=" + lagranz(xy, t));
        }
    }

    private static double lagranz(Map<Double, Double> xy, double t) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for(Map.Entry<Double, Double> entry : xy.entrySet()) {
            x.add(entry.getKey());
            y.add(entry.getValue());
        }
        return lagranz(x, y, t);
    }

    public static double lagranz(ArrayList<Double> X, ArrayList<Double> Y, double t){
        double sum, prod;
        int n = X.size();
        sum = 0;
        for (int j = 0; j < n; j++){
            prod=1;
            for (int i = 0; i < n; i++){
                if (i != j){
                    prod *= (t- X.get(i))/(X.get(j) - X.get(i));
                }
            }
            sum += Y.get(j) * prod;
        }
        return sum;
    }
}
