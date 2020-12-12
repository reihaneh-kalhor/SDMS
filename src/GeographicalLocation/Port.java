package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Port extends GeographicalLocation {
    private String id;
    private String name;
    private String scale_rank;
    private String website;
    private String ntlscale;
    private String geo_wkt;
    private Geometry geom;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("id", "name", "scale_rank", "website", "ntlscale", "geo_wkt"));

    public Port(String i, String n, String sc_r, String web, String ntl, String geo, Geometry g) {
        id = i;
        name = n;
        scale_rank = sc_r;
        website = web;
        ntlscale = ntl;
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

    public String getScale_rank() {
        return scale_rank;
    }

    public String getWebsite() {
        return website;
    }

    public String getNtlscale() {
        return ntlscale;
    }

    @Override
    public Geometry getGeometry() {
        return geom;
    }

    @Override
    public String getLatitude() {
        return null;
    }

    @Override
    public String getLongitude() {
        return null;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(id, name, scale_rank, website, ntlscale, geo_wkt));
    }


    public void print() {
        System.out.println("id: " + id + " name: " + name + " scale_rank: " + scale_rank + " website: " + website + " ntlscale: " + ntlscale);
    }

}
