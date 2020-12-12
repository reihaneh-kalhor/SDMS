package Algorithms;

import Algorithms.PlaneSweepHelpers.PlaneSweepItalyLocation;
import Algorithms.PlaneSweepHelpers.SweepStructure;
import GeographicalLocation.GeographicalLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//procedure PLANE_SWEEP(setA, setB);
//begin
//    listA←SORT_BY_LEFT_SIDE( setA );
//    listB←SORT_BY_LEFT_SIDE( setB );
//    sweepStructureA←CREATE_SWEEP_STRUCTURE();
//    sweepStructureB←CREATE_SWEEP_STRUCTURE();
//    while NOT listA.END() OR NOT listB.END() do
//        /* get left most Italylocation from the two lists */
//        if listA.FIRST() < listB.FIRST() then
//            sweepStructureA.INSERT(listA.FIRST());
//            sweepStructureB.REMOVE_INACTIVE(listA.FIRST());
//            sweepStructureB.SEARCH(listA.FIRST());
//            listA.NEXT();
//        else
//            sweepStructureB.INSERT(listB.FIRST());
//            sweepStructureA.REMOVE_INACTIVE(listB.FIRST());
//            sweepStructureA.SEARCH(listB.FIRST());
//            listB.NEXT();
//        end if;
//    end loop;
//end;

public class PlaneSweep {
    String attribute;
    String attribute2;
    protected ArrayList<PlaneSweepItalyLocation> setA;
    protected ArrayList<PlaneSweepItalyLocation> setB;

    public PlaneSweep(String _attribute){
        attribute = _attribute;
        attribute2 = _attribute;
    }

    public PlaneSweep(String _attribute, String _attribute2){
        attribute = _attribute;
        attribute2 = _attribute2;
    }

    public void initialize(ArrayList<GeographicalLocation> geographicalLocations1, ArrayList<GeographicalLocation> geographicalLocations2){
        setA = intializeLocations(geographicalLocations1, attribute);
        setB = intializeLocations(geographicalLocations2, attribute2);
    }

    private ArrayList<PlaneSweepItalyLocation> intializeLocations(ArrayList<GeographicalLocation> geographicalLocations, String attr){
        ArrayList<PlaneSweepItalyLocation> psLocations = new ArrayList<>();
        for(int i = 0; i< geographicalLocations.size(); i++){
            psLocations.add(new PlaneSweepItalyLocation(geographicalLocations.get(i), attr));
        }
        return psLocations;
    }

    protected ArrayList<PlaneSweepItalyLocation> sortByLeftSide(ArrayList<PlaneSweepItalyLocation> set) {
        Collections.sort(set, new Comparator<PlaneSweepItalyLocation>() {
            @Override
            public int compare(PlaneSweepItalyLocation o1, PlaneSweepItalyLocation o2) {
                double italy1Left = o1.left;
                double italy2Left = o2.left;
                if (italy1Left < italy2Left) return -1;
                if (italy1Left > italy2Left) return 1;
                return 0;
            }
        });
        return set;
    }

    public ArrayList<ArrayList<String>> planeSweep() {
        ArrayList<PlaneSweepItalyLocation> listA = sortByLeftSide(setA);
        ArrayList<PlaneSweepItalyLocation> listB = sortByLeftSide(setB);
        SweepStructure sweepStructureA = new SweepStructure();
        SweepStructure sweepStructureB = new SweepStructure();

        int intersectingCount = 0;

        int indexA = 0;
        int indexB = 0;
        int sizeA = listA.size();
        int sizeB = listB.size();

        while (indexA < sizeA && indexB < sizeB) {
            PlaneSweepItalyLocation listAFirst = listA.get(indexA);
            PlaneSweepItalyLocation listBFirst = listB.get(indexB);
            /* get left most ItalyLocation from the two lists */
            if (listAFirst.left < listBFirst.left) {
                sweepStructureA.insert(listAFirst);
                sweepStructureB.removeInactive(listAFirst);
                ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureB.search(listAFirst);
                intersectingCount += intersecting.size();
                indexA++;
            } else {
                sweepStructureB.insert(listBFirst);
                sweepStructureA.removeInactive(listBFirst);
                ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureA.search(listBFirst);
                intersectingCount += intersecting.size();
                indexB++;
            }
        }
        while (indexA < sizeA && !sweepStructureB.isEmpty()) {
            PlaneSweepItalyLocation listAFirst = listA.get(indexA);
            sweepStructureB.removeInactive(listAFirst);
            ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureB.search(listAFirst);
            intersectingCount = intersectingCount + intersecting.size();
            indexA++;
        }
        while (indexB < sizeB && !sweepStructureA.isEmpty()) {
            PlaneSweepItalyLocation listBFirst = listB.get(indexB);
            sweepStructureA.removeInactive(listBFirst);
            ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureA.search(listBFirst);
            intersectingCount = intersectingCount + intersecting.size();
            indexB++;
        }

        System.out.println("PlaneSweep detected " + intersectingCount + " intersections");
        return null;
    }
}
