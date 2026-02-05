import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String og=sc.nextLine();
        String ref=sc.nextLine();
        
        int n1=og.length();
        int n2=ref.length();
        
        int l1=0,l2=0;
        while(l1<n1 && l2<n2){
            // System.out.println(og.charAt(l1)+" "+ref.charAt(l2));
            if(og.charAt(l1)==ref.charAt(l2)){
                l1++;
                l2++;
                continue;
            }
            if(!Character.isDigit(ref.charAt(l2))){
                System.out.println(false);
                return;
            }
            String temp="";
            while(l2<n2 && Character.isDigit(ref.charAt(l2))){
                temp+=ref.charAt(l2);
                l2++;
            }
            if(temp.length()>=1 && temp.charAt(0)=='0'){
                System.out.println(false);
                return;
            }
            int num=Integer.parseInt(temp);
            l1+=num;
            
        }
        System.out.println(l1==n1 && l2==n2);
    }
}