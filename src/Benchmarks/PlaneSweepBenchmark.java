package Benchmarks;

import Algorithms.PlaneSweepMerge;
import Database.ClientDB;
import GeographicalLocation.GeographicalLocation;

import java.util.ArrayList;

public class PlaneSweepBenchmark {

    private static long calculateAvgDuration(ArrayList<Long> durations){
        long temp = 0;
        for (int i=0; i<durations.size();i++){
            temp += durations.get(i);
        }
        return temp/durations.size();
    }

    private static void printResults(int warmUp, int reps, long avg, long init, int intersections){
        System.out.println("--------------------------");
        System.out.println("Detected Intersections: " + intersections);
        System.out.println("Total runs: " + reps);
        System.out.println("Warm-up runs: " + warmUp);
        System.out.println("Average Initializing Duration: " + init + "ms");
        System.out.println("Average PlaneSweep Duration: " + avg + "ms");
        System.out.println("Combined Duration: " + (init + avg) + "ms");
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Long> initDurations = new ArrayList<>();
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


        // PlaneSweepMerge object
        PlaneSweepMerge psm = new PlaneSweepMerge("geo_wkt");

        //Run Benchmark
        ArrayList<GeographicalLocation> russianProvinces = new ArrayList<>();
        for (GeographicalLocation prov : prov_global) {
            if (prov.getValuesAsList().get(3).equals("Russia")) {
                russianProvinces.add(prov);
            }
        }
        for (int i=0;i<reps;i++){
            //Initialize data
            System.out.println("Initializing...");
            System.out.println("--------------------------");
            long initStartTime = System.nanoTime();

            //Set data to use and measure initializing time
            // Query 1
//            psm.initialize(communities, railways);
            // Query 2
//            psm.initialize(countries, ports);
            // Query 3
            psm.initialize(countries, russianProvinces);

            long initEndTime = System.nanoTime();
            long initDuration = ((initEndTime - initStartTime) / 1000000);
            System.out.println("Finished initializing, took " + initDuration + "ms");
            System.out.println("--------------------------");
            long psStartTime = System.nanoTime();
            ArrayList<ArrayList<String>> result = psm.planeSweep();
            long endTime = System.nanoTime();
            long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
            long fullDuration = initDuration + duration;
            detectedIntersections = result.size();
            System.out.println("PlaneSweep took: " + duration + "ms" + "  (" + fullDuration + "ms plus init)");
            System.out.println("--------------------------");
            if (i>= warmUp){
                initDurations.add(initDuration);
                durations.add(duration);
            }
        }

        //Report Result
        long avg = calculateAvgDuration(durations);
        long avgInit = calculateAvgDuration(initDurations);
        printResults(warmUp, reps, avg, avgInit, detectedIntersections);
    }
}
