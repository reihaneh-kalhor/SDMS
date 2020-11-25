package Algorithms.GeometryHelpers;

import org.geotools.geometry.iso.io.wkt.ParseException;
import org.geotools.geometry.iso.io.wkt.WKTReader;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.util.factory.FactoryFinder;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPolygon;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.opengis.geometry.coordinate.LineString;

public class Geometry {
    // compare MultiLineString (shape1) with MultiPolygon (shape2), used to
    public boolean compareShapes(String shape1, String shape2) {


        WKTReader2 reader = new WKTReader2();
        try{
            MultiLineString geometry = (MultiLineString) reader.read(shape1);
//            System.out.println("Read shape1...");
//            System.out.println(geometry);

            MultiPolygon geometry2 = (MultiPolygon) reader.read(shape2);
//            System.out.println("Read shape2...");
//            System.out.println(geometry2);

            return geometry.intersects(geometry2);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }
}
