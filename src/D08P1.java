import java.util.*;

class D08P1 {
    public static int helper(int[] arr, int x, int n) {
        if (x >= n - 1)
            return 0;

        int ans = Integer.MAX_VALUE - 10;

        for (int i = x + 1; i < n && i <= x + arr[x]; i++) {
            ans = Math.min(helper(arr, i, n), ans);
        }

        return ans + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int ans = helper(arr, 0, n);

        System.out.println(ans);
    }
}