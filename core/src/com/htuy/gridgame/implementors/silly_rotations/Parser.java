package com.htuy.gridgame.implementors.silly_rotations;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Pair<Integer, List<Transform>> parse(String input) {
        List<Transform> res = new ArrayList<>();
        input = input.replaceAll("\\s", "");
        char[] results = input.toCharArray();
        int x = 0;
        int sides = results[0];
        x += 1;
        while (x < results.length) {
            if (results[x] == 'r') {
                x += handleR(res, results, x);
            } else if (results[x] == 'f') {
                res.add(new Transform.ReflectTransform());
                x++;
            }
        }
        return new Pair(sides, res);
    }

    private static int handleR(List<Transform> res, char[] input, int x) {
        if (x + 1 > input.length) {
            res.add(new Transform.RotateTransform(1));
            return 1;
        }
        char nxt = input[x + 1];
        if (Character.isDigit(nxt)) {
            if (x + 2 > input.length) {
                res.add(new Transform.RotateTransform(Integer.parseInt("" + nxt)));
                return 2;
            } else {
                char nxtNxt = input[x + 2];
                if (Character.isDigit(nxtNxt)) {
                    res.add(new Transform.RotateTransform(Integer.parseInt("" + nxt + nxtNxt)));
                    return 3;
                }
                res.add(new Transform.RotateTransform(Integer.parseInt("" + nxt)));
                return 2;
            }
        } else {
            res.add(new Transform.RotateTransform(1));
            return 1;
        }
    }


}
