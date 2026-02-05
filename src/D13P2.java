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
    static int res=Integer.MAX_VALUE;
    static int calcLength(TreeNode root, int tgt){
        if (root == null) return Integer.MAX_VALUE;
        if (root.val == tgt) return 0;
    
        int left = calcLength(root.left, tgt);
        int right = calcLength(root.right, tgt);
    
        int min = Math.min(left, right);
        return min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
    }
    public static TreeNode solve(TreeNode root,int src,int tgt){
        if(root==null)return null;
        if(root.val==src){
            res = Math.min(res, calcLength(root, tgt));
            return root;
        }
        if(root.val==tgt){
            res = Math.min(res, calcLength(root, src));
            return root;
        }
        TreeNode left=solve(root.left,src,tgt);
        TreeNode right=solve(root.right,src,tgt);
        if(left!=null && right!=null){
            res=Math.min(res,calcLength(root,src)+calcLength(root,tgt));
            return null;
        }
        if(left==null)return right;
        return left;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Queue<TreeNode> q=new LinkedList<>();
        String[] tempp=sc.nextLine().split(" ");
        int src=sc.nextInt();
        int tgt=sc.nextInt();
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
        solve(root,src,tgt);
        System.out.println(res==Integer.MAX_VALUE?0:res);
        

        
    }
}