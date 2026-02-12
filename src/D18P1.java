import java.util.*;
class TreeNode {
    Integer val;
    TreeNode left, right;
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}
class Solution{
    static TreeNode newNode;
    public static void flatten(TreeNode root){
        if(root==null)return;
        flatten(root.left);
        flatten(root.right);
        if(root.left!=null){
            TreeNode temp=root.left;
            TreeNode leftie=root.left;
            while(leftie.right!=null)leftie=leftie.right;
            leftie.right=root.right;
            root.right=null;
            root.left=null;
            root.right=temp;
        }
    }
    public static void print(TreeNode root){
        if(root==null)return;
        System.out.println(root.val);
        print(root.right);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Queue<TreeNode> q=new LinkedList<>();
        String[] tempp=sc.nextLine().split(" ");
        int[] arr=new int[tempp.length];
        for(int i=0;i<tempp.length;i++)arr[i]=Integer.parseInt(tempp[i]);
        TreeNode root=new TreeNode(arr[0]);
        int ind=1;
        q.add(root);
        int p=1;
        while (!q.isEmpty() && ind < arr.length) {
            int size=q.size();
            int temp=-1;
            for (int i=0;i<size;i++) {
                TreeNode curr = q.poll();
                if(ind<arr.length && arr[ind]!=-1) {
                        curr.left = new TreeNode(arr[ind]);
                    q.add(curr.left);
                }
                ind++;
                if(ind<arr.length && arr[ind]!= -1) {
                    curr.right = new TreeNode(arr[ind]);
                    q.add(curr.right);
                }
                ind++;
            }
        }
        flatten(root);
        print(root);
    }
}