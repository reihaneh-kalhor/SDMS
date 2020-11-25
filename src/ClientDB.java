import Italy.Community;
import Italy.Province;
import Italy.Railway;
import Italy.Region;

import java.sql.*;
import java.util.ArrayList;

public class ClientDB {

//    public void fillAirportTable() {
//
//        String line = "";
//        Connection conn = null;
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            conn = DriverManager.getConnection("jdbc:sqlite:airports.sqlite");
//            java.sql.Statement stmt = conn.createStatement();
//
//            try {
//                FileReader fileReader = new FileReader(new File("airports.dat").getAbsolutePath());
//                BufferedReader reader = new BufferedReader(fileReader);
//
//                while ((line = reader.readLine()) != null) {
//                    Airport a = new Airport(line);
//                    int id = a.id;
//                    String name = a.name;
//                    String city = a.city;
//                    String country = a.country;
//                    String iata = a.iata;
//                    String icao = a.icao;
//                    float latitude = a.latitude;
//                    float longitude = a.longitude;
//                    float altitude = a.altitude;
//                    float timezone = a.timezone;
//                    String dst = a.dst;
//                    String tz = a.tz;
//                    String type = a.type;
//                    String source = a.source;
//
//                    System.out.println("inserting airport " + id);
//                    String sql = "INSERT INTO Airport (AIRPORT_ID,NAME,CITY,COUNTRY,IATA,ICAO,LAT,LONG,ALT,TIMEZONE,DST,TZ,TYPE,SOURCE) "
//                            + "values ('" + id + "','" + name + "','" + country + "','" +  city + "','" + iata + "','" + icao + "','" + latitude + "','" + longitude + "','"
//                            + altitude + "','" + timezone + "','" + dst + "','" + tz + "','" + type + "','" + source +"')";
//
//                    stmt.executeUpdate(sql);
//
//                }
//                reader.close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//    }
//
//    public void fillFlightTable() {
//
//        String line = "";
//        Connection conn = null;
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            conn = DriverManager.getConnection("jdbc:sqlite:airports.sqlite");
//            java.sql.Statement stmt = conn.createStatement();
//
//            try {
//                FileReader fileReader = new FileReader(new File("routes.dat").getAbsolutePath());
//                BufferedReader reader = new BufferedReader(fileReader);
//
//                while ((line = reader.readLine()) != null) {
//                    Flight f = new Flight(line);
//                    String airline = f.airline;
//                    String id = f.id;
//                    String source = f.source;
//                    String sourceId = f.sourceId;
//                    String destination = f.destination;
//                    String destinationId = f.destinationId;
//                    String codeshare = f.codeshare;
//                    int stops = f.stops;
//                    String equipment = f.equipment;
//
//                    System.out.println("inserting flight " + id);
//                    String sql = "INSERT INTO Flight (AIRLINE,AIRLINE_ID,SOURCE,SOURCE_ID,DESTINATION,DESTINATION_ID,CODESHARE,STOPS,EQUIPMENT) "
//                            + "values ('" + airline + "','" + id + "','" + source + "','" + sourceId + "','" + destination + "','" + destinationId + "','" + codeshare + "','" + stops + "','" + equipment + "')";
//
//                    stmt.executeUpdate(sql);
//
//                }
//                reader.close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//    }

    public ArrayList<Community> readCommunities() {
        Connection conn = null;
        ArrayList<Community> comm_set = new ArrayList<>();

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
    public ArrayList<Province> readProvinces() {
        Connection conn = null;
        ArrayList<Province> prov_set = new ArrayList<>();

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
    public ArrayList<Region> readRegions() {
        Connection conn = null;
        ArrayList<Region> reg_set = new ArrayList<>();

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
    public ArrayList<Railway> readRailways() {
        Connection conn = null;
        ArrayList<Railway> rail_set = new ArrayList<>();

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
