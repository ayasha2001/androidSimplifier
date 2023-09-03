package com.example.myapplication.utils;


import com.example.myapplication.dto.EquationTableDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

    //utility class to organize the list
    public static List<String> filterAndOrganizeByDate(List<EquationTableDto> equationList) {
        Map<String, List<String>> equationMap = new HashMap<>();

        // Iterate through the input list and group equations by date
        for (EquationTableDto equation : equationList) {
            String date = equation.getDate();
            String expression = equation.getEquation();

            // Check if the date is already in the map
            if (equationMap.containsKey(date)) {
                // If it is, add the expression to the existing list
                equationMap.get(date).add(expression);
            } else {
                // If it's not, create a new list and add the expression
                List<String> expressionList = new ArrayList<>();
                expressionList.add(expression);
                equationMap.put(date, expressionList);
            }
        }

        // Creating the final list with dates followed by expressions
        List<String> organizedList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : equationMap.entrySet()) {
            String date = entry.getKey();
            List<String> expressions = entry.getValue();

            // Add date to the list
            organizedList.add(date);

            // Add expressions corresponding to the date
            organizedList.addAll(expressions);
        }

        return organizedList;
    }
}
