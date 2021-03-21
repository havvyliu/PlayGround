package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Algorithms {
    static Logger LOGGER = LoggerFactory.getLogger(Algorithms.class);

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        int[] arr2 = {3, 2};

//         MergeSort.mergeSort(arr2);
//        MaxSubarray solver = new MaxSubarray();
//        solver.solve();

        HeapSort solver = new HeapSort();
        solver.solve();
    }

    /**
     *
     * @param ints
     */
    public static void insertionSort(int[] ints) {
        LOGGER.info("The input arr is {}", ints);
        if (ints == null || ints.length == 1) {
            return;
        }
        for (int i = 0; i < ints.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j > 0; j--) {
                if (ints[j] < ints[index]) {
                    int key = ints[index];
                    ints[index] = ints[j];
                    ints[j] = key;
                    LOGGER.info("The input arr is {}", ints);
                    index--;
                }
            }
        }
        LOGGER.info("The output arr is {}", ints);
    }

    public static class MergeSort {

        public static void merge(int[] arr, int p, int q, int r) {
            LOGGER.info("p = {}, q = {}, r = {}", p, q, r);
            final int[] left = Arrays.copyOfRange(arr, p, q + 1);
            final int[] right = Arrays.copyOfRange(arr, q + 1, r + 1);
            LOGGER.info("Left is {}", left);
            LOGGER.info("Right is {}", right);
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
            LOGGER.info("Current arr is {}", arr);
        }

        public static void mergeSort(int[] arr, int p, int r) {
            if (r > p) {
                int q = (r + p)/2;
                mergeSort(arr, p, q);
                mergeSort(arr, q + 1, r);
                merge(arr, p, q, r);
            }
        }

        public static void mergeSort(int[] arr) {
            mergeSort(arr, 0, arr.length - 1);
        }
    }
}
