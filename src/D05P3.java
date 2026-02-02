//You are a robot explorer navigating a vast digital maze stored as a string of
//digits. Each digit or pair of digits on the path represents a movement instruction,
//which translates to a specific direction using the following map:
//
//        "1" → Move 'A'
//
//        "2" → Move 'B'
//
//        ...
//
//        "26" → Move 'Z'
//
//However, the maze has tricky encoding rules. Sometimes a single digit can be
//        read alone, and sometimes two digits together form a valid move — but not every
//combination is valid. For example, "05" is invalid, while "5" is fine. Your
//robot can only navigate using valid instruction steps, and you must find how
//many unique navigation sequences the robot can follow to reach the end of the maze.
//
//Given the string s of digits, determine the total number of distinct ways the
//robot can interpret and follow the path from start to end without making an
//invalid move.
//
//If no valid navigation is possible, return 0.
//
//
//Input Format:
//        -------------
//A string s.
//
//Output Format:
//        --------------
//Print an integer result.
//
//
//Sample Input-1:
//        ---------------
//        123
//
//Sample Output-1:
//        ----------------
//        3
//
//Explanation:
//        ------------
//        123 can be converted as: ABC, LC, AW
//
//
//Sample Input-2:
//        ---------------
//        326
//
//Sample Output-2:
//        ----------------
//        2
//
//Explanation:
//        ------------
//        326 can be converted as: CBF, CZ

import java.util.*;

class D05P3 {
    public static int getAll(String s, int i, int n, HashMap<Integer, Integer> map) {
        if (i == n) {
            return 1;
        }

        if (map.containsKey(i))
            return map.get(i);

        int ans = 0;

        char ch1 = s.charAt(i);

        if (ch1 == '0')
            return 0;

        ans += getAll(s, i + 1, n, map);

        if (ch1 < '3' && i + 1 < n) {
            char ch2 = s.charAt(i + 1);

            if (ch1 == '1' || ch2 <= '6')
                ans += getAll(s, i + 2, n, map);
        }

        map.put(i, ans);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();

        HashMap<Integer, Integer> map = new HashMap<>();
        int n = s.length();

        System.out.println(getAll(s, 0, n, map));
    }
}