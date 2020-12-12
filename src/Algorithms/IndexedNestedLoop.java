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
import java.util.List;

public class IndexedNestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    public ArrayList<Geometry> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2) {
        ArrayList<Geometry> interMediateResult = new ArrayList<>();
        ArrayList<Geometry> result = new ArrayList<>();
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

//        int c = 0;
        for (Geometry geom1 : interMediateResult) {
//            System.out.println(c + "/" + resSize);
//            c++;
            for (GeographicalLocation n2 : table2) {
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
