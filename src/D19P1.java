import java.util.*;
class TreeNode {
    Integer val;
    TreeNode left, right;
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//       / \
//       2   6
//      / \  /
//     3   1 5
class Solution{
    public static TreeNode build(int l,int r,String s){
        if(l>r)return null;
        // System.out.println(s.substring(l,r+1));
        if(l==r)return new TreeNode(Integer.parseInt(s.charAt(l)+""));
        int num=0;
        int i=l;
        boolean minus=false;
        if(s.charAt(i)=='-'){
            minus=true;
            i++;
        }
        while(i<s.length() && Character.isDigit(s.charAt(i))){
            if(s.charAt(i)=='-')minus=true;
            num=num*10+(s.charAt(i)-'0');
            i++;
        }
        if(minus){
            num=-num;
            // System.out.println(num);
        }
        TreeNode root=new TreeNode(num);
        if(i>r)return root;
        
        int count=0;
        int start=i;
        for(;start<=r;start++){
            if(s.charAt(start)==')')count--;
            if(s.charAt(start)=='(')count++;
            if(count==0)break;
        }
        root.left=build(i+1,start-1,s);
        root.right=build(start+2,r-1,s);
        
        return root;
        
    }
    public static void level(TreeNode root){
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode curr=q.poll();
                System.out.print(curr.val+" ");
                if(curr.left!=null)q.add(curr.left);
                if(curr.right!=null)q.add(curr.right);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        TreeNode root= build(0,s.length()-1,s);
        level(root);
        
    }
}