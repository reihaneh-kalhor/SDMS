import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class TestAlgorithm {
    public static void main(String[] args) {

        System.out.println("Reading dataset");
        ArrayList<Airport> set = new ArrayList<Airport>();
        try {
            String[] lines = Files.readAllLines(new File("airports_lite.dat").toPath()).toArray(new String[0]); // airports_lite.dat: 3790 airports
            for (String i : lines) {
                Airport a = new Airport(i);
//                a.print();
                set.add(a);
            }
            set.get(0).print();
            System.out.println("number of airports: " + set.size());
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
