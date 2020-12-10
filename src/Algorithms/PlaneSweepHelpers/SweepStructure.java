package Algorithms.PlaneSweepHelpers;

import java.util.ArrayList;

public class SweepStructure {
    //List of active Objects
    private ArrayList<PlaneSweepItalyLocation> activeObjects = new ArrayList<>();

    public boolean isEmpty(){
        return activeObjects.isEmpty();
    }

    //Add an Object to the list of active Objects
    public void insert(PlaneSweepItalyLocation obj1){
        activeObjects.add(obj1);
    }

    public void removeInactive(PlaneSweepItalyLocation obj1){
        //Initial list is empty
        ArrayList<PlaneSweepItalyLocation> newActiveObjects = new ArrayList<>();
        //Loop over all Objects in activeObjects list
        //removes from the active set all Objects that do NOT overlap a given Object
        for(int i = 0; i< activeObjects.size(); i++){
            PlaneSweepItalyLocation obj2 = activeObjects.get(i);
            //If Object is not behind sweep-line: add it to new list
            if(obj2.right >= obj1.left){
                newActiveObjects.add(obj2);
            }
        }
        //Update activeObjects
        activeObjects = newActiveObjects;
    }

    //Return all Objects that intersect with a given Object.
    public ArrayList<PlaneSweepItalyLocation> search(PlaneSweepItalyLocation obj1) {
        //Initial list is empty
        ArrayList<PlaneSweepItalyLocation> intersectingObjects = new ArrayList<>();
        //Loop over all active Objects
        for(int i = 0; i< activeObjects.size(); i++){
            PlaneSweepItalyLocation obj2 = activeObjects.get(i);
            //If Object intersects with given Object: add it to list
            if(obj2.intersects(obj1)){
                intersectingObjects.add(obj2);
            }
        }
        //Return the intersecting Objects.
        return intersectingObjects;
    }
}