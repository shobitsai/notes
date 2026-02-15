import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        
        int count=n/8;
        int rest=n%8;
        String res="";
        for(int i=0;i<count;i++){
            if(i!=3)res+="255.";
            else res+="255";
        }
        int mask=0;a
        for(int i=7;i>0;i--){
            if(rest==0)break;
            mask=(mask | (1<<i));
            rest--;
        }a
        // System.out.println("mask: "+mask);
        if(mask!=0)res+=String.valueOf(mask);
        for(int i=count;i<3;i++){
            res+=".0";
        }
        System.out.println(res);
    }
}