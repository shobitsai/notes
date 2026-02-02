//There are N computers in a network, all the computers are connected as tree
//structure. And one new connection is added in the Network. The computers in
//the network are identified with their IDs, the IDs are numbered between 1 to N.
//
//The connections in the network is given as coonection[i] = [comp-A, comp-B],
//there is a connection between comp-A and comp-B.
//
//Your task is to remove a connection in the network and print it, so that
//all the computers are connected as tree structure. If there are multiple
//options to remove, remove the connection that occurs last in the input.
//
//
//Input Format:
//        -------------
//Line-1: Two space separated integers N, number of computers.
//Next N lines: Two space separated integers, comp-A & comp-B.
//
//Output Format:
//        --------------
//Print the connection which is removed.
//
//
//Sample Input-1:
//        ---------------
//        6
//        1 2
//        3 4
//        3 6
//        4 5
//        5 6
//        2 3
//
//Sample Output-1:
//        ---------------
//        5 6
//
//
//Sample Input-2:
//        ---------------
//        4
//        1 2
//        2 3
//        3 4
//        2 4
//
//Sample Output-2:
//        ---------------
//        2 4


import java.util.*;

class DSU {
    int[] parent, rank;

    DSU(int n) {
        this.parent = new int[n + 1];
        this.rank = new int[n + 1];

        for (int i = 1; i <= n; i++)
            parent[i] = i;

    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);

        return parent[x];
    }

    public boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa == fb) {
            return false;
        }

        if (rank[fa] < rank[fb]) {
            parent[fa] = fb;
        } else if (rank[fa] > rank[fb]) {
            parent[fb] = fa;
        } else {
            parent[fb] = fa;
            rank[fa]++;
        }

        return true;
    }
}

class D08P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] inputs = new int[n][2];
        for (int i = 0; i < n; i++) {
            inputs[i][0] = sc.nextInt();
            inputs[i][1] = sc.nextInt();
        }

        DSU dset = new DSU(n);
        int[] ans = new int[2];

        for (int i = 0; i < n; i++) {
            if (!dset.union(inputs[i][0], inputs[i][1])) {
                ans[0] = inputs[i][0];
                ans[1] = inputs[i][1];
            }
        }

        if (ans[0] != 0) {
            System.out.println(ans[0] + " " + ans[1]);
        }

    }
}