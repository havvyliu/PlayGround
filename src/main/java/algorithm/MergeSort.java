package algorithm;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] arr, int p, int q, int r) {
        Algorithms.LOGGER.info("p = {}, q = {}, r = {}", p, q, r);
        final int[] left = Arrays.copyOfRange(arr, p, q + 1);
        final int[] right = Arrays.copyOfRange(arr, q + 1, r + 1);
        Algorithms.LOGGER.info("Left is {}", left);
        Algorithms.LOGGER.info("Right is {}", right);
        int i = 0;
        int j = 0;
        for (int k = p; k <= r; k++) {

            if (i >= left.length) {
                if (j < right.length) {
                    arr[k] = right[j];
                    j++;
                    continue;
                }
            }
            if (j >= right.length) {
                if (i < left.length) {
                    arr[k] = left[i];
                    i++;
                    continue;
                }
            }
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
        Algorithms.LOGGER.info("Current arr is {}", arr);
    }

    public static void mergeSort(int[] arr, int p, int r) {
        if (r > p) {
            int q = (r + p) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
}
