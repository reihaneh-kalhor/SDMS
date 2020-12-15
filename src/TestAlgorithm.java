import Algorithms.IndexedNestedLoop;
import Algorithms.NestedLoop;
import Database.ClientDB;
import GeographicalLocation.GeographicalLocation;

import java.util.ArrayList;
import java.util.HashSet;

public class TestAlgorithm {
    public static void main(String[] args) {

        ClientDB db = new ClientDB();

        System.out.println("Reading communities...");
        ArrayList<GeographicalLocation> communities = db.readCommunities();

//        System.out.println("Reading provinces...");
//        ArrayList<GeographicalLocation> provinces = db.readProvinces();

//        System.out.println("Reading regions...");
//        ArrayList<GeographicalLocation> regions = db.readRegions();

        System.out.println("Reading railways...");
        ArrayList<GeographicalLocation> railways = db.readRailways();

//        System.out.println("Reading populated_places...");
//        ArrayList<GeographicalLocation> populatedPlaces = db.readPopulatedPlaces();    // TODO create and populate "geo_wkt" column


//        System.out.println("Reading countries...");
//        ArrayList<GeographicalLocation> countries = db.readCountries();
//
//        System.out.println("Reading provinces...");
//        ArrayList<GeographicalLocation> prov_global = db.readProvincesGlobal();
//
//        System.out.println("Reading ports...");
//        ArrayList<GeographicalLocation> ports = db.readPorts();

        System.out.println(" ~ done \n");




        // Nested loop example queries
//        NestedLoop nl = new NestedLoop();
//        long startTime = System.nanoTime();
////        ArrayList<ArrayList<String>> res = nl.join(communities, provinces, "province_id");      // res size: 8092   time: 271ms
////        ArrayList<ArrayList<String>> res = nl.join(communities, populatedPlaces, "community_name", "name");      // res size: 7713   time: 6352ms
//        HashSet<String> res = nl.joinGeometries(communities, railways);      // res size: 81   time: 8072ms
//        long endTime = System.nanoTime();


//         Index-nested loop join
        IndexedNestedLoop idxnl = new IndexedNestedLoop();
        long startTime = System.nanoTime();
        HashSet<String> res = idxnl.join(communities, railways); // res size: 81   time: 4647ms
        long endTime = System.nanoTime();


        // Index-nested loop join Rey version
//        // only works with community and railway tables for now (because they have geometry column)
//        long startTime = System.nanoTime();
//        IndexedNestedLoopRTree idxnl = new IndexedNestedLoopRTree();
//        ArrayList<ItalyLocation> res = idxnl.join(populatedPlaces,populatedPlaces); // res size: 862   time: 130ms
//        long endTime = System.nanoTime();
//
//        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.


        // Natural earth queries

//
//        ArrayList<GeographicalLocation> russianProvinces = new ArrayList<>();
//        for (GeographicalLocation prov : prov_global) {
//            if (prov.getValuesAsList().get(3).equals("Russia")) {
//                russianProvinces.add(prov);
//            }
//        }
//        System.out.println("nr of russian provinces: " + russianProvinces.size());

//        NestedLoop nl = new NestedLoop();
//        long startTime = System.nanoTime();
////        HashSet<String> res = nl.joinGeometries(countries, ports); // res size: 642    time: 284735ms
//        HashSet<String> res = nl.joinGeometries(countries, russianProvinces); // res size: 135    time: 30772ms
//        long endTime = System.nanoTime();


//        IndexedNestedLoop idxnl = new IndexedNestedLoop();
//        long startTime = System.nanoTime();
////        HashSet<String> res = idxnl.join(countries, ports); // res size: 642    time: 37254ms
//        HashSet<String> res = idxnl.join(countries, russianProvinces); // res size: 135    time: 7038ms
//        long endTime = System.nanoTime();


        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
        System.out.println("time: " + duration + "ms");
        System.out.println("res size: " + res.size());

    }
}
