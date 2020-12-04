import Algorithms.NestedLoop;
import Algorithms.PlaneSweep;
import Algorithms.PlaneSweepHelpers.PlaneSweepItalyLocation;
import Italy.ItalyLocation;

import java.util.ArrayList;
import java.util.Random;

public class TestPlaneSweep {

    public static void main(String[] args) {
        ClientDB db = new ClientDB();

        System.out.println("Reading communities...");
        ArrayList<ItalyLocation> communities = db.readCommunities();
        System.out.println("Reading provinces...");
        ArrayList<ItalyLocation> provinces = db.readProvinces();
        System.out.println("Reading regions...");
        ArrayList<ItalyLocation> regions = db.readRegions();
        System.out.println("Reading railways...");
        ArrayList<ItalyLocation> railways = db.readRailways();
        System.out.println("Reading populated_places...");
        ArrayList<ItalyLocation> populatedPlaces = db.readPopulatedPlaces();    // TODO create and populate "geo_wkt" column
        System.out.println(" ~ done \n");


        // Nested loop example queries
        PlaneSweep ps = new PlaneSweep();

        long startTime = System.nanoTime();
        ArrayList<PlaneSweepItalyLocation> ps1 = ps.intializeLocations(communities);
        ArrayList<PlaneSweepItalyLocation> ps2 = ps.intializeLocations(railways);
        long initTime = System.nanoTime();
        System.out.println("Finished initializing, took " + ((initTime - startTime) / 1000000) + "ms");

        ps.planeSweep(ps1, ps2);


//        ArrayList<ArrayList<String>> res = nl.join(communities, provinces, "province_id");      // res size: 8092   time: 271ms
//        ArrayList<ArrayList<String>> res = nl.join(communities, populatedPlaces, "community_name", "name");      // res size: 7713   time: 6352ms
//        ArrayList<ArrayList<String>> res = nl.join(communities, railways, "geo_wkt");      // res size: 81   time: 22684ms
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
//        System.out.println("res size: " + res.size());
        System.out.println("time: " + duration + "ms");


    }


}
