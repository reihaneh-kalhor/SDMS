package Italy;

import java.util.ArrayList;
import java.util.Arrays;

public class Community extends ItalyLocation {
    private String comm_id;
    private String comm_name;
    private String population;
    private String province_id;
    private String geo_wkt;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("comm_id", "comm_name", "population", "province_id", "geo_wkt"));

    public Community(String id, String name, String pop, String prov_id, String geo) {
        comm_id = id;
        comm_name = name;
        population = pop;
        province_id = prov_id;
        geo_wkt = geo;
    }
    public String getComm_id() {
        return comm_id;
    }
    public String getComm_name() {
        return comm_name;
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
    public ArrayList<String> getColumns() {return columns; }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(comm_id, comm_name, population, province_id, geo_wkt));
    }

    public void print() {
        System.out.println("id: " + comm_id + " name: " + comm_name + " population: " + population + " prov_id: " + province_id);
    }
}
