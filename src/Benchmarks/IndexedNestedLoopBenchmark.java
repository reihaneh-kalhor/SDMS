package Benchmarks;

import Algorithms.IndexedNestedLoop;
import Database.ClientDB;
import Italy.ItalyLocation;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class IndexedNestedLoopBenchmark {

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
        System.out.println("Average IndexNestedLoop Duration: " + avg + "ms");
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
        ArrayList<ItalyLocation> communities = db.readCommunities();
        ArrayList<ItalyLocation> railways = db.readRailways();
        System.out.println(" ~ done");
        System.out.println("--------------------------");


        // IndexedNestedLoop object
        IndexedNestedLoop idxnl = new IndexedNestedLoop();

        //Run Benchmark
        for (int i=0;i<reps;i++){
            long psStartTime = System.nanoTime();
            ArrayList<Geometry> result = idxnl.join(communities, railways);
            long endTime = System.nanoTime();
            long duration = (endTime - psStartTime) / 1000000;  //divide by 1000000 to get ms, 1000000000 for sec.
            detectedIntersections = result.size();
            System.out.println("IndexedNestedLoop took: " + duration + "ms");
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
