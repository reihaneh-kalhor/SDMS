package Italy;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Railway extends ItalyLocation {
    private String pk_uid;
    private String id;
    private String name;
    private String geo_wkt;
    private Geometry geometry;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("pk_uid", "id", "name", "geo_wkt"));

    public Railway(String uid, String i, String n, String geo, Geometry geom) {
        pk_uid = uid;
        id = i;
        name = n;
        geo_wkt= geo;
        geometry = geom;
    }

    public String getPk_uid() {
        return pk_uid;
    }
    @Override
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGeo_wkt() {
        return geo_wkt;
    }
    public ArrayList<String> getColumns() {
        return columns;
    }
    public Geometry getGeometry() { return geometry; }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(pk_uid, id, name, geo_wkt));
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
        System.out.println("id: " + id + " name: " + name);
    }
}
