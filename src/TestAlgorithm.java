import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class TestAlgorithm {
    public static void main(String[] args) {

        ClientDB db = new ClientDB();
//        db.fillAirportTable();    // fill sqlite db with airport data (!!! ONLY EXECUTE ONCE, otherwise data is duplicated)
//        db.fillFlightTable();    // fill sqlite db with flight data (!!! ONLY EXECUTE ONCE, otherwise data is duplicated)

//        System.out.println("Reading dataset1");
//        ArrayList<Airport> set1 = new ArrayList<Airport>();
//        try {
//            String[] lines = Files.readAllLines(new File("airports.dat").toPath()).toArray(new String[0]); // airports_lite.dat: 3790 airports
//            for (String i : lines) {
//                Airport a = new Airport(i);
////                a.print();
//                set1.add(a);
//            }
//            System.out.println("number of airports: " + set1.size());
//        } catch(IOException ex) {
//            System.out.println(ex.toString());
//        }
//
//        System.out.println("Reading dataset2");
//        ArrayList<Flight> set2 = new ArrayList<Flight>();
//        try {
//            String[] lines = Files.readAllLines(new File("routes.dat").toPath()).toArray(new String[0]); // airports_lite.dat: 3790 airports
//            for (String i : lines) {
//                Flight f = new Flight(i);
////                f.print();
//                set2.add(f);
//            }
//            System.out.println("number of airports: " + set2.size());
//        } catch(IOException ex) {
//            System.out.println(ex.toString());
//        }

        db.readItalyDB();
    }
}
