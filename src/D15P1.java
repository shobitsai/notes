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
    public static void solve(TreeNode root){
        if(root==null)return;
        if(root.left==null)return;
    
        solve(root.left);
    
        root.left.right = root;
        root.left.left = root.right;
    
        root.left = null;
        root.right = null;
    
        return;
    }

    public static TreeNode getNewRoot(TreeNode root){
        if(root==null)return null;
        if(root.left==null && root.right==null)return root;
        
        TreeNode left=getNewRoot(root.left);
        if(left!=null)return left;
        return null;
    }
    public static void bfs(TreeNode root){
         Queue<TreeNode> q=new LinkedList<>();
         q.add(root);
         while(!q.isEmpty()){
             int size=q.size();
             for(int i=0;i<size;i++){
                 TreeNode curr=q.poll();
                 System.out.print(curr.val+" ");
                 if(curr.left!=null)q.add(curr.left);
                 if(curr.right!=null)q.add(curr.right);
             }
         }
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
        
        TreeNode newRoot=getNewRoot(root);
        // System.out.pri(newRoot.val);
        solve(root);
        bfs(newRoot);
    }   
}