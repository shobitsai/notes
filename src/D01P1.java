// 12 Jan 2025

//A satellite scans a long strip of land divided into N zones.
//Each zone emits a certain energy value (positive or negative).
//
//The satellite scanner can analyze exactly k consecutive zones
//at a time. As the scanner moves from left to right (one zone at
//        a time), it computes the total energy of the current window.
//
//Given an integer array representing energy values and a window
//size k, return the maximum sum of any contiguous subarray of length k.
//
//Input Format:
//        -----------------
//Two integers N and K
//N space separated integers representing energy values
//
//Output Format:
//        ------------------
//An integer — maximum total energy
//
//
//Sample Input
//------------------
//        5 2
//        4 -1 2 10 -3
//
//Sample Output
//------------------
//        12
//
//Explanation
//---------------
//All possible subarrays of length K = 2:
//Subarray	Sum
//[4, -1]	3
//        [-1, 2]	1
//        [2, 10]	12 ✅
//        [10, -3]	7
//

import java.util.*;

class D01P1 {
    public static int findMax(int[] arr, int n, int k) {
        int x = 0, ans = Integer.MIN_VALUE;

        if (k == 0) return 0;

        for (int i = 0; i < k - 1; i++)
            x += arr[i];

        for (int i = k - 1; i < n; i++) {
            x += arr[i];
            ans = Math.max(x, ans);
            x -= arr[i - k + 1];
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println(findMax(arr, n, k));
    }
}