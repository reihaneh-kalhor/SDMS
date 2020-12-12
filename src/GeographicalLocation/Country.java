package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Country extends GeographicalLocation{
    private String id;
    private String name;
    private String population;
    private String income_group;
    private String geo_wkt;
    private Geometry geom;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("id", "country_name", "population", "income_group", "geo_wkt"));

    public Country(String i, String n, String pop, String inc, String geo, Geometry g) {
        id = i;
        name = n;
        population = pop;
        income_group = inc;
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

    public String getPopulation() {
        return population;
    }

    public String getIncome_group() {
        return income_group;
    }


    @Override
    public Geometry getGeometry() {
        return geom;
    }

    @Override
    public String getLatitude() {
        return "Country has no latitude";
    }

    @Override
    public String getLongitude() {
        return "Country has no longitude";
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(id, name, population, income_group, geo_wkt));
    }


    public void print() {
        System.out.println("id: " + id + " name: " + name + " population: " + population + " income_group: " + income_group);
    }
}
