import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDumper;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.GeometryFactoryFinder;
import org.geotools.geometry.iso.text.WKTParser;
import org.geotools.referencing.crs.*;
import org.geotools.util.factory.Hints;
import org.locationtech.jts.geom.MultiPolygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.geometry.PositionFactory;
import org.opengis.geometry.aggregate.AggregateFactory;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.PrimitiveFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDB {

    public void fillAirportTable() {

        String line = "";
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:airports.sqlite");
            java.sql.Statement stmt = conn.createStatement();

            try {
                FileReader fileReader = new FileReader(new File("airports.dat").getAbsolutePath());
                BufferedReader reader = new BufferedReader(fileReader);

                while ((line = reader.readLine()) != null) {
                    Airport a = new Airport(line);
                    int id = a.id;
                    String name = a.name;
                    String city = a.city;
                    String country = a.country;
                    String iata = a.iata;
                    String icao = a.icao;
                    float latitude = a.latitude;
                    float longitude = a.longitude;
                    float altitude = a.altitude;
                    float timezone = a.timezone;
                    String dst = a.dst;
                    String tz = a.tz;
                    String type = a.type;
                    String source = a.source;

                    System.out.println("inserting airport " + id);
                    String sql = "INSERT INTO Airport (AIRPORT_ID,NAME,CITY,COUNTRY,IATA,ICAO,LAT,LONG,ALT,TIMEZONE,DST,TZ,TYPE,SOURCE) "
                            + "values ('" + id + "','" + name + "','" + country + "','" +  city + "','" + iata + "','" + icao + "','" + latitude + "','" + longitude + "','"
                            + altitude + "','" + timezone + "','" + dst + "','" + tz + "','" + type + "','" + source +"')";

                    stmt.executeUpdate(sql);

                }
                reader.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void fillFlightTable() {

        String line = "";
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:airports.sqlite");
            java.sql.Statement stmt = conn.createStatement();

            try {
                FileReader fileReader = new FileReader(new File("routes.dat").getAbsolutePath());
                BufferedReader reader = new BufferedReader(fileReader);

                while ((line = reader.readLine()) != null) {
                    Flight f = new Flight(line);
                    String airline = f.airline;
                    String id = f.id;
                    String source = f.source;
                    String sourceId = f.sourceId;
                    String destination = f.destination;
                    String destinationId = f.destinationId;
                    String codeshare = f.codeshare;
                    int stops = f.stops;
                    String equipment = f.equipment;

                    System.out.println("inserting flight " + id);
                    String sql = "INSERT INTO Flight (AIRLINE,AIRLINE_ID,SOURCE,SOURCE_ID,DESTINATION,DESTINATION_ID,CODESHARE,STOPS,EQUIPMENT) "
                            + "values ('" + airline + "','" + id + "','" + source + "','" + sourceId + "','" + destination + "','" + destinationId + "','" + codeshare + "','" + stops + "','" + equipment + "')";

                    stmt.executeUpdate(sql);

                }
                reader.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void readItalyDB() {
        Connection conn = null;
//        Hints hints = new Hints(Hints.CRS, DefaultGeographicCRS.WGS84);
//        PositionFactory positionFactory = GeometryFactoryFinder.getPositionFactory(hints);
//        GeometryFactory geometryFactory = GeometryFactoryFinder.getGeometryFactory(hints);
//        PrimitiveFactory primitiveFactory = GeometryFactoryFinder.getPrimitiveFactory(hints);
//        AggregateFactory aggregateFactory = GeometryFactoryFinder.getAggregateFactory(hints);
//
//        WKTParser parser = new WKTParser( geometryFactory, primitiveFactory, positionFactory, aggregateFactory );
        ArrayList<ArrayList<String>> comm_set = new ArrayList<>();
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

//                System.out.println("comm id: " + comm_id + " comm_name: " + comm_name + " population: " + population + " province_id: " + province_id + " geom: " + geo_wkt);

                ArrayList<String> row = new ArrayList<>();
                row.add(comm_id);
                row.add(comm_name);
                row.add(population);
                row.add(province_id);
                row.add(geo_wkt);
                comm_set.add(row);
            }
            System.out.println(comm_set.size());

            // read railways
            java.sql.Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery("select * from railways;");
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }


    }
}
