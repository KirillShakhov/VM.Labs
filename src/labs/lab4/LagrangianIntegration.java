package labs.lab4;

import labs.models.IFunc;
import labs.models.Point;
import labs.modules.GraphModule;

import java.awt.*;
import java.util.*;

public class LagrangianIntegration {
    static Scanner scanner = new Scanner(System.in);

    public static void solve(IFunc func1) {
        Map<Double, Double> xy = new HashMap<>();
        while (true) {
            try {
                System.out.println("Выберите готовые данные, или введиет свои:");
                // TODO добавить данных
                System.out.println("1. Сгенерировать данные");
                System.out.println("2. Готовые данные для sin(x)");
                System.out.println("3. Ввести свои данные");
                if (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    if (s.equals("0")) {
                        xy = new HashMap<>();
                        double left, right;
                        left = 0d;
                        right = 4d;
                        Double sep = right - left;
                        Double steps = 4d;
                        for (double i = left; i < right; i += sep / steps) {
                            xy.put(i, func1.solve(i));
                        }
                        System.out.println("Полученные данные:");
                        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                            System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
                        }
                    } else if (s.equals("1")) {
                        while (true) {
                            xy = new HashMap<>();
                            double left, right;
                            System.out.println("Введите границы через запятую");
                            System.out.println("Привем для промежутка от -5 до 5:\n-5,5");
                            System.out.println("Введите данные:");
                            try {
                                if (scanner.hasNext()) {
                                    String[] buffer = scanner.nextLine().split(",");
                                    left = Double.parseDouble(buffer[0]);
                                    right = Double.parseDouble(buffer[1]);
                                    if (right < left) {
                                        Double t = right;
                                        right = left;
                                        left = t;
                                    }
                                    Double sep = right - left;
                                    System.out.println("Введите количество точек:");
                                    Double steps = Double.valueOf(scanner.nextLine());
                                    for (double i = left; i < right; i += sep / steps) {
                                        xy.put(i, func1.solve(i));
                                    }
                                    System.out.println("Полученные данные:");
                                    for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                                        System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
                                    }
                                } else {
                                    System.out.println("Завершершение работы");
                                    System.exit(0);
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Введены неправильные данные");
                            }
                        }
                    } else if (s.equals("2")) {
                        System.out.println("Данные для sin(x):");
                        xy = new HashMap<>();
                        xy.put(1.0, 1.0);
                        xy.put(2.0, 2.0);
                        xy.put(2.0000001, 5.0);
                        xy.put(4.0, 4.0);
                        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                            System.out.println("(" + entry.getKey() + "," + entry.getValue() + ")");
                        }
                    } else {
                        String buffer = "";
                        xy = new HashMap<>();
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
                    }
                } else {
                    System.out.println("Завершение работы");
                    System.exit(0);
                }
                break;
            } catch (
                    Exception ignored) {
            }
        }
        if (xy.isEmpty()) {
            System.out.println("Что-то пошло не так. Массивы X и Y отсутствуют.");
        } else {
            System.out.println("Введите координату x искомой точки:");
            double t = Double.parseDouble(scanner.nextLine());
            Double result = lagranz(xy, t);
            System.out.println("Ln(" + t + ")=" + result);
            ArrayList<Point> points = new ArrayList<>();

            // Сортировка
            ArrayList<Map.Entry<Double, Double>> list = new ArrayList<>(xy.entrySet());
            list.sort(Map.Entry.comparingByKey());


            // Добавление точек исходных данных
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for (Map.Entry<Double, Double> entry : xy.entrySet()) {
                if (entry.getKey() >= max) {
                    max = entry.getKey();
                }
                if (entry.getKey() <= min) {
                    min = entry.getKey();
                }
                points.add(new Point(entry.getKey(), entry.getValue()));
            }


            int steps;
            for (int i = 1; i < list.size(); i++) {
            }

            /*
            Добавление точек результата
             */
            points.add(new Point(t, result, Color.BLUE));

            Approximation(xy, points, min, max);
            new GraphModule(func1, points);
        }

    }

    private static ArrayList<Point> Approximation(Map<Double, Double> xy, ArrayList<Point> points, double min, double max) {
        //Аппроксимирование
        int steps = 100;
        int xplux = 10;
        //
        for (double i = max; i < (max + xplux); i += (max + xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i), Color.orange));
        }
        //
        for (double i = min; i > (min - xplux); i += (min - xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i), Color.orange));
        }
        return points;
    }

    private static double lagranz(Map<Double, Double> xy, double t) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (Map.Entry<Double, Double> entry : xy.entrySet()) {
            x.add(entry.getKey());
            y.add(entry.getValue());
        }
        return lagranz(x, y, t);
    }

    public static double lagranz(ArrayList<Double> X, ArrayList<Double> Y, double t) {
        double sum, prod;
        int n = X.size();
        sum = 0;
        for (int j = 0; j < n; j++) {
            prod = 1;
            for (int i = 0; i < n; i++) {
                if (i != j) {
                    prod *= (t - X.get(i)) / (X.get(j) - X.get(i));
                }
            }
            sum += Y.get(j) * prod;
        }
        return sum;
    }
}
