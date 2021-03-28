package org.example.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序测试
 */
public class SelectionSortTest {
    @Test
    public void sort() {
        int[] arr = new int[]{11, -79, 999, 85652, 9999};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
