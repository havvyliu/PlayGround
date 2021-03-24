package algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Algorithms {
    static Logger LOGGER = LoggerFactory.getLogger(Algorithms.class);

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        int[] arr2 = {3, 2};

        RandomSelect select = new RandomSelect();
        select.solve();
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
