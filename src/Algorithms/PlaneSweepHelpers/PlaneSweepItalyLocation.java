package Algorithms.PlaneSweepHelpers;

import Algorithms.GeometryHelpers.GeometryReader;
import GeographicalLocation.GeographicalLocation;
import org.locationtech.jts.geom.Geometry;

import java.util.Arrays;

public class PlaneSweepItalyLocation {
    private GeometryReader reader = new GeometryReader();
    public GeographicalLocation geographicalLocation;
    public double left = Double.POSITIVE_INFINITY;
    public double right = Double.NEGATIVE_INFINITY;
    public Geometry geometry;

    public boolean intersects(PlaneSweepItalyLocation otherObj){
        return geometry.intersects(otherObj.geometry);
    }

    public PlaneSweepItalyLocation(GeographicalLocation location, String attribute){
        geographicalLocation = location;

        // Geometry
        int geoIndex = geographicalLocation.getColumns().indexOf(attribute);
        String geoString = geographicalLocation.getValuesAsList().get(geoIndex);
        geometry = reader.read(geoString);

        // Left and Right : find x-value of leftmost and rightmost coordinate
        Arrays.stream(geometry.getCoordinates()).forEach(coordinate -> {
            if (coordinate.x < this.left){
                this.left = coordinate.x;
            } else if (coordinate.x > this.right){
                this.right = coordinate.x;
            }
        });
    }

}
