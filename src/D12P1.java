import java.util.*;
class Solution{
    public static boolean isOk(int[] a1,int[] a2){
        int xor=0;
        int n=a1.length;
        for(int i=0;i<a1.length;i++){
            if((a1[i]^a2[i])==1)xor++;
        }
        // if(eq==n)return true;
        if(xor==n || xor==0)return true;
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] arr=new int[n][m];
        for(int i=0;i<n;i++)for(int j=0;j<m;j++)arr[i][j]=sc.nextInt();
        // int[] fixed_cols=grid[0];
        
        for(int j=1;j<n;j++){
            if(!isOk(arr[0],arr[j])){
                System.out.println(false);
                return;
            }
        }
        
        System.out.println(true);
        
        
    }
}