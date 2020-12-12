package Algorithms;

//procedure INDEX_NESTED_LOOP_JOIN(setA, setB);
//begin
//    spatialIndex←CREATE_SPATIAL_INDEX(setA);
//    for each a ∈ setA
//        spatialIndex.INSERT(a)
//    end for each;
//    for each b ∈ setB
//        searchResults←spatialIndex.SEARCH(b)
//        REPORT(searchResults)
//    end for each;
//end;


import Algorithms.GeometryHelpers.GeometryComparison;
import Italy.ItalyLocation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.List;

public class IndexedNestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    public ArrayList<Geometry> join(ArrayList<ItalyLocation> table1, ArrayList<ItalyLocation> table2) {
        ArrayList<Geometry> interMediateResult = new ArrayList<>();
        ArrayList<Geometry> result = new ArrayList<>();
        STRtree spatialIndex = new STRtree();

        for (ItalyLocation n1 : table1) {
            Geometry geometry1 = n1.getGeometry();

            spatialIndex.insert(geometry1.getEnvelopeInternal(), geometry1);
        }
        System.out.println("Spatial index complete");

        for (ItalyLocation n2 : table2) {
            Geometry geometry2 = n2.getGeometry();

            List intersectingObjects = spatialIndex.query(geometry2.getEnvelopeInternal());
            interMediateResult.addAll(intersectingObjects);
        }
        System.out.println("Spatial querying complete");
        System.out.println("MBR intersections: " + interMediateResult.size());

        for (Geometry geom1 : interMediateResult) {

            for (ItalyLocation n2 : table2) {
                int attIdx2 = n2.getColumns().indexOf("geo_wkt");
                String geom2 = n2.getValuesAsList().get(attIdx2);

                if (geo.compareShapesIntersection(geom1.toString(), geom2)) { // if joining on geometry, compare shapes
                    result.add(geom1);
                }
            }
        }
        return result;
    }
}
