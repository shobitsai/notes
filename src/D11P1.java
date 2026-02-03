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
        List<Integer> res=new ArrayList<>();
        res.add(root.val);
        while (!q.isEmpty() && ind < arr.length) {
            int size = q.size();
            int temp = -1;
        
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
        
                if (ind < arr.length && arr[ind] != -1) {
                    curr.left = new TreeNode(arr[ind]);
                    q.add(curr.left);
                    temp = curr.left.val;
                }
                ind++;
        
                if (ind < arr.length && arr[ind] != -1) {
                    curr.right = new TreeNode(arr[ind]);
                    q.add(curr.right);
                    temp = curr.right.val;
                }
                ind++;
            }
        
            if (temp != -1) res.add(temp);
        }

        System.out.println(res);
        
    }
}