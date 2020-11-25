package Italy;

public class Railway {
    String pk_uid;
    String id;
    String name;
    String geo_wkt;

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

    public void print() {
        System.out.println("id: " + id + " name: " + name);
    }
}
