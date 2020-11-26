import Algorithms.NestedLoop;
import Italy.*;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithm {
    public static void main(String[] args) {

        ClientDB db = new ClientDB();


        ArrayList<ItalyLocation> communities = db.readCommunities();
        System.out.println("Read communities");
        ArrayList<ItalyLocation> provinces = db.readProvinces();
        System.out.println("Read provinces");
        ArrayList<ItalyLocation> regions = db.readRegions();
        System.out.println("Read regions");
        ArrayList<ItalyLocation> railways = db.readRailways();
        System.out.println("Read railways");
        System.out.println(" ~ done \n");


        // Nested loop example queries
        NestedLoop nl = new NestedLoop();
//        nl.join(communities, railways);
//        nl.join(communities);
        ArrayList<ArrayList<String>> res = nl.join(provinces, regions, "region_id");
//        ArrayList<ArrayList<String>> res2 = nl.join(communities, provinces, "province_id");
        System.out.println("res size: " + res.size());


    }
}
