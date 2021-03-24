package algorithm;

public class RandomSelect {

    public void solve() {
        int[] arr = {4, 3, 2, 8, 5};
        int position = randomSelect(arr, 0, arr.length - 1, 0);
        System.out.println(position);
    }

    private int randomSelect(int[] arr, int p, int r, int q) {
        if (p == r) {
            return arr[p];
        }

        int partition = partition(arr, p, r);
        if (partition == q) {
            return arr[q];
        }
        if (partition >= q) {
            return randomSelect(arr, p, partition - 1, q);
        } else {
            return randomSelect(arr, partition + 1, r, q);
        }
    }

    private int partition(int[] arr, int l, int r) {
        int i = l;
        for (int k = l; k < r; k++) {
            if (arr[k] < arr[r]) {
                int tmp = arr[k];
                arr[k] = arr[l];
                arr[l] = tmp;
                i++;
            }
        }
        int lastElement = arr[r];
        arr[r] = arr[i];
        arr[i] = lastElement;
        return i;
    }
}
