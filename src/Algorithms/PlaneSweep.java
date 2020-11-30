package Algorithms;

import Algorithms.PlaneSweepHelpers.Rectangle;
import Algorithms.PlaneSweepHelpers.SweepStructure;

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
//        /* get left most rectangle from the two lists */
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

    private ArrayList<Rectangle> sortByLeftSide(ArrayList<Rectangle> set) {
        Collections.sort(set, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                float rect1Left = o1.left;
                float rect2Left = o2.left;
                if (rect1Left < rect2Left) return -1;
                if (rect1Left > rect2Left) return 1;
                return 0;
            }
        });
        return set;
    }

    public void planeSweep(ArrayList<Rectangle> setA, ArrayList<Rectangle> setB) {
        ArrayList<Rectangle> listA = sortByLeftSide(setA);
        ArrayList<Rectangle> listB = sortByLeftSide(setB);
        SweepStructure sweepStructureA = new SweepStructure();
        SweepStructure sweepStructureB = new SweepStructure();

        int indexA = 0;
        int indexB = 0;
        int sizeA = listA.size();
        int sizeB = listB.size();

        while (indexA < sizeA && indexB < sizeB) {
            Rectangle listAFirst = listA.get(indexA);
            Rectangle listBFirst = listB.get(indexB);
            /* get left most rectangle from the two lists */
            if (listAFirst.left < listBFirst.left) {
                sweepStructureA.insert(listAFirst);
                sweepStructureB.removeInactive(listAFirst);
                ArrayList<Rectangle> intersecting = sweepStructureB.search(listAFirst);
                //TODO: Do something meaningful with the result
                indexA++;
            } else {
                sweepStructureB.insert(listBFirst);
                sweepStructureA.removeInactive(listBFirst);
                ArrayList<Rectangle> intersecting = sweepStructureA.search(listBFirst);
                //TODO: Do something meaningful with the result
                indexB++;
            }
        }
    }
}
