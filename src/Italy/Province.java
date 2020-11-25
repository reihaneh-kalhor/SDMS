package Italy;

public class Province {
    String province_id;
    String province_name;
    String car_plate_code;
    String region_id;

    public Province(String id, String name, String car, String reg_id){
        province_id = id;
        province_name = name;
        car_plate_code = car;
        region_id = reg_id;
    }
    public String getProvince_id() {
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

    public void print() {
        System.out.println("id: " + province_id + " name: " + province_name + " car: " + car_plate_code + " region_id: " + region_id);
    }
}
