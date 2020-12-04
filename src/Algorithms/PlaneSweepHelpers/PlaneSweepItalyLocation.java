package Algorithms.PlaneSweepHelpers;

import Algorithms.GeometryHelpers.GeometryReader;
import Italy.ItalyLocation;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import java.util.Arrays;
import java.util.Comparator;

public class PlaneSweepItalyLocation {
    private GeometryReader reader = new GeometryReader();
    public ItalyLocation italyLocation;
    public double left;
    public Geometry geometry;

    public boolean intersects(PlaneSweepItalyLocation otherObj){
        return geometry.intersects(otherObj.geometry);
    }

    public PlaneSweepItalyLocation(ItalyLocation location){
        italyLocation = location;
        // Geometry
        int geoIndex = italyLocation.getColumns().indexOf("geo_wkt");
        String geoString = italyLocation.getValuesAsList().get(geoIndex);
        geometry = reader.read(geoString);
        // Left: get x-value of leftmost coordinate
        Arrays.stream(geometry.getCoordinates()).min(new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                if (o1.x < o2.x) return -1;
                if (o1.x > o2.x) return 1;
                return 0;
            }
        }).ifPresent(value -> this.left = value.x );
    }

}
