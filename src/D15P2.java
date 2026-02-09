import java.util.*;
class Node{
    public String val;
    public int freq;
    Node(String val,int freq){
        this.val=val;
        this.freq=freq;
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] arr=sc.nextLine().split(",");
        int k=sc.nextInt();
        Map<String,Integer> mp=new HashMap<>();
        // string, freq
        PriorityQueue<Node> pq=new PriorityQueue<>((a,b)->{
            if(b.freq!=a.freq){
                return b.freq-a.freq;
            }
            return a.val.compareTo(b.val);
        });
        for(String i:arr)mp.put(i,mp.getOrDefault(i,0)+1);
        for(String i:mp.keySet()){
            Node temp=new Node(i,mp.get(i));
            pq.add(temp);
        }
        while(k>0){
            k--;
            System.out.println(pq.poll().val);
        }
    }
}