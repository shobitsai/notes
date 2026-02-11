import java.util.*;
class Shared{
    int n;
    int curr=1;
    public Shared(int n){
        this.n=n;
    }
    public synchronized void printHi() throws Exception{
        while(curr<=n){
            while(curr<=n && !(curr%3==0 && curr%5!=0)){
                wait();
            }
            if(curr>n)break;
            System.out.println("Hi ");
            curr++;
            notifyAll();
        }
    }
    public synchronized void printHello() throws Exception{
        while(curr<=n){
            while(curr<=n && !(curr%5==0 && curr%3!=0)){
                wait();
            }
            if(curr>n)break;
            System.out.println("Hello ");
            curr++;
            notifyAll();
        }
    }
    public synchronized void printHiHello() throws Exception{
        while(curr<=n){
            while(curr<=n && !(curr%3==0 && curr%5==0)){
                wait();
            }
            if(curr>n)break;
            System.out.println("HiHello ");
            curr++;
            notifyAll();
        }
    }
    public synchronized void printNum() throws Exception{
        while(curr<=n){
            while(curr<=n && (curr%3==0 || curr%5==0)){
                wait();
            }
            if(curr>n)break;
            System.out.println(curr+" ");
            curr++;
            notifyAll();
        }
    }
}
class Solution{
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Shared shared=new Shared(n);
        Thread t1=new Thread(()->{
            try{
                shared.printHi();
            }catch(Exception e){}
        });
        Thread t2=new Thread(()->{
            try{
                shared.printHello();
            }catch(Exception e){}
        });
        Thread t3=new Thread(()->{
            try{
            shared.printHiHello();
            }catch(Exception e){}
        });
        Thread t4=new Thread(()->{
            try{
                shared.printNum();
            }catch(Exception e){}
        });
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
    }
}