import Algorithms.PlaneSweep;
import Database.ClientDB;
import GeographicalLocation.GeographicalLocation;

import java.util.ArrayList;

public class TestPlaneSweep {

    public static void main(String[] args) {
        ClientDB db = new ClientDB();

//        System.out.println("Reading communities...");
//        ArrayList<GeographicalLocation> communities = db.readCommunities();
//        System.out.println("Reading provinces...");
//        ArrayList<GeographicalLocation> provinces = db.readProvinces();
//        System.out.println("Reading regions...");
//        ArrayList<GeographicalLocation> regions = db.readRegions();
//        System.out.println("Reading railways...");
//        ArrayList<GeographicalLocation> railways = db.readRailways();
//        System.out.println("Reading populated_places...");
//        ArrayList<GeographicalLocation> populatedPlaces = db.readPopulatedPlaces();

        System.out.println("Reading countries...");
        ArrayList<GeographicalLocation> countries = db.readCountries();
        System.out.println("Reading provinces...");
        ArrayList<GeographicalLocation> prov_global = db.readProvincesGlobal();
        System.out.println("Reading ports...");
        ArrayList<GeographicalLocation> ports = db.readPorts();
        System.out.println(" ~ done \n");


        // PlaneSweep object
        PlaneSweep ps = new PlaneSweep("geo_wkt");

        //Initialize data
//        long startTime = System.nanoTime();
//        ps.initialize(communities, railways);
//        long initTime = System.nanoTime();
//        System.out.println("Finished initializing, took " + ((initTime - startTime) / 1000000) + "ms");
//
//        long psStartTime = System.nanoTime();
//        ps.planeSweep();
//        long endTime = System.nanoTime();
//        long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
//        System.out.println("PlaneSweep took: " + duration + "ms");


        // Natural Earth Queries
        //Initialize data
        ArrayList<GeographicalLocation> russianProvinces = new ArrayList<>();
        for (GeographicalLocation prov : prov_global) {
            if (prov.getValuesAsList().get(3).equals("Russia")) {
                russianProvinces.add(prov);
            }
        }
        System.out.println("nr of russian provinces: " + russianProvinces.size());
        long startTime = System.nanoTime();
//        ps.initialize(countries, ports);                // res size: 642    time: 39453ms
        ps.initialize(countries, russianProvinces);     // res size: 135    time: 5504ms
        long initTime = System.nanoTime();
        System.out.println("Finished initializing, took " + ((initTime - startTime) / 1000000) + "ms");

        long psStartTime = System.nanoTime();
        ps.planeSweep();
        long endTime = System.nanoTime();
        long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
        System.out.println("PlaneSweep took: " + duration + "ms");
    }
}
