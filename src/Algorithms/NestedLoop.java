package Algorithms;

//procedure NESTED_LOOP_JOIN(setA, setB, joinCondition);
//begin
//    for each a ∈ setA
//        for each b ∈ SetB
//            if SATISFIED(a, b, joinCondition) then
//                REPORT(a, b);
//            end if;
//        next b;
//    next a;
//end;


import Algorithms.GeometryHelpers.Geometry;
import Italy.Community;
import Italy.Railway;

import java.util.ArrayList;

public class NestedLoop {
    Geometry geo = new Geometry();

    // join if community intersects railway
    public void join(ArrayList<Community> communities, ArrayList<Railway> railways) {

        String railway = railways.get(0).getGeo_wkt();
        for (int i = 0; i < communities.size(); i++) {
            String comm = communities.get(i).getGeo_wkt();
            boolean b = geo.compareShapes(railway, comm);
            if (b) {
                System.out.println(railways.get(0).getName() + " and " + communities.get(i).getComm_name() + " intersect!");
            }
        }

    }
    // join communities that intersect
    public void join(ArrayList<Community> communities) {

        for (int i = 0; i < communities.size(); i++) {
            Community comm1 = communities.get(i);
            String comm1Geo = comm1.getGeo_wkt();
            for (int j = 0; j < communities.size(); j++) {
                Community comm2 = communities.get(j);
                String comm2Geo = comm2.getGeo_wkt();
                boolean b = geo.compareShapes(comm1Geo, comm2Geo);
                if (b) {
                    System.out.println(comm1.getComm_name() + " and " + comm2.getComm_name() + " intersect!");
                }
            }
        }

    }
}
