import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] tem=sc.nextLine().split(" ");
        Set<String> dict=new HashSet<>();
        int maxLen=0;
        for(String i:tem){
            dict.add(i);
            maxLen=Math.max(maxLen,i.length());
        }
        for(int i=0;i<s.length();i++){
            String temp="";
            for(int j=i;j<Math.min(i+maxLen,s.length());j++){
                temp=s.substring(i,j+1);
                if(dict.contains(temp)){
                    System.out.println(i+" "+j);
                }
            }
        }
        return;
    }
}