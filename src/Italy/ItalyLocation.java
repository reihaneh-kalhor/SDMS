package Italy;

import java.util.ArrayList;
import java.util.Arrays;

public class ItalyLocation {
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList(""));

    public ArrayList<String> getColumns() {
        return columns;
    }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(""));
    }

    public void print() {
        System.out.println("italy");
    }
}
