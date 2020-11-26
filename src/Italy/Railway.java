package Italy;

import java.util.ArrayList;
import java.util.Arrays;

public class Railway extends ItalyLocation {
    private String pk_uid;
    private String id;
    private String name;
    private String geo_wkt;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("pk_uid", "id", "name", "geo_wkt"));

    public Railway(String uid, String i, String n, String geo) {
        pk_uid = uid;
        id = i;
        name = n;
        geo_wkt= geo;
    }

    public String getPk_uid() {
        return pk_uid;
    }
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
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(pk_uid, id, name, geo_wkt));
    }


    public void print() {
        System.out.println("id: " + id + " name: " + name);
    }
}
