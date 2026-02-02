//You are the chief designer for a futuristic garden trail, where there are N
//consecutive decorative light posts along a path. Each post can be illuminated
//with one of three distinct colors: Crimson, Azure, or Emerald. The cost to
//illuminate each post with a particular color depends on the post's location
//and the energy circuit required.
//
//        However, to maintain aesthetic harmony and prevent power circuit overloads,
//no two adjacent posts can have the same light color.
//
//The cost of illuminating each post with each color is provided in a 2D array
//costs, where costs[i][0] is the cost of lighting post i with Crimson, costs[i][1]
//        with Azure, and costs[i][2] with Emerald.
//
//Your goal is to find the minimum total cost to light up all posts in such a way
//that no two neighboring posts have the same color.
//
//Input Format:
//        -------------
//Line-1: An integer N, number of post.
//Next N lines: 3 space separated integers, cost of illuminating the posts.
//
//Output Format:
//        --------------
//Print an integer, minimum total cost to light up all posts.
//
//
//Sample Input:
//        ---------------
//        3
//        17 2 17
//        16 4 5
//        14 3 19
//
//Sample Output:
//        ----------------
//        10
//
//Explanation:
//        ------------
//        1st post is with Azure, 2nd post is with Emerald,
//        3rd post is with Crimson.
//Minimum cost: 2 + 5 + 3 = 10.

import java.util.*;

class D06P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] cost = new int[n][3];
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++)
                cost[i][j] = sc.nextInt();
        }

        sc.close();

        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        int result = Math.min(dp[n - 1][0],Math.min(dp[n - 1][1], dp[n - 1][2]));

        System.out.println(result);
    }
}
