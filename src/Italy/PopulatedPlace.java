package Italy;

import java.util.ArrayList;
import java.util.Arrays;

public class PopulatedPlace extends ItalyLocation {
    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("id", "name", "latitude", "longitude"));

    public PopulatedPlace(String i, String n, String la, String lo) {
        id = i;
        name = n;
        latitude = la;
        longitude = lo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(id, name, latitude, longitude));
    }


    public void print() {
        System.out.println("id: " + id + " name: " + name + " latitude: " + latitude + " longitude: " + longitude);
    }
}