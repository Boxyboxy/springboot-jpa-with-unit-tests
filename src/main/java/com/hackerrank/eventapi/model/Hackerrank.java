package com.hackerrank.eventapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hackerrank {
  public static List<Integer> countTeams(List<Integer> ratings, List<List<Integer>> queries) {

    List<Integer> results = new ArrayList<Integer>();

    for (int i = 0; i < queries.size(); i++) {
      int l = queries.get(i).get(0);
      int r = queries.get(i).get(1);
      // each query will have a seen Map
      Map<Integer, Boolean> seen = new HashMap<>();
      int teams = 0;
      // Loop through ratings list
      for (int j = l - 1; j < r; j++) {
        int currRating = ratings.get(j);
        // if key is present in map
        if (seen.containsKey(currRating)) {
          // has it been seen before, if the flag is off, this is the second time seeing
          // it
          if (!seen.get(currRating)) {
            teams += 1;
            seen.replace(currRating, true);
            // if it has been seen before, a team has been formed, toggle flag to off
          } else if (seen.get(currRating)) {
            seen.replace(currRating, false);
          }
          // if key is not present, put the key and toggle flag to off, indicating that
          // this rating has been seen
        } else if (!seen.containsKey(currRating)) {
          seen.put(currRating, false);
        }
      }

      results.add(teams);

    }
    return results;
  }

  public static void main(String[] args) {
    List<Integer> ratings1 = new ArrayList<Integer>() {
      {
        add(2);
        add(3);
        add(4);
        add(2);
        add(2);
        add(2);
        add(2);
        add(2);
        add(3);
      }
    };

    final List<Integer> query1 = new ArrayList<Integer>() {
      {
        add(1);
        add(9);
      }
    };

    final List<Integer> query2 = new ArrayList<Integer>() {
      {
        add(3);
        add(4);
      }
    };
    List<List<Integer>> queries1 = new ArrayList<List<Integer>>() {
      {
        add(query1);
        add(query2);
      }
    };

    System.out.println(countTeams(ratings1, queries1));

  }
}
