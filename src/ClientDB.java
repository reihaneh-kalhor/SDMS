import Italy.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDB {


    public ArrayList<ItalyLocation> readCommunities() {
        Connection conn;
        ArrayList<ItalyLocation> comm_set = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite");

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from communities;");
            while(rs.next()) {
                String comm_id = rs.getString("community_id");
                String comm_name = rs.getString("community_name");
                String population = rs.getString("population");
                String province_id = rs.getString("province_id");
                String geo_wkt = rs.getString("geo_wkt");
                Community comm = new Community(comm_id, comm_name, population, province_id, geo_wkt);
                comm_set.add(comm);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return comm_set;
    }
    public ArrayList<ItalyLocation> readProvinces() {
        Connection conn;
        ArrayList<ItalyLocation> prov_set = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite");

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from provinces;");
            while(rs.next()) {
                String province_id = rs.getString("province_id");
                String province_name = rs.getString("province_name");
                String car_plate_code = rs.getString("car_plate_code");
                String region_id = rs.getString("region_id");
                Province prov = new Province(province_id, province_name, car_plate_code, region_id);
                prov_set.add(prov);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return prov_set;
    }
    public ArrayList<ItalyLocation> readRegions() {
        Connection conn;
        ArrayList<ItalyLocation> reg_set = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite");

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from regions;");
            while(rs.next()) {
                String region_id = rs.getString("region_id");
                String region_name = rs.getString("region_name");
                Region reg = new Region(region_id, region_name);
                reg_set.add(reg);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return reg_set;
    }
    public ArrayList<ItalyLocation> readRailways() {
        Connection conn;
        ArrayList<ItalyLocation> rail_set = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite");

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from railways;");
            while(rs.next()) {
                String pk_uid = rs.getString("pk_uid");
                String id = rs.getString("id");
                String name = rs.getString("name");
                String geo_wkt = rs.getString("geo_wkt");
                Railway reg = new Railway(pk_uid, id, name, geo_wkt);
                rail_set.add(reg);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return rail_set;
    }
}
