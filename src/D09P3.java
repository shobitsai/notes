//Given the in-order and post-order traversals of a binary tree, construct
//the original binary tree.
//
//The output should list the nodes in the order they appear in pre-order.
//
//Input Format:
//        -------------
//An integer N representing the number nodes.
//A space-separated list of N integers, the nodes in in-order traversal.
//A space-separated list of N integers, the nodes in post-order traversal.
//
//Output Format:
//        --------------
//Print the list of the nodes in pre-order.
//
//
//Sample Input:
//        -------------
//        7
//        4 2 5 1 6 3 7
//        4 5 2 6 7 3 1
//
//Sample Output:
//        --------------
//        [1, 2, 4, 5, 3, 6, 7]
//
//Explanation:
//        1
//        / \
//        2   3
//        / \  / \
//        4   5 6  7
//

import java.util.*;

class tnode {
    int val;
    tnode left, right;

    tnode(int n) {
        this.val = n;
        this.left = null;
        this.right = null;
    }
}

class D09P3 {
    public static int[] inx(int n, Scanner sc) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        return arr;
    }

    public static tnode conquer(int[] inorder, int a, int b, int[] postorder, int x, int y) {
        if (a > b || x > y)
            return null;

        tnode root = new tnode(postorder[y]);

        int r = 0;
        for (int i = a; i <= b; i++) {
            if (inorder[i] == postorder[y]) {
                r = i;
                break;
            }
        }

        int leftSize = r - a;

        root.left = conquer(inorder, a, r - 1, postorder, x, x + leftSize - 1);
        root.right = conquer(inorder, r + 1, b, postorder, x + leftSize, y - 1);

        return root;
    }

    public static void preorder(tnode x) {
        if (x == null)
            return;

        System.out.print(x.val + " ");
        preorder(x.left);
        preorder(x.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] inorder = inx(n, sc);
        int[] postorder = inx(n, sc);

        tnode root = conquer(inorder, 0, n - 1, postorder, 0, n - 1);
        preorder(root);
    }
}
