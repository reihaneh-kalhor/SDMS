

public class Airport {

//    Airport ID	    Unique OpenFlights identifier for this airport.
//    Name	            Name of airport. May or may not contain the City name.
//    City	            Main city served by airport. May be spelled differently from Name.
//    Country	        Country or territory where airport is located. See Countries to cross-reference to ISO 3166-1 codes.
//    IATA	            3-letter IATA code. Null if not assigned/unknown.
//    ICAO	            4-letter ICAO code. Null if not assigned.
//    Latitude	        Decimal degrees, usually to six significant digits. Negative is South, positive is North.
//    Longitude	        Decimal degrees, usually to six significant digits. Negative is West, positive is East.
//    Altitude	        In feet.
//    Timezone	        Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
//    DST	            Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown). See also: Help: Time
//    Tz database   	Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
//    Type	            Type of the airport. Value "airport" for air terminals, "station" for train stations, "port" for ferry terminals and "unknown" if not known. In airports.csv, only type=airport is included.
//    Source	        Source of this data. "OurAirports" for data sourced from OurAirports, "Legacy" for old data not matched to OurAirports (mostly DAFIF), "User" for unverified user contributions. In airports.csv, only source=OurAirports is included.

    int id;
    String name;
    String city;
    String country;
    String iata;
    String icao;
    float latitude;
    float longitude;
    float altitude;
    float timezone;
    String dst;
    String tz;
    String type;
    String source;

    // Class constructor
    Airport(String details) {
        String[] s = details.split(",");
        id = Integer.parseInt(s[0]);
        name = s[1].replaceAll("\"", "").replaceAll("'", "");
        city = s[2].replaceAll("\"", "").replaceAll("'", "");
        country = s[3].replaceAll("\"", "").replaceAll("'", "");
        iata = s[4].replaceAll("\"", "");
        icao = s[5].replaceAll("\"", "");
        latitude = Float.parseFloat(s[6]);
        longitude = Float.parseFloat(s[7]);
        altitude = Float.parseFloat(s[8]);
        timezone = Float.parseFloat(s[9]);
        dst = s[10].replaceAll("\"", "");
        tz = s[11].replaceAll("\"", "");
        type = s[12].replaceAll("\"", "");
        source = s[13].replaceAll("\"", "");
    }

    public void print() {
        System.out.println(id + " - " + name + "("+longitude+", "+latitude+")");
    }


}
