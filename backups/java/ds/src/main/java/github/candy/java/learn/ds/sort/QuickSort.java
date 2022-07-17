package github.candy.java.learn.ds.sort;

/**
 * 快速排序
 */
public class QuickSort {
    public static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int l = low, r = high;
        // 找到基数
        int middle = (l + r) / 2;
        while (l < r) {

            // 在右边找个小于基数的树
            while (arr[r] >= arr[middle] && l < r) {
                r--;
            }

            // 在左边找到个大于基数的数
            while (arr[l] <= arr[middle] && l < r) {
                l++;
            }


            // 如果满足条件，则交换
            if (l < r) {
                int temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }
        }
        int temp = arr[middle];
        arr[middle] = arr[l];
        arr[l] = temp;

        // 递归左边
        sort(arr, low, l - 1);
        // 递归右边
        sort(arr, l + 1, r);
    }
}
