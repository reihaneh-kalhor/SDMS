package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GeographicalLocation {
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList(""));

    public abstract String getId();
    public abstract Geometry getGeometry();
    public abstract String getGeoWKT();
    public abstract ArrayList<String> getColumns();
    public abstract ArrayList<String> getValuesAsList();

    public abstract String getLatitude();
    public abstract String getLongitude();

}
