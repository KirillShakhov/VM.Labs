package lab.modules;

import java.util.ArrayList;

public class FuncModule {
    public static double first(double val) {
        return Math.pow(val, 2) + val + 2;
    }
    public static double firstP1(double val) {
        return 2 * val + 1;
    }
    public static double second(double val) {
        return 3 * Math.pow(val, 2) - (14 * val) - 5;
    }
    public static double secondP1(double val) {
        return 6 * val - 14;
    }

    public static double third(double val) {
        return Math.pow(val, 2) + (2 * val) + 1;
    }
    public static double thirdP1(double val) {
        return 2 * val + 2;
    }
    public static double fourth(double val) {
        return Math.pow(Math.E, val) - 1;
    }
    public static double fourthP1(double val) {
        return Math.pow(Math.E, val);
    }

    //Система
    public static ArrayList<Double> fun0(double x0, double y0) {
        ArrayList<Double> ar = new ArrayList<>();
        ar.add(valStr1(x0, y0));
        ar.add(valStr2(x0, y0));
        return ar;
    }

    public static double valStr1(double val, double val1) {
        return val + val1 - 8;
    }
    public static double valStr2(double val, double val1) {
        return Math.pow(val, 2) + Math.pow(val1, 2) - 82;
    }

//    def yakobian0(x0, y0):
//            return [[1, 1], [2 * x0, 2 * y0]]
//
//
//    def valStr1(val, val1):
//            return val + val1 - 8
//
//
//    def valStr2(val, val1):
//            return math.pow(val, 2) + math.pow(val1, 2) - 82
//
//
//    def valGr(val):
//            return 8 - val
//
//
//    def valGr1(val):
//            return math.pow(82 - math.pow(val, 2), 0.5)
//


}
