public class Flight {

//    Airline	            2-letter (IATA) or 3-letter (ICAO) code of the airline.
//    Airline ID	        Unique OpenFlights identifier for airline (see Airline).
//    Source airport	    3-letter (IATA) or 4-letter (ICAO) code of the source airport.
//    Source airport ID	    Unique OpenFlights identifier for source airport (see Airport)
//    Destination airport	3-letter (IATA) or 4-letter (ICAO) code of the destination airport.
//    Destination airport ID	Unique OpenFlights identifier for destination airport (see Airport)
//    Codeshare	            "Y" if this flight is a codeshare (that is, not operated by Airline, but another carrier), empty otherwise.
//    Stops	                Number of stops on this flight ("0" for direct)
//    Equipment	            3-letter codes for plane type(s) generally used on this flight, separated by spaces

    String airline;
    String id;
    String source;
    String sourceId;
    String destination;
    String destinationId;
    String codeshare;
    int stops;
    String equipment;

    Flight(String details) {
        String[] s = details.split(",");
        airline = s[0];
        id = s[1];
        source = s[2];
        sourceId = s[3];
        destination = s[4];
        destinationId = s[5];
        codeshare = s[6];
        stops = Integer.parseInt(s[7]);
        if (s.length == 8) {
            equipment = "320";
        } else {
            equipment = s[8];
        }
    }

    public void print() {
        System.out.println(id + ": " + source + " -> " + destination);
    }
}
