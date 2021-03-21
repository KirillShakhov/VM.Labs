package lab;

import java.util.ArrayList;

public class ResultSet {
    ArrayList<ArrayList<Double>> iteraz = new ArrayList<>();
    ArrayList<Double> result = new ArrayList<>();
    ArrayList<Double> discrepancies = new ArrayList<>();
    public void addIter(double[] iter){
        ArrayList<Double> arrayList = new ArrayList<>();
        for(double it : iter){ arrayList.add(it); }
        iteraz.add(arrayList);
    }
    public String getTable(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("№ ");
        for(int i = 0; i < iteraz.get(0).size(); i++) {
            stringBuilder.append("x").append(i).append("   ");
        }
        stringBuilder.append("\n");
        for(int i = 0; i < iteraz.size(); i++){
            stringBuilder.append(i).append("   |");
            for(Double it : iteraz.get(i)){
                stringBuilder.append(it).append("   |");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void setResult(double[] res){
        for(double it : res){
            result.add(it);
        }
    }

    public ArrayList<Double> getResult() {
        return result;
    }

    public ArrayList<Double> getDiscrepancies() {
        return discrepancies;
    }

    public int getIteratorSize(){
        return iteraz.size();
    }

    public void setDiscrepancies(ArrayList<Double> discrepancies) {
        this.discrepancies = discrepancies;
    }
}
