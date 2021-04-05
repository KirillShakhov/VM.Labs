package lab;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        double x0=0,y0=0,x,y,d1,d2,eps=0.0001;
        do
        {
            x= g_x(y0);
            y= g_y(x0);
            d1=f1(x, y);
            d2=f2(x, y);
            x0=x;
            y0=y;
            System.out.println("x= "+x+"  :  y= "+y);
            System.out.println("Math.abs(d1) = "+ Math.abs(d1));
            System.out.println("Math.abs(d2) = "+ Math.abs(d2));
        }while(Math.abs(d1)>eps && Math.abs(d2)>eps);
    }
//    public static double g_x(double y) {return Math.sin(y+2)-15;}//y=(15-x^2)/x
//    public static double g_y(double x) { return 0.5-Math.cos(x-2); }
//    public static double f1(double x, double y) {return Math.sin(y+2)-x-15;}//y=(15-x^2)/x
//    public static double f2(double x, double y) { return y+Math.cos(x-2)-0.5; }

    //x^2+xy-15=0     // y = (15-x^2)/x
    //y^2+xy-10=0     // x = (10-y^2)/y
//    public static double g_x(double y) {return (10-Math.pow(y, 2))/y;}
//    public static double g_y(double x) { return (15-Math.pow(x, 2))/x; }
//    public static double f1(double x, double y) {return Math.pow(x, 2)+x*y-15;}
//    public static double f2(double x, double y) { return Math.pow(y, 2)+x*y-10; }
    public static double g_x(double y) {return Math.pow(3-Math.pow(y, 2), 0.5);}
    public static double g_y(double x) { return (1-2*x)/3; }
    public static double f1(double x, double y) {return Math.pow(x,2)+Math.pow(y,2)-3;}
    public static double f2(double x, double y) { return 2*x+3*y-1; }

//    static double f(double x)
//    {
//        return Math.pow(2.0,x) - 2*Math.pow(x, 2.0) - 1;
//    }
//
//    static double g(double x)
//    {
//        return x + 0.5*f(x);
//    }
//    public static double f(double x) {
//        return (Math.pow(x,2)-16)/(3*x);
//    }
//    public static double g(double x) { return 3*Math.pow(x, 2)+2*x*f(x)-Math.pow(f(x), 2); }

    //x^2+xy-15=0
    //y^2+xy-10=0
//    public static double f(double x) {return (15-Math.pow(x, 2))/x;}//y=(15-x^2)/x
//    public static double g(double x) { return Math.pow(f(x), 2)+x*f(x)-10; }
//    public static double f(double x) {return 6/x;}//y=(15-x^2)/x
//    public static double g(double x) { return Math.pow(x, 2)+Math.pow(f(x), 2) - 13; }
}
