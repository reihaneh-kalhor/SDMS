import Algorithms.PlaneSweep;
import Algorithms.PlaneSweepHelpers.PlaneSweepItalyLocation;
import Database.ClientDB;
import Italy.ItalyLocation;

import java.util.ArrayList;

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
        ArrayList<ItalyLocation> populatedPlaces = db.readPopulatedPlaces();
        System.out.println(" ~ done");


        // PlaneSweep object
        PlaneSweep ps = new PlaneSweep("geo_wkt");

        //Initialize data
        long startTime = System.nanoTime();
        ps.initialize(communities, railways);
        long initTime = System.nanoTime();
        System.out.println("Finished initializing, took " + ((initTime - startTime) / 1000000) + "ms");

        long psStartTime = System.nanoTime();
        ps.planeSweep();
        long endTime = System.nanoTime();
        long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
        System.out.println("PlaneSweep took: " + duration + "ms");
    }
}
