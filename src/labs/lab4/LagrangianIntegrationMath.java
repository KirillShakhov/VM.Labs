package labs.lab4;
import labs.models.IFuncX;
import labs.models.Point;
import labs.modules.GraphModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class LagrangianIntegrationMath {
    static Scanner scanner = new Scanner(System.in);

    public static void solve(IFuncX func1) {
        ArrayList<Point> xy = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Выберите готовые данные, или введиет свои:");
                System.out.println("1. Сгенерировать данные");
                System.out.println("2. Ввести свои данные");
                System.out.println("3. Ввести свои данные из файла");
                System.out.println("4. Готовые данные для sin(x)");
                if (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    if (s.equals("0")) {
                        xy = new ArrayList<>();
                        double left, right;
                        left = 0d;
                        right = 4d;
                        Double sep = right - left;
                        Double steps = 4d;
                        for (double i = left; i < right; i += sep / steps) {
                            xy.add(new Point(i, func1.solve(i)));
                        }
                        System.out.println("Полученные данные:");
                        for (Point point : xy) {
                            System.out.println("(" + point.getX() + "," + point.getY() + ")");
                        }
                    } else if (s.equals("1")) {
                        while (true) {
                            xy = new ArrayList<>();
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
                                        xy.add(new Point(i, func1.solve(i)));
                                    }
                                    System.out.println("Полученные данные:");
                                    for (Point point : xy) {
                                        System.out.println("(" + point.getX() + "," + point.getY() + ")");
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
                    } else if(s.equals("2")) {
                        String buffer = "";
                        xy = new ArrayList<>();
                        System.out.println("Вводите данные через запятую, используйте \"0\" после ввода данных:");
                        System.out.println("Пример:\n1,2\n2,3\n4,5\n0");
                        System.out.println("Вводите данные:");
                        while (!buffer.equals("0")) {
                            try {
                                if (scanner.hasNext()) {
                                    buffer = scanner.nextLine();
                                    if (!buffer.equals("0")) {
                                        String[] t = buffer.split(",");
                                        xy.add(new Point(Double.parseDouble(t[0]), Double.parseDouble(t[1])));
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
                    } else if(s.equals("3")){
                        try {
                            xy = new ArrayList<>();
                            System.out.println("Введите имя файла:");
                            String path = scanner.nextLine();
                            BufferedReader file = new BufferedReader(new FileReader(new File(path)));
                            while (true) {
                                String buffer = file.readLine().trim();
                                if (!buffer.equals("0")) {
                                    String[] t = buffer.split(",");
                                    xy.add(new Point(Double.parseDouble(t[0]), Double.parseDouble(t[1])));
                                } else {
                                    break;
                                }
                            }
                        }catch (Exception e){
                            System.out.println("Произошла ошибка при чтании файла");
                            e.printStackTrace();
                        }
                    } else if (s.equals("4")) {
                        System.out.println("Данные для sin(x):");
                        xy = new ArrayList<>();
                        xy.add(new Point(1.14, 0.9));
                        xy.add(new Point(4.0, -0.756802));
                        xy.add(new Point(5.221, -0.873422));
                        for (Point point : xy) {
                            System.out.println("(" + point.getX() + "," + point.getY() + ")");
                        }
                    } else{
                        System.out.println("Такого варианта нет");
                        throw new Exception("Такого варианта нет");
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
            Map<String, ArrayList<IFuncX>> map_func = new HashMap<>();
            Map<String, ArrayList<Point>> point_func = new HashMap<>();
            // Добавление функции на график
            ArrayList<IFuncX> funcs = new ArrayList<>();
            funcs.add(func1);
            map_func.put("График функции", funcs);

            // Добавление точек исходных данных на график
            point_func.put("Точки исходных данных", xy);

            // Интерполяция
            System.out.println("Введите координату x искомой точки:");
            double t = Double.parseDouble(scanner.nextLine());
            double result = lagranz(xy, t);
            System.out.println("Ln(" + t + ")=" + result);
            ArrayList<Point> point = new ArrayList<>();
            point.add(new Point(t, result));
            point_func.put("Точка Интерполяции", point);
            // Аппроксимирование
            ArrayList<Point> interpolation = Interpolation(xy, 2);
            point_func.put("Точки Интерполяции", interpolation);
            // Экстрополяция
            ArrayList<Point> extrapolation = Extrapolation(xy);
            point_func.put("Точки вне исходных данных, посчитанные Лагранжем", extrapolation);
            // Рисуем график
            new GraphModule(map_func, point_func);
        }

    }

    /*
    Построение точек между входными данными.
    xy - входные данные: точки в массиве
    steps - количество точек, которое надо найти между точками входных данных.
     */
    private static ArrayList<Point> Interpolation(ArrayList<Point> xy, int steps) {
        ArrayList<Point> points = new ArrayList<>();
        // Сортировка
        xy.sort(Comparator.comparingDouble(Point::getX));
        //
        for (int i = 1; i < xy.size(); i++) {
            double first = xy.get(i-1).getX();
            double second = xy.get(i).getX();
            double step = (second-first)/(steps+1);
            for(double j = first+step; j+0.0000001 < second; j+=step){
                points.add(new Point(j, lagranz(xy, j)));
            }
        }
        return points;
    }
    // Построение точек вне промежутка.
    private static ArrayList<Point> Extrapolation(ArrayList<Point> xy) {
        ArrayList<Point> points = new ArrayList<>();
        int steps = 100;
        int xplux = 10;
        // Поиск минимума и макисума
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (Point point : xy) {
            if (point.getX() >= max) { max = point.getX(); }
            if (point.getX() <= min) { min = point.getX(); }
        }
        //
        for (double i = max; i < (max + xplux); i += (max + xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i)));
        }
        //
        for (double i = min; i > (min - xplux); i += (min - xplux) / steps) {
            points.add(new Point(i, lagranz(xy, i)));
        }
        return points;
    }

    // Обертка для Лагранжа, чтобы использовать Array с точками, вместо двух массивов
    private static double lagranz(ArrayList<Point> xy, double x1) {
        // разделение на два массива
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (Point point : xy) {
            x.add(point.getX());
            y.add(point.getY());
        }
        return lagranz(x, y, x1);
    }

    /*
    Лагранж - метод лагранжа
    X - координаты X
    Y - координаты Y
    x1 - координата X для поиска Y.
     */
    public static double lagranz(ArrayList<Double> X, ArrayList<Double> Y, double x1) {
        double sum, prod;
        int n = X.size();
        sum = 0;
        for (int j = 0; j < n; j++) {
            prod = 1;
            for (int i = 0; i < n; i++) {
                if (i != j) {
                    prod *= (x1 - X.get(i)) / (X.get(j) - X.get(i));
                }
            }
            sum += Y.get(j) * prod;
        }
        return sum;
    }
}
