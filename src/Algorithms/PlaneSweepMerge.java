package Algorithms;

import Algorithms.PlaneSweepHelpers.PlaneSweepItalyLocation;
import Algorithms.PlaneSweepHelpers.SweepStructure;

import java.util.ArrayList;

public class PlaneSweepMerge extends PlaneSweep {

    public PlaneSweepMerge(String _attribute){
        super(_attribute);
    }

    public PlaneSweepMerge(String _attribute, String _attribute2){
        super(_attribute, _attribute2);
    }

    private void merge(PlaneSweepItalyLocation location1, ArrayList<PlaneSweepItalyLocation> intersectingLocations, ArrayList<ArrayList<String>> result){
        int attIdx1 = location1.geographicalLocation.getColumns().indexOf(attribute);
        ArrayList<String> values1 = location1.geographicalLocation.getValuesAsList();

        for(int i=0;i<intersectingLocations.size();i++){
            PlaneSweepItalyLocation location2 = intersectingLocations.get(i);
            int attIdx2 = location2.geographicalLocation.getColumns().indexOf(attribute2);
            ArrayList<String> values2 = location2.geographicalLocation.getValuesAsList();
            values2.remove(attIdx2);
            values1.addAll(values2);
            result.add(values1); // merge values
        }
    }

    @Override
    public ArrayList<ArrayList<String>> planeSweep() {
        ArrayList<PlaneSweepItalyLocation> listA = sortByLeftSide(setA);
        ArrayList<PlaneSweepItalyLocation> listB = sortByLeftSide(setB);
        SweepStructure sweepStructureA = new SweepStructure();
        SweepStructure sweepStructureB = new SweepStructure();
        ArrayList<ArrayList<String>> result = new ArrayList<>();

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
                merge(listAFirst, intersecting, result);
                indexA++;
            } else {
                sweepStructureB.insert(listBFirst);
                sweepStructureA.removeInactive(listBFirst);
                ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureA.search(listBFirst);
                merge(listBFirst, intersecting, result);
                indexB++;
            }
        }
        while (indexA < sizeA && !sweepStructureB.isEmpty()) {
            PlaneSweepItalyLocation listAFirst = listA.get(indexA);
            sweepStructureB.removeInactive(listAFirst);
            ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureB.search(listAFirst);
            merge(listAFirst, intersecting, result);
            indexA++;
        }
        while (indexB < sizeB && !sweepStructureA.isEmpty()) {
            PlaneSweepItalyLocation listBFirst = listB.get(indexB);
            sweepStructureA.removeInactive(listBFirst);
            ArrayList<PlaneSweepItalyLocation> intersecting = sweepStructureA.search(listBFirst);
            merge(listBFirst, intersecting, result);
            indexB++;
        }
        return result;
    }
}