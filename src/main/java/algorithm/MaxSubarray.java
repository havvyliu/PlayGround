package algorithm;

public class MaxSubarray {
    void solve() {
        int[] arr = {-1, -2};
        int result = findMaxSum(arr, 0, arr.length - 1);
        System.out.print(result);
    }
    
    int findMaxSumWithMid(int[] arr, int left, int right, int mid) {
        int sum = Integer.MIN_VALUE;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            if (i == mid) {
                sum = arr[i];
            } else {
                sum += arr[i];
            }
            if (leftSum < sum) {
                leftSum = sum;
            }
        }
        sum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        System.out.println("rightSum is " + rightSum);
        for (int i = mid + 1; i <= right; i++) {
            if (i == mid + 1) {
                sum = arr[i];
            } else {
                sum += arr[i];
            }
            if (rightSum < sum) {
                rightSum = sum;
            }
        }
        int result = Math.max(Math.max(rightSum, leftSum), rightSum + leftSum);
        System.out.println("local result is " + rightSum + ',' + leftSum + ',' + (rightSum + leftSum));
        return result;
    }

    int findMaxSum(int[] arr, int left, int right) {
        if (left == right) {
            return arr[0];
        }
        int mid = (left + right)/2;
        int currSum = findMaxSumWithMid(arr, left, right, mid);
        int leftSum = findMaxSum(arr, left, mid);
        int rightSum = findMaxSum(arr, mid + 1, right);

        return Math.max(Math.max(currSum, leftSum), rightSum);
    }
}

