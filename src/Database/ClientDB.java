package Database;

import GeographicalLocation.*;
//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDB {

    private WKTReader2 reader = new WKTReader2();

    public ArrayList<GeographicalLocation> readCommunities() {
        Connection conn;
        ArrayList<GeographicalLocation> comm_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from communities;");

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
    public ArrayList<GeographicalLocation> readProvinces() {
        Connection conn;
        ArrayList<GeographicalLocation> prov_set = new ArrayList<>();

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
    public ArrayList<GeographicalLocation> readRegions() {
        Connection conn;
        ArrayList<GeographicalLocation> reg_set = new ArrayList<>();

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
    public ArrayList<GeographicalLocation> readRailways() {
        Connection conn;
        ArrayList<GeographicalLocation> rail_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from railways;");

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
    public ArrayList<GeographicalLocation> readPopulatedPlaces() {
        Connection conn;
        ArrayList<GeographicalLocation> pop_set = new ArrayList<>();

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
    public ArrayList<GeographicalLocation> readCountries() {
        Connection conn;
        ArrayList<GeographicalLocation> country_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from COUNTRIES;");
            while(rs.next()) {
                String id = rs.getString("country_id");
                String name = rs.getString("country_name");
                String population = rs.getString("population");
                String income_group = rs.getString("income_group");
                String geo_wkt = rs.getString("geo_wkt");
                Geometry geom = reader.read(geo_wkt);
                Country c = new Country(id, name, population, income_group, geo_wkt, geom);
                country_set.add(c);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return country_set;
    }
    public ArrayList<GeographicalLocation> readProvincesGlobal() {
        Connection conn;
        ArrayList<GeographicalLocation> prov_global_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from PROVINCES_GLOBAL;");
            while(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("province_name");
                String type = rs.getString("province_type");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String country_name = rs.getString("country_name");
                String geo_wkt = rs.getString("geo_wkt");
                Geometry geom = reader.read(geo_wkt);
                ProvinceGlobal p = new ProvinceGlobal(id, name, type, latitude, longitude, country_name, geo_wkt, geom);
                prov_global_set.add(p);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return prov_global_set;
    }
    public ArrayList<GeographicalLocation> readPorts() {
        Connection conn;
        ArrayList<GeographicalLocation> port_set = new ArrayList<>();

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enableLoadExtension(true);
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:italy.sqlite", config.toProperties());

            // read communities
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from PORTS;");
            while(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String scale_rank = rs.getString("scale_rank");
                String website = rs.getString("website");
                String ntlscale = rs.getString("ntlscale");
                String geo_wkt = rs.getString("geo_wkt");
                Geometry geom = reader.read(geo_wkt);
                Port p = new Port(id, name, scale_rank, website, ntlscale, geo_wkt, geom);
                port_set.add(p);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return port_set;
    }
}
