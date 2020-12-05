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

import Italy.ItalyLocation;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.List;
import com.infomatiq.jsi.rtree.RTree;
import java.util.Properties;

public class IndexedNestedLoopReiVer {

    public ArrayList<List> join(ArrayList<ItalyLocation> table1/*, ArrayList<ItalyLocation> table2*/) {

        ArrayList<List> result = new ArrayList<>();

        for (ItalyLocation n1 : table1) {
            createRTreeIndex(Float.parseFloat(n1.getLatitude()), Float.parseFloat(n1.getLongitude()),Integer.parseInt(n1.getId()));
        }
        System.out.println("Spatial index complete");

        return result;
    }

    private Rectangle genRectPoint(float latitude, float longitude) {
        float lat = latitude + (((float)Math.random() - 0.5f) * 0.0001f);
        float lon = longitude + (((float)Math.random() - 0.5f) * 0.0001f);
        return new Rectangle(lon, lat, lon, lat);
    }

    private void createRTreeIndex(float latitude, float longitude, int id) {
        SpatialIndex si = new RTree();
        si.init(null);
        Rectangle newRect = genRectPoint(latitude, longitude);
        si.add(newRect, id);

    }


}
