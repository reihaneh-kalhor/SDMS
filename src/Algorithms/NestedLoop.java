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
import GeographicalLocation.*;

import java.util.ArrayList;

public class NestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    // TODO find way to generalize join condition (now: 'equals' for strings and 'intersects' for geo)
    // generic join, give any two tables and one attribute, return joined result
    public ArrayList<ArrayList<String>> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2, String attribute) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (hasAttribute(table1, attribute) && hasAttribute(table2, attribute)) {

            for (GeographicalLocation n1 : table1) {
                int attIdx1 = n1.getColumns().indexOf(attribute);
                ArrayList<String> values1 = n1.getValuesAsList();
                String attVal1 = values1.get(attIdx1);

                for (GeographicalLocation n2 : table2) {
                    int attIdx2 = n2.getColumns().indexOf(attribute);
                    ArrayList<String> values2 = n2.getValuesAsList();
                    String attVal2 = values2.get(attIdx2);

                    if (attribute.equals("geo_wkt") && geo.compareShapesIntersection(attVal1, attVal2)) { // if joining on geometry, compare shapes
                        values2.remove(attIdx2);
                        values1.addAll(values2);
                        result.add(values1); // merge values
                    } else if (attVal1.equals(attVal2)) { // else, compare strings
                        values2.remove(attIdx2);
                        values1.addAll(values2);
                        result.add(values1); // merge values
//                        System.out.println("joined: " + values1.toString());
                    }
                }
            }
        } else {
            System.out.println("Tried joining two tables but at least one table does not have a column named: " + attribute);
        }
        return result;
    }



    // generic join, give any two tables and two attributes, return joined result
    public ArrayList<ArrayList<String>> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2, String attribute1, String attribute2) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (hasAttribute(table1, attribute1)) {
            if (hasAttribute(table2, attribute2)) {

                for (GeographicalLocation n1 : table1) {
                    int attIdx1 = n1.getColumns().indexOf(attribute1);
                    ArrayList<String> values1 = n1.getValuesAsList();
                    String attVal1 = n1.getValuesAsList().get(attIdx1);

                    for (GeographicalLocation n2 : table2) {
                        int attIdx2 = n2.getColumns().indexOf(attribute2);
                        ArrayList<String> values2 = n2.getValuesAsList();
                        String attVal2 = n2.getValuesAsList().get(attIdx2);

                        if (attribute1.equals("geo_wkt") && attribute2.equals("geo_wkt") && geo.compareShapesIntersection(attVal1, attVal2)) { // if joining on geometry, compare shapes
                            values2.remove(attIdx2);
                            values1.addAll(values2);
                            result.add(values1); // merge values
                        } else if (attVal1.equals(attVal2)) { // else, compare strings
                            values2.remove(attIdx2);
                            values1.addAll(values2);
                            result.add(values1); // merge values
//                        System.out.println("joined: " + values1.toString());
                        }
                    }
                }
            } else {
                System.out.println("Tried joining but table2 does not have a column named: " + attribute2);
            }
        } else {
            System.out.println("Tried joining but table1 does not have a column named: " + attribute1);
        }
        return result;
    }

    private boolean hasAttribute(ArrayList<GeographicalLocation> table, String attribute) {
        return (table.size() > 0 && table.get(0).getColumns().contains(attribute));
    }
}
