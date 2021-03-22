package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class HeapSort {
    private static final Logger LOGGER = LoggerFactory.getLogger("HeapSort");

    public void solve() {
        int[] arr = {9, 8, 7, 8, 1, 10};
        heapSort(arr);
    }

    private void maxify(int[] arr, int index, int end) {
        int left = index << 1;
        int right = (index * 2) + 1;
        if (left > end) {
            return;
        }
        if (end > right) {
            if (arr[right - 1] > arr[left - 1]) {
                if (arr[index - 1] < arr[right - 1]) {
                    int tmp = arr[index -1];
                    arr[index - 1] = arr[right - 1];
                    arr[right - 1] = tmp;
                    maxify(arr, right, end);
                }
            } else {
                if ((arr[left - 1] > arr[index - 1])) {
                    int tmp = arr[index - 1];
                    arr[index - 1] = arr[left - 1];
                    arr[left - 1] = tmp;
                    maxify(arr, left, end);
                }
            }
        } else {
            if (arr[index - 1] < arr[left - 1]) {
                int tmp = arr[index - 1];
                arr[index - 1] = arr[left - 1];
                arr[left - 1] = tmp;
                maxify(arr, left, end);
            }
        }
    }

    private void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2; i > 0; i--) {
            maxify(arr, i, arr.length + 1);
        }
    }

    private void heapSort(int[] arr) {
        buildMaxHeap(arr);
        for (int i = arr.length; i > 0; i--) {
            int tmp = arr[i - 1];
            arr[i - 1] = arr[0];
            arr[0] = tmp;
            maxify(arr, 1, i - 1);
        }
    }
}
