package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSelect {
    Logger LOGGER = LoggerFactory.getLogger("QuickSelect");

    public static void main(String[] args) {
        QuickSelect qs = new QuickSelect();
        qs.solve();
    }

    public void solve() {
        int[] arr = new int[]{4, 2, 3, 1, 5};
        LOGGER.info("result is {}", quickSelect(arr, 0));
    }

    public int quickSelect(int[] arr, int k) {
        int index = partition(arr, 0, arr.length - 1);
        int low = 0;
        int high = arr.length - 1;
        while (index != k) {
            if (index < k) {
                low = index + 1;
            } else {
                high = index - 1;
            }
            index = partition(arr, low, high);
        }
        return arr[k];
    }

    public int partition(int[] arr, int l, int r) {
       int pivot = arr[l];
       int start = l;
       int index = start;
       for (int i=start+1; i<=r; i++) {
           if (pivot > arr[i]) {
               start++;
               swap(arr, start, i);
               LOGGER.info("Current arr is {}", arr);
           }
       }
       swap(arr, index, start);
        LOGGER.info("Current arr is {}", arr);
        LOGGER.info("Returned index is {}", index);
        return index;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
