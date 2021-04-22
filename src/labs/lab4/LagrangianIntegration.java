package labs.lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class LagrangianIntegration {
    static Scanner scanner = new Scanner(System.in);
    public static void solve(){
        ArrayList<Double> x = new ArrayList<>();
        x.add(1.0);
        x.add(2.0);
        x.add(2.0000001);
        x.add(4.0);
        ArrayList<Double> y = new ArrayList<>();
        y.add(1.0);
        y.add(2.0);
        y.add(5.0);
        y.add(4.0);
        double t = Double.parseDouble(scanner.nextLine());
        System.out.println("Ln("+t+")=" + lagranz(x,y,t));
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
