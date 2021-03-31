package org.example.sort;


import java.util.Arrays;

public class BubblingSort {//  11、8、1、79
    public static void sort(int[] arr) {
        for (int j = arr.length; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
}
