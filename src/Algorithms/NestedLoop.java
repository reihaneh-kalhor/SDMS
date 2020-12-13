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
import GeographicalLocation.GeographicalLocation;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.HashSet;

public class NestedLoop {
    private GeometryComparison geo = new GeometryComparison();

    // Geometric join, given any two tables and no attribute, join on geometries
    public HashSet<String> joinGeometries(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2) {
        HashSet<String> result = new HashSet<>();

        for (GeographicalLocation n1 : table1) {
            String geom1 = n1.getGeoWKT();                              // get Geometry of row1

            for (GeographicalLocation n2 : table2) {
                Geometry geom2 = n2.getGeometry();                          // get Geometry of row2

                if (geo.compareShapesIntersection(geom1, geom2)) {          // check if Geometries intersect
                    result.add(n2.getValuesAsList().toString().concat(n1.getValuesAsList().toString()));    // merge rows
                }
            }
        }
        return result;
    }

    // generic join, give any two tables and one attribute, return joined result
    public ArrayList<ArrayList<String>> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2, String attribute) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (hasAttribute(table1, attribute) && hasAttribute(table2, attribute)) {

            for (GeographicalLocation n1 : table1) {
                int attIdx1 = n1.getColumns().indexOf(attribute);           // index of attribute1 for table1 (= which column)
                ArrayList<String> values1 = n1.getValuesAsList();           // get row
                String attVal1 = values1.get(attIdx1);                      // get value of field

                for (GeographicalLocation n2 : table2) {
                    int attIdx2 = n2.getColumns().indexOf(attribute);       // index of attribute2 for table2 (= which column)
                    ArrayList<String> values2 = n2.getValuesAsList();       // get row
                    String attVal2 = values2.get(attIdx2);                  // get value of field

                    if (attVal1.equals(attVal2)) {                          // compare fields
                        values2.remove(attIdx2);
                        values1.addAll(values2);                            // merge rows
                        result.add(values1);
                    }
                }
            }
        } else {
            System.out.println("Tried joining two tables but at least one table does not have a column named: " + attribute);
        }
        return result;
    }

    // generic join, give any two tables and two attributes (attribute1 for table1, attribute2 for table2), return joined result
    public ArrayList<ArrayList<String>> join(ArrayList<GeographicalLocation> table1, ArrayList<GeographicalLocation> table2, String attribute1, String attribute2) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (hasAttribute(table1, attribute1)) {
            if (hasAttribute(table2, attribute2)) {

                for (GeographicalLocation n1 : table1) {
                    int attIdx1 = n1.getColumns().indexOf(attribute1);      // index of attribute1 for table1 (= which column)
                    ArrayList<String> values1 = n1.getValuesAsList();       // get row
                    String attVal1 = n1.getValuesAsList().get(attIdx1);     // get value of field

                    for (GeographicalLocation n2 : table2) {
                        int attIdx2 = n2.getColumns().indexOf(attribute2);  // index of attribute2 for table2 (= which column)
                        ArrayList<String> values2 = n2.getValuesAsList();   // get row
                        String attVal2 = n2.getValuesAsList().get(attIdx2); // get value of field

                        if (attVal1.equals(attVal2)) {                      // compare fields
                            values2.remove(attIdx2);
                            values1.addAll(values2);                        // merge rows
                            result.add(values1);
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
