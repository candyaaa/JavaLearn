package github.candy.java.learn.ds.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubblingSortTest {
    @Test
    public void sort(){
        int[] arr = new int[]{11, -79, 999, 85652, 9999};
        BubblingSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
