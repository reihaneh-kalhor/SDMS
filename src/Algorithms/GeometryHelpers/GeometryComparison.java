package Algorithms.GeometryHelpers;

import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.io.ParseException;


public class GeometryComparison {
    // Geometry relationships: https://docs.geotools.org/latest/userguide/library/jts/relate.html

    // compare 2 geometries, return true if they intersect (!compareShapesIntersection for disjoint)
    //https://locationtech.github.io/spatial4j/apidocs/org/locationtech/spatial4j/io/WKTReader.html
    public boolean compareShapesIntersection(String shape1, String shape2) {

        WKTReader2 reader = new WKTReader2();
        try{
            Geometry geometry = reader.read(shape1);
            Geometry geometry2 = reader.read(shape2);

            return geometry.intersects(geometry2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }
    public boolean compareShapesIntersection(Geometry shape1, Geometry shape2) {
        return shape1.intersects(shape2);
    }
    public boolean compareShapesIntersection(Geometry shape1, String shape2) {

        WKTReader2 reader = new WKTReader2();
        try{
            Geometry geometry2 = reader.read(shape2);

            return shape1.intersects(geometry2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }
    public boolean compareShapesIntersection(String shape1, Geometry shape2) {

        WKTReader2 reader = new WKTReader2();
        try{
            Geometry geometry1 = reader.read(shape1);

            return geometry1.intersects(shape2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }

    // compare two geometries, return DE-9IM (use matrix to find what relationships hold, e.g. matrix.isDisjoint() or matrix.isTouches(2,2))
    // https://docs.geotools.org/stable/userguide/library/jts/dim9.html
    // https://locationtech.github.io/jts/javadoc/org/locationtech/jts/geom/IntersectionMatrix.html
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
