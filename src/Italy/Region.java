package Italy;

public class Region {
    String region_id;
    String region_name;

    public Region(String id, String name) {
        region_id = id;
        region_name = name;
    }
    public String getRegion_id() {
        return region_id;
    }
    public String getRegion_name() {
        return region_name;
    }

    public void print() {
        System.out.println("id: " + region_id + " name: " + region_name);
    }
}
