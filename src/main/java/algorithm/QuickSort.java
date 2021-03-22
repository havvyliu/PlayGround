package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class QuickSort {
    private static final Logger LOGGER = LoggerFactory.getLogger("quickSort");

    public void solve() {
        int[] arr = {0, 10, 3, 4, 5, 9};
        quickSort(arr, 0, arr.length - 1);
        LOGGER.info(Arrays.toString(arr));
    }

    public int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (pivot < arr[j]) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                i++;
            }
        }
        arr[r] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public void quickSort(int[] arr, int p, int r) {
        if (r > p) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }
}
