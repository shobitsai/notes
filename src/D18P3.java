import java.util.*;
class Shared{
    int n;
    int curr;
    boolean isEven=false;
    boolean printZero=true;
    Shared(int n){
        this.curr=1;
        this.n=n;
    }
    public synchronized void print0() throws Exception{
        while(curr<n){
            while(curr<n && !printZero){
                wait();
            }
            System.out.print(0);
            printZero=false;
            if(isEven)isEven=false;
            else isEven=true;
            
            notifyAll();
        }
    }
    public synchronized void printE() throws Exception{
        while(curr<n){
            while(curr<n && (!isEven || printZero)){
                wait();
            }
            System.out.print(curr);
            curr++;
            printZero=true;
            isEven=false;
            notifyAll();
        }
    }
    public synchronized void printO() throws Exception{
        while(curr<n){
            while(curr<n && (isEven || printZero)){
                wait();
            }
            System.out.print(curr);
            curr++;
            printZero=true;
            isEven=true;
            notifyAll();
        }
    }
}
class Solution{
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Shared s=new Shared(n);
        Thread t1=new Thread(()->{
            try{
                s.print0();
            }catch(Exception e){}
        });
        Thread t2=new Thread(()->{
            try{
                s.printE();
            }catch(Exception e){}
        });
        Thread t3=new Thread(()->{
            try{
                s.printO();
            }catch(Exception e){}
        });
        t1.start();
        t2.start();
        t3.start();
    }
}