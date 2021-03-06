package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Region extends GeographicalLocation {
    private String region_id;
    private String region_name;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("region_id", "region_name"));

    public Region(String id, String name) {
        region_id = id;
        region_name = name;
    }
    @Override
    public String getId() {
        return region_id;
    }
    public String getRegion_name() {
        return region_name;
    }

    @Override
    public Geometry getGeometry() {
        System.out.println("Region has no geometry column");
        return null;
    }
    @Override
    public String getGeoWKT() {
        return null;
    }

    public ArrayList<String> getColumns() {return columns; }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(region_id, region_name));
    }

    @Override
    public String getLatitude() {
        System.out.println("Region has no Latitude column");
        return null;
    }

    @Override
    public String getLongitude() {
        System.out.println("Region has no Longitude column");
        return null;
    }

    public void print() {
        System.out.println("id: " + region_id + " name: " + region_name);
    }
}
