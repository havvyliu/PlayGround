package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class Node {
    int val;
    Node next;
}

public class Algorithms {
    static Logger LOGGER = LoggerFactory.getLogger(Algorithms.class);

    public static void main(String[] args) {
        binarySearchWithLeftShift(3);
        binarySearchWithRightShift(3);
        Node node = new Node();
    }
    public static int monotoneStackMax(int[] arr) {
        Stack<Integer>  leftMax = new Stack<>();
        Stack<Integer> rightMax = new Stack<>();
        Stack<Integer> leftMin = new Stack<>();
        Stack<Integer> rightMin = new Stack<>();
        int[] lmArr = new int[arr.length], rmArr = new int[arr.length], lminArr = new int[arr.length], rminArr = new int[arr.length];

        for (int i=0; i<arr.length; i++) {
            lmArr[i] = lminArr[i] = i + 1;
            rmArr[i] = rminArr[i] = arr.length - i;
        }

        for (int i=0; i<arr.length; i++) {
            while (leftMax.size() > 0 && arr[leftMax.peek()] < arr[i]) {
                leftMax.pop();
            }
            lmArr[i] = leftMax.size() > 0 ? i - leftMax.peek(): lmArr[i];
            leftMax.add(i);

            while (rightMax.size() > 0 && arr[rightMax.peek()] < arr[i]) {
                rmArr[rightMax.peek()] = i - rightMax.peek();
                rightMax.pop();
            }
            rightMax.add(i);

            while (leftMin.size() > 0 && arr[leftMin.peek()] > arr[i]) {
                leftMin.pop();
            }
            lminArr[i] = leftMin.size() > 0 ? i - leftMin.peek(): i + 1;
            leftMin.add(i);

            while (rightMin.size() > 0 && arr[rightMin.peek()] > arr[i]) {
                rminArr[rightMin.peek()] = i - rightMin.peek();
                rightMin.pop();
            }
            rightMin.add(i);

        }

        int res = 0;
        for (int i=0; i<arr.length; i++) {
            res += arr[i] * lmArr[i] * rmArr[i] - arr[i] * rminArr[i] * lminArr[i];
        }
        return res;
    }

    private static String addBinary(String str1, String str2) {
        int c = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String a = new StringBuilder(str1).reverse().toString();
        String b = new StringBuilder(str2).reverse().toString();
        while (i < a.length() || i < b.length()) {
            if (i < a.length() && i < b.length()) {
                int a1 = a.charAt(i) - '0';
                int b1 = b.charAt(i) - '0';
                sb.append((a1 + b1 + c) % 2);
                c = (a1 + b1 + c) / 2;
            } else if (i < a.length()) {
                int a1 = a.charAt(i) - '0';
                sb.append((a1 + c) % 2);
                c = (a1 + c) / 2;
            } else {
                int b1 = b.charAt(i) - '0';
                sb.append((b1 + c) % 2);
                c = (b1 + c) / 2;
            }
            i++;
        }
        if (c == 1) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

    private void charCheck() {
        char c = 'C';
        boolean isLetter = Character.isLetter(c);
    }

    private static void binarySearchWithRightShift(int target) {
        int[] arr = new int[]{0, 1, 3, 3, 3, 4, 5};
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
            LOGGER.info("m is {}, l is {}, r is {}", m, l ,r);
        }
        LOGGER.info("Result for maximum is {}", l - 1);
    }

    private static void binarySearchWithLeftShift(int target) {
        int[] arr = new int[]{0, 1, 3, 3, 3, 4, 5};
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
            LOGGER.info("m is {}, l is {}, r is {}", m, l ,r);
        }
        LOGGER.info("Result for minimum is {}", l);
    }

    private static void binarySearch(int target) {
        int[] arr = new int[]{0, 1, 3, 3, 3, 4};
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            LOGGER.info("Current l is {}, r is {}", l ,r);
            int m = (l + r) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        LOGGER.info("Final l is {}, r is {}", l , r);
    }

    private static void add(int count) {
        count++;
    }

    public long getTotal(long ii, long jj, long orderr) {
        double i = (double) ii;
        double j = (double) jj;
        double order =  (double) orderr;
        double diff = i - j;
        if (diff == 0) return (long) (i * order);
        if (diff == 1) return (long) ((i + j) * order);
        double r = diff%2;
        if (r == 0) {
            return (long) ((i + j) * (i - j + 1.) / 2 * order);
        } else {
            return (long) (((i + j - 1) * (i - j + 1) / 2 + j) * order);
        }
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

}
