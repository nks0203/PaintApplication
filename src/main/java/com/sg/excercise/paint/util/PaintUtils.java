package com.sg.excercise.paint.util;

public class PaintUtils {

    public static void areAllPointsPositive(int... nums) {
        for (int num : nums) {
            if (num < 1) {
                throw new IllegalArgumentException("Passed points should be greater than 0. Please try again");
            }
        }
    }

}
