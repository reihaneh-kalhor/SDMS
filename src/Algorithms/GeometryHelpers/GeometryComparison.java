package Algorithms.GeometryHelpers;

import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.io.ParseException;


public class GeometryComparison {
    // Geometry relationships: https://docs.geotools.org/latest/userguide/library/jts/relate.html

    // compare 2 geometries, return true if they intersect (!compareShapesIntersection for disjoint)
    public boolean compareShapesIntersection(String shape1, String shape2) {

        WKTReader2 reader = new WKTReader2();
        try{
            Geometry geometry = reader.read(shape1);
//            System.out.println(geometry2);

            Geometry geometry2 = reader.read(shape2);
//            System.out.println(geometry2);

            return geometry.intersects(geometry2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }

    // compare two geometries, return DE-9IM (use matrix to find what relationships hold, e.g. matrix.isDisjoint() or matrix.isTouches(2,2))
    // https://docs.geotools.org/stable/userguide/library/jts/dim9.html
    public IntersectionMatrix compareShapesCustom(String shape1, String shape2) {

        WKTReader2 reader = new WKTReader2();
        try{
            Geometry geometry = reader.read(shape1);
//            System.out.println(geometry2);

            Geometry geometry2 = reader.read(shape2);
//            System.out.println(geometry2);

            return geometry.relate(geometry2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new IntersectionMatrix();

    }
}
