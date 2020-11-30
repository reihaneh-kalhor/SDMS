package Algorithms.PlaneSweepHelpers;

import java.util.ArrayList;

public class SweepStructure {
    //List of active Rectangles
    ArrayList<Rectangle> activeRectangles = new ArrayList<>();

    //Add a Rectangle to the list of active Rectangles
    public void insert(Rectangle rect){
        activeRectangles.add(rect);
    }

    public void removeInactive(Rectangle rect){
        //Initial list is empty
        ArrayList<Rectangle> newActiveRectangles = new ArrayList<>();
        //Loop over all Rectangles in activeRectangles list
        //removes from the active set all rectangles that do NOT overlap a given rectangle
        for(int i = 0; i< activeRectangles.size(); i++){
            Rectangle rect2 = activeRectangles.get(i);
            //If rectangle does not intersect with given Rectangle: add it to new list
            if(rect2.intersects(rect)){
                newActiveRectangles.add(rect2);
            }
        }
        //Update activeRectangles
        activeRectangles = newActiveRectangles;
    }

    //Return all Rectangles that intersect with a given rectangle.
    public ArrayList<Rectangle> search(Rectangle rect) {
        //Initial list is empty
        ArrayList<Rectangle> intersectingRectangles = new ArrayList<>();
        //Loop over all active Rectangles
        for(int i = 0; i< activeRectangles.size(); i++){
            Rectangle rect2 = activeRectangles.get(i);
            //If rectangle intersects with given Rectangle: add it to list
            if(rect2.intersects(rect)){
                intersectingRectangles.add(rect2);
            }
        }
        //Return the intersecting Rectangles.
        return intersectingRectangles;
    }
}