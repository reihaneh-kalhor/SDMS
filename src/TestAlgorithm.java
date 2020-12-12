import Algorithms.*;
import Database.ClientDB;
import Italy.*;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithm {
    public static void main(String[] args) {

        ClientDB db = new ClientDB();

        System.out.println("Reading communities...");
        ArrayList<ItalyLocation> communities = db.readCommunities();

//        System.out.println("Reading provinces...");
//        ArrayList<ItalyLocation> provinces = db.readProvinces();

//        System.out.println("Reading regions...");
//        ArrayList<ItalyLocation> regions = db.readRegions();

        System.out.println("Reading railways...");
        ArrayList<ItalyLocation> railways = db.readRailways();

//        System.out.println("Reading populated_places...");
//        ArrayList<ItalyLocation> populatedPlaces = db.readPopulatedPlaces();    // TODO create and populate "geo_wkt" column
        System.out.println(" ~ done \n");


        // Nested loop example queries
//        NestedLoop nl = new NestedLoop();

//        long startTime = System.nanoTime();
//        ArrayList<ArrayList<String>> res = nl.join(communities, provinces, "province_id");      // res size: 8092   time: 271ms
//        ArrayList<ArrayList<String>> res = nl.join(communities, populatedPlaces, "community_name", "name");      // res size: 7713   time: 6352ms
//        ArrayList<ArrayList<String>> res = nl.join(communities, railways, "geo_wkt");      // res size: 81   time: 22684ms
//        long endTime = System.nanoTime();
//
//        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.


        // Index-nested loop join
//         only works with community and railway tables for now (because they have geometry column)
        long startTime = System.nanoTime();
        IndexedNestedLoop idxnl = new IndexedNestedLoop();
        ArrayList<Geometry> res = idxnl.join(communities, railways); // res size: 862   time: 130ms
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.

        // Index-nested loop join Rey version
//        // only works with community and railway tables for now (because they have geometry column)
//        long startTime = System.nanoTime();
//        IndexedNestedLoopRTree idxnl = new IndexedNestedLoopRTree();
//        ArrayList<ItalyLocation> res =
//                idxnl.join(populatedPlaces,populatedPlaces); // res size: 862   time: 130ms
//        long endTime = System.nanoTime();
//
//        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
//        System.out.println("time: " + duration + "ms");

    }
}
