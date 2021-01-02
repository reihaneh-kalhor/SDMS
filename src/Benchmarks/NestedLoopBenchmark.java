package Benchmarks;

import Algorithms.NestedLoop;
import Database.ClientDB;
import GeographicalLocation.GeographicalLocation;

import java.util.ArrayList;
import java.util.HashSet;

public class NestedLoopBenchmark {

    private static long calculateAvgDuration(ArrayList<Long> durations){
        long temp = 0;
        for (int i=0; i<durations.size();i++){
            temp += durations.get(i);
        }
        return temp/durations.size();
    }

    private static void printResults(int warmUp, int reps, long avg, int intersections){
        System.out.println("--------------------------");
        System.out.println("Detected Intersections: " + intersections);
        System.out.println("Total runs: " + reps);
        System.out.println("Warm-up runs: " + warmUp);
        System.out.println("Average NestedLoop Duration: " + avg + "ms");
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Long> durations = new ArrayList<>();
        int detectedIntersections = 0;
        int warmUp = 5;
        int reps = 15;
        if(args.length>0){
            reps = Integer.parseInt(args[0]);
            if(args.length>1){
                warmUp = Integer.parseInt(args[1]);
            }
        }

        //Database
        ClientDB db = new ClientDB();

        System.out.println("--------------------------");
        System.out.println("Reading data from Database...");
        ArrayList<GeographicalLocation> communities = db.readCommunities();
        ArrayList<GeographicalLocation> railways = db.readRailways();
        ArrayList<GeographicalLocation> countries = db.readCountries();
        ArrayList<GeographicalLocation> prov_global = db.readProvincesGlobal();
        ArrayList<GeographicalLocation> ports = db.readPorts();
        System.out.println(" ~ done");
        System.out.println("--------------------------");


        // NestedLoop object
        NestedLoop nl = new NestedLoop();

        //Run Benchmark
        ArrayList<GeographicalLocation> russianProvinces = new ArrayList<>();
        for (GeographicalLocation prov : prov_global) {
            if (prov.getValuesAsList().get(3).equals("Russia")) {
                russianProvinces.add(prov);
            }
        }
        System.out.println("nr of russian provinces: " + russianProvinces.size());
        for (int i=0;i<reps;i++){
            long psStartTime = System.nanoTime();

            // Query 1
            HashSet<String> result = nl.joinGeometries(communities, railways);
            // Query 2
//            HashSet<String> result = nl.joinGeometries(countries, ports);
            // Query 3
//            HashSet<String> result = nl.joinGeometries(countries, russianProvinces);

            long endTime = System.nanoTime();
            long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
            detectedIntersections = result.size();
            System.out.println("NestedLoop took: " + duration + "ms");
            System.out.println("--------------------------");
            if (i>= warmUp){
                durations.add(duration);
            }
        }

        //Report Result
        long avg = calculateAvgDuration(durations);
        printResults(warmUp, reps, avg, detectedIntersections);
    }
}
