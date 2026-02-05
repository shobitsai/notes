import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] nums=sc.nextLine().split(" ");
        int xor=0;
        for(String i:nums)xor=xor^(Integer.parseInt(i));
        
        int mask=1;
        while((mask&xor)==0){
            mask=mask<<1;
        }
        int a=0,b=0;
        for(String i:nums){
            if((mask & Integer.parseInt(i))!=0){
                a=a^Integer.parseInt(i);
            }else{
                b=b^Integer.parseInt(i);
            }
        }
        int[] res={a,b};
        System.out.println(Arrays.toString(res));
    }
}