package lab.modules;

import java.util.*;

public class CalcModule {

    ArrayList<String> post;

    public CalcModule(String Func) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("(");

        String[] ar = new String[1];

        ar = (Func + " ").split(" ");

        String f = "";

        for (int i = 0; i < ar.length; i++) {
            f = f + ar[i];
        }

        ar = f.split("");
        int ind = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i].equals("+") | ar[i].equals("-") | ar[i].equals("*") | ar[i].equals("/") | ar[i].equals("(") | ar[i].equals(")") | ar[i].equals("^")) {

                String s = "";
                for (int j = ind; j < i; j++) {
                    s = s + ar[j];
                }
                list.add(s);
                list.add(ar[i]);
                ind = i + 1;
            } else {
                if ((i + 2) < ar.length) {
                    if ((ar[i] + ar[i + 1] + ar[i + 2]).equals("sin") | (ar[i] + ar[i + 1] + ar[i + 2]).equals("cos") | (ar[i] + ar[i + 1] + ar[i + 2]).equals("ctg")) {
                        list.add(ar[i] + ar[i + 1] + ar[i + 2]);
                        i = i + 2;
                        ind = i + 1;
                    }
                } else {
                    if ((i + 1) < ar.length) {
                        if ((ar[i] + ar[i + 1]).equals("tg") | (ar[i] + ar[i + 1]).equals("ln") | (ar[i] + ar[i + 1]).equals("lg") | (ar[i] + ar[i + 1]).equals("Pi")) {
                            list.add(ar[i] + ar[i + 1] + ar[i + 2]);
                            i = i + 1;
                            ind = i + 1;
                        }
                    }
                }
            }
        }





        String s = "";
        for (int j = ind; j < ar.length; j++) {
            s = s + ar[j];
        }
        list.add(s);

        int q = 0;
        while (q == 0) {
            q = 1;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("")) {
                    list.remove(i);
                    q = 0;
                }
            }
        }

        q = 0;
        while (q == 0) {
            q = 1;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("-")) {
                    if (list.get(i - 1).equals("(")) {
                        list.remove(i);
                        list.add(i, "-1");
                        list.add(i + 1, "*");
                        q = 0;
                    }
                }
            }
        }

        post = new ArrayList<String>();
        ArrayList<String> steck = new ArrayList<String>();
        steck.add("");
        list.add(")");
        list.add("?");


        int i = 0;
        while (!list.get(i).equals("?")) {

            if (list.get(i).equals("(")) {
                steck.add("(");
                i++;
            } else if (list.get(i).equals("*") | list.get(i).equals("/")) {
                if (steck.get(steck.size() - 1).equals("*") | steck.get(steck.size() - 1).equals("/")) {
                    post.add(steck.get(steck.size() - 1));
                    steck.remove(steck.size() - 1);
                } else {
                    steck.add(list.get(i));
                    i++;
                }
            } else if (list.get(i).equals("+") | list.get(i).equals("-")) {
                if (steck.get(steck.size() - 1).equals("(")) {
                    steck.add(list.get(i));
                    i++;
                } else {
                    post.add(steck.get(steck.size() - 1));
                    steck.remove(steck.size() - 1);
                }
            } else if (list.get(i).equals(")")) {
                while (!steck.get(steck.size() - 1).equals("(")) {
                    post.add(steck.get(steck.size() - 1));
                    steck.remove(steck.size() - 1);
                }
                i++;
                steck.remove(steck.size() - 1);
            } else if (list.get(i).equals("^") | list.get(i).equals("sin") | list.get(i).equals("cos") | list.get(i).equals("tg") | list.get(i).equals("ctg") | list.get(i).equals("lg") | list.get(i).equals("ln")) {
                steck.add(list.get(i));
                i++;
            } else {
                post.add(list.get(i));
                i++;
            }

        }

        q = 0;
        while (q == 0) {
            q = 1;
            for (i = 0; i < post.size(); i++) {
                if (post.get(i).equals("")) {
                    post.remove(i);
                    q = 0;
                }
            }
        }

    }

    public Double execute(double x) {
        ArrayList<Double> steck = new ArrayList<Double>();

        for (int i = 0; i < post.size(); i++) {
            if (post.get(i).equals("+")) {
                double n = steck.get(steck.size() - 1) + steck.get(steck.size() - 2);
                steck.remove(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("-")) {
                double n = steck.get(steck.size() - 2) - steck.get(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("*")) {
                double n = steck.get(steck.size() - 2) * steck.get(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("/")) {
                double n = steck.get(steck.size() - 2) / steck.get(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("^")) {
                double n = Math.pow(steck.get(steck.size() - 2), steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("x") | post.get(i).equals("X")) {
                steck.add(x);
            } else if (post.get(i).equals("sin")) {
                double n = Math.sin(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("cos")) {
                double n = Math.cos(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("tg")) {
                double n = Math.tan(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("ctg")) {
                double n = Math.cos(steck.get(steck.size() - 1)) / Math.sin(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("ln")) {
                double n = Math.log(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("lg")) {
                double n = Math.log10(steck.get(steck.size() - 1));
                steck.remove(steck.size() - 1);
                steck.add(n);
            } else if (post.get(i).equals("Pi") | post.get(i).equals("pi")) {
                steck.add(Math.PI);
            } else if (post.get(i).equals("e") | post.get(i).equals("E")) {
                steck.add(Math.E);
            } else {
                steck.add(Double.parseDouble(post.get(i)));
            }


        }
        double y = steck.get(0);
        return y;
    }
}
