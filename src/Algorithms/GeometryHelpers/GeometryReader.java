package Algorithms.GeometryHelpers;

import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;

public class GeometryReader {
    public Geometry read(String shape) {
        Geometry geometry = null;

        WKTReader2 reader = new WKTReader2();
        try{
            geometry = reader.read(shape);
            return geometry;
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return geometry;
    }
}
