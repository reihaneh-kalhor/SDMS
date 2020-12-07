package Benchmarks;

import Algorithms.PlaneSweep;
import Algorithms.PlaneSweepHelpers.PlaneSweepItalyLocation;
import Database.ClientDB;
import Italy.ItalyLocation;

import java.util.ArrayList;

public class PlaneSweepBenchmark {

    private static long calculateAvgDuration(ArrayList<Long> durations){
        long temp = 0;
        for (int i=0; i<durations.size();i++){
            temp += durations.get(i);
        }
        return temp/durations.size();
    }

    private static void printResults(int warmUp, int reps, long avg){
        System.out.println("--------------------------");
        System.out.println("Total runs: " + reps);
        System.out.println("Warm-up runs: " + warmUp);
        System.out.println("Average Duration: " + avg + "ms");
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Long> durations = new ArrayList<>();
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

        System.out.println("Reading data from Database...");
        ArrayList<ItalyLocation> communities = db.readCommunities();
        //ArrayList<ItalyLocation> provinces = db.readProvinces();
        //ArrayList<ItalyLocation> regions = db.readRegions();
        ArrayList<ItalyLocation> railways = db.readRailways();
        //ArrayList<ItalyLocation> populatedPlaces = db.readPopulatedPlaces();
        System.out.println(" ~ done");


        // PlaneSweep object
        PlaneSweep ps = new PlaneSweep();

        //Initialize data
        System.out.println("Initializing...");
        long startTime = System.nanoTime();
        ArrayList<PlaneSweepItalyLocation> ps1 = ps.intializeLocations(communities);
        ArrayList<PlaneSweepItalyLocation> ps2 = ps.intializeLocations(railways);
        long initTime = System.nanoTime();
        System.out.println("Finished initializing, took " + ((initTime - startTime) / 1000000) + "ms");

        //Run Benchmark
        for (int i=0;i<reps;i++){
            long psStartTime = System.nanoTime();
            ps.planeSweep(ps1, ps2);
            long endTime = System.nanoTime();
            long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
            System.out.println("PlaneSweep took: " + duration + "ms");
            if (i>= warmUp){
                durations.add(duration);
            }
        }

        //Report Result
        long avg = calculateAvgDuration(durations);
        printResults(warmUp, reps, avg);
    }
}
