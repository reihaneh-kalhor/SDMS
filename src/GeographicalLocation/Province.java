package GeographicalLocation;

import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;

public class Province extends GeographicalLocation {
    private String province_id;
    private String province_name;
    private String car_plate_code;
    private String region_id;
    private ArrayList<String> columns = new ArrayList<>(Arrays.asList("province_id", "province_name", "car_plate_code", "region_id"));

    public Province(String id, String name, String car, String reg_id){
        province_id = id;
        province_name = name;
        car_plate_code = car;
        region_id = reg_id;
    }
    @Override
    public String getId() {
        return province_id;
    }
    public String getProvince_name() {
        return province_name;
    }
    public String getCar_plate_code() {
        return car_plate_code;
    }
    public String getRegion_id() {
        return region_id;
    }

    @Override
    public Geometry getGeometry() {
        System.out.println("Province has no geometry column");
        return null;
    }
    @Override
    public String getGeoWKT() {
        return null;
    }
    public ArrayList<String> getColumns() {return columns; }
    public ArrayList<String> getValuesAsList() {
        return new ArrayList<>(Arrays.asList(province_id, province_name, car_plate_code, region_id));
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
        System.out.println("id: " + province_id + " name: " + province_name + " car: " + car_plate_code + " region_id: " + region_id);
    }
}
