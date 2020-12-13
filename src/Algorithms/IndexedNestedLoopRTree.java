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

import GeographicalLocation.GeographicalLocation;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;
import gnu.trove.TIntProcedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//source: https://github.com/aled/jsi

public class IndexedNestedLoopRTree {
    private Rectangle[] rTreeRecIndex1;
    private Rectangle[] rTreeRecIndex2;


    public ArrayList<GeographicalLocation> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2) {

        ArrayList<GeographicalLocation> result = new ArrayList<>();
        SpatialIndex si1 = initialSpatialIndex(table1.size(), rTreeRecIndex1);
        SpatialIndex si2 =
                initialSpatialIndex(table2.size(), rTreeRecIndex2);

        for (GeographicalLocation n1 : table1) {
            createRTreeIndex(si1, rTreeRecIndex1,
                    Float.parseFloat(n1.getLatitude()), Float.parseFloat(n1.getLongitude()),Integer.parseInt(n1.getId()));
        }
        System.out.println("Spatial index 1 " +
                "complete");

        for (GeographicalLocation n2 : table2) {
            createRTreeIndex(si2, rTreeRecIndex2,
                    Float.parseFloat(n2.getLatitude()), Float.parseFloat(n2.getLongitude()),Integer.parseInt(n2.getId()));
        }
        System.out.println("Spatial index 2 " +
                "complete");

        List<Integer> ids = new ArrayList<Integer>();
        TIntProcedure p =  new TIntProcedure() {
            public boolean execute(int id) {
                ids.add(id);
                return true;
            }
        };

        for (Rectangle r2 : rTreeRecIndex2){
            si1.intersects(r2,p);
        }

        for (Integer id : ids) {
            for (GeographicalLocation n2 : table2) {
                if(Integer.parseInt(n2.getId()) == id){
                    result.add(n2);
                }
            }
        }

        return result;
    }

    private Rectangle genRectPoint(float latitude, float longitude) {
        float lat = latitude + (((float)Math.random() - 0.5f) * 0.0001f);
        float lon = longitude + (((float)Math.random() - 0.5f) * 0.0001f);
        return new Rectangle(lon, lat, lon+0.5f, lat+0.5f);
    }

    private SpatialIndex initialSpatialIndex(int count, Rectangle[] rects){
        SpatialIndex si = new RTree();
        si.init(new Properties());
        rects = new Rectangle[count];
        return si;
    }
    private void createRTreeIndex(SpatialIndex si, Rectangle[] rects,
                               float latitude, float longitude, int id) {
        Rectangle newRect = genRectPoint(latitude, longitude);
        rects[id] = newRect;
        si.add(newRect, id);
    }
}
