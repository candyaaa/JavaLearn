package github.candy.java.learn.ds.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int indexValue = arr[i];
            int target = i - 1;
            while (target >= 0 && indexValue <= arr[target]) {
                arr[target + 1] = arr[target];
                target--;
            }
            arr[target + 1] = indexValue;
        }
    }
}
