import Italy.*;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.renderer.crs.GeographicHandlerFactory;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDB {


    public ArrayList<ItalyLocation> readCommunities() {
        Connection conn;
        ArrayList<ItalyLocation> comm_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from communities;");

            WKTReader2 reader = new WKTReader2();
            while(rs.next()) {
                try{
                    String comm_id = rs.getString("community_id");
                    String comm_name = rs.getString("community_name");
                    String population = rs.getString("population");
                    String province_id = rs.getString("province_id");
                    String geo_wkt = rs.getString("geo_wkt");
                    Geometry geom = reader.read(geo_wkt);
                    Community comm = new Community(comm_id, comm_name, population, province_id, geo_wkt, geom);
                    comm_set.add(comm);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
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
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

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
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

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
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from railways;");
            WKTReader2 reader = new WKTReader2();

            while(rs.next()) {
                try {
                    String pk_uid = rs.getString("pk_uid");
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String geo_wkt = rs.getString("geo_wkt");
                    Geometry geom = reader.read(geo_wkt);

                    Railway rail = new Railway(pk_uid, id, name, geo_wkt, geom);
                    rail_set.add(rail);
                    }
                catch (ParseException e) {
                e.printStackTrace();
                System.exit(1);
            }
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return rail_set;
    }
    public ArrayList<ItalyLocation> readPopulatedPlaces() {
        Connection conn;
        ArrayList<ItalyLocation> pop_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from populated_places;");
            while(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                PopulatedPlace pop = new PopulatedPlace(id, name, latitude, longitude);
                pop_set.add(pop);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return pop_set;
    }
}
