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
import GeographicalLocation.GeographicalLocation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IndexedNestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    public HashSet<String> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2) {
        HashSet<Geometry> interMediateResult = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        STRtree spatialIndex = new STRtree();

        for (GeographicalLocation n1 : table1) {
            Geometry geometry1 = n1.getGeometry();

            spatialIndex.insert(geometry1.getEnvelopeInternal(), geometry1);
        }
        System.out.println("Spatial index complete");

        for (GeographicalLocation n2 : table2) {
            Geometry geometry2 = n2.getGeometry();

            List intersectingObjects = spatialIndex.query(geometry2.getEnvelopeInternal());
            interMediateResult.addAll(intersectingObjects);
        }
        System.out.println("Spatial querying complete");
        int resSize = interMediateResult.size();
        System.out.println("MBR intersections: " + resSize);

        for (Geometry geom1 : interMediateResult) {
            int b = 0;

            for (GeographicalLocation n2 : table2) {
                Geometry geom2 = n2.getGeometry();
                b++;

                if (geo.compareShapesIntersection(geom1, geom2)) { // check if Geometries intersect
                    result.add(n2.getValuesAsList().toString().concat(", " + b));
                }
            }
        }
        return result;
    }
}
