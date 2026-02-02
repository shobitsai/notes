import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}

class D08P4 {
    public static TreeNode buildTree(int[] arr, int n) {
        if (n <= 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;

        while (i < n && !q.isEmpty()) {
            TreeNode x = q.poll();

            if (arr[i] != -1) {
                x.left = new TreeNode(arr[i]);
                q.offer(x.left);
            }
            i++;

            if (i < n && arr[i] != -1) {
                x.right = new TreeNode(arr[i]);
                q.offer(x.right);
            }
            i++;
        }

        return root;
    }

    public static void inorder(TreeNode x) {
        if (x == null)
            return;

        inorder(x.left);
        System.out.print(x.val + " ");
        inorder(x.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String[] parts = s.split(" ");
        int n = parts.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(parts[i]);

        TreeNode root = buildTree(arr, n);
        inorder(root);
    }
}