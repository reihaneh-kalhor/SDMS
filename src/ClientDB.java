import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
