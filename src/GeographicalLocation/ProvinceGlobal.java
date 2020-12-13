package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class ProvinceGlobal extends GeographicalLocation{
    private String id;
    private String name;
    private String type;
    private String country_name;
    private String latitude;
    private String longitude;
    private String geo_wkt;
    private Geometry geom;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("id", "name", "type", "country_name", "latitude", "longitude", "geo_wkt"));

    public ProvinceGlobal(String i, String n, String t, String c_name, String lat, String lo, String geo, Geometry g) {
        id = i;
        name = n;
        type = t;
        country_name = c_name;
        latitude = lat;
        longitude = lo;
        geo_wkt = geo;
        geom = g;
    }
    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getType() {
        return type;
    }

    @Override
    public Geometry getGeometry() {
        return geom;
    }
    @Override
    public String getGeoWKT() {
        return geo_wkt;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(id, name, type, country_name, latitude, longitude, geo_wkt));
    }


    public void print() {
        System.out.println("id: " + id + " name: " + name + " country_name: " + country_name + " latitude: " + latitude + " longitude: " + longitude);
    }
}
