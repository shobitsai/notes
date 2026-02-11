import java.util.*;
class TreeNode {
    Integer val;
    TreeNode left, right;
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}
class Pair{
    TreeNode node;
    int dist;
    Pair(TreeNode node,int dist){
        this.node=node;
        this.dist=dist;
    }
}
class Solution{
    static TreeMap<Integer,List<Integer>> mp;
    public static void bfs(TreeNode root){
        Queue<Pair> q=new LinkedList<>();
        
        q.add(new Pair(root,0));
        while(!q.isEmpty()){
            Pair curr = q.poll(); 
            mp.putIfAbsent(curr.dist,new ArrayList<>());
            mp.get(curr.dist).add(curr.node.val);
            if(curr.node.left!=null)q.add(new Pair(curr.node.left,curr.dist-1));
            if(curr.node.right!=null)q.add(new Pair(curr.node.right,curr.dist+1));
        }
    }
    public static void main(String [] args){
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
        mp=new TreeMap<>();
        bfs(root);
        System.out.println(mp.values());
    }
}