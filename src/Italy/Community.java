package Italy;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Community extends ItalyLocation {
    private String community_id;
    private String community_name;
    private String population;
    private String province_id;
    private String geo_wkt;
    private Geometry geometry;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("community_id", "community_name", "population", "province_id", "geo_wkt"));

    public Community(String id, String name, String pop, String prov_id, String geo, Geometry geom) {
        community_id = id;
        community_name = name;
        population = pop;
        province_id = prov_id;
        geo_wkt = geo;
        geometry = geom;
    }
    public String getComm_id() {
        return community_id;
    }
    public String getComm_name() {
        return community_name;
    }
    public String getPopulation() {
        return population;
    }
    public String getProvince_id() {
        return province_id;
    }
    public String getGeo_wkt() {
        return geo_wkt;
    }
    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    public ArrayList<String> getColumns() {return columns; }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(community_id, community_name, population, province_id, geo_wkt));
    }

    public void print() {
        System.out.println("id: " + community_id + " name: " + community_name + " population: " + population + " prov_id: " + province_id);
    }
}
