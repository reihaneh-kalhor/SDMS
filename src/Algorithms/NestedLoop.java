package Algorithms;

//procedure NESTED_LOOP_JOIN(setA, setB, joinCondition);
//begin
//    for each a ∈ setA
//        for each b ∈ SetB
//            if SATISFIED(a, b, joinCondition) then
//                REPORT(a, b);
//            end if;
//        next b;
//    next a;
//end;


import Algorithms.GeometryHelpers.GeometryComparison;
import Italy.*;
import java.util.ArrayList;

public class NestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    // join if community intersects railway
    public void join(ArrayList<Community> communities, ArrayList<Railway> railways) {

        for (Railway r : railways) {
            String rail = r.getGeo_wkt();

            for (Community c : communities) {
                String comm = c.getGeo_wkt();
                if (geo.compareShapesIntersection(rail, comm)) { // if the railway and community's geometry have at least one point in common
                    System.out.println(r.getName() + " and " + c.getComm_name() + " intersect!");
                }
            }
        }
    }

    // join communities that intersect
    public void join(ArrayList<Community> communities) {

        for (Community c1 : communities) {
            String comm1 = c1.getGeo_wkt();

            for (Community c2 : communities) {
                String comm2 = c2.getGeo_wkt();
                if (geo.compareShapesIntersection(comm1, comm2)) { // if communities' geometry have at least one point in common
                    System.out.println(c1.getComm_name() + " and " + c2.getComm_name() + " intersect!");
                }
            }
        }
    }

    // generic join, give any two tables and one attribute, return joined result
    public ArrayList<ArrayList<String>> join(ArrayList<ItalyLocation> table1, ArrayList<ItalyLocation> table2, String attribute) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (hasAttribute(table1, attribute) && hasAttribute(table2, attribute)) {

            for (ItalyLocation n1 : table1) {
                int attIdx1 = n1.getColumns().indexOf(attribute);
                ArrayList<String> values1 = n1.getValuesAsList();
                String attVal1 = n1.getValuesAsList().get(attIdx1);

                for (ItalyLocation n2 : table2) {
                    int attIdx2 = n2.getColumns().indexOf(attribute);
                    ArrayList<String> values2 = n2.getValuesAsList();
                    String attVal2 = n2.getValuesAsList().get(attIdx2);

                    if (attVal1.equals(attVal2)) { // if value of row from table1 is same as value of row from table2, join both rows
                        values2.remove(attIdx2);
                        values1.addAll(values2);
                        result.add(values1);
                        System.out.println("joined: " + values1.toString() + "\n");
                    }
                }
            }
        } else {
            System.out.println("Tried joining two tables but at least one table does not have a column named: " + attribute);
        }
        return result;
    }

    private boolean hasAttribute(ArrayList<ItalyLocation> table, String attribute) {
        return (table.size() > 0 && table.get(0).getColumns().contains(attribute));
    }
}
