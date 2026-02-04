import java.util.concurrent.*;
import java.util.Scanner;
import java.util.*;

class Idk implements Callable<Integer>{
    int[][] grid;
    int startRow;
    int startCol;
    int poolSize;
    public Idk(int[][] grid,int startRow,int startCol,int poolSize){
        this.grid=grid;
        this.startRow=startRow;
        this.startCol=startCol;
        this.poolSize=poolSize;
    }
    public Integer call(){
        Integer res=Integer.MIN_VALUE;
        for(int i=0;i<poolSize;i++){
            for(int j=0;j<poolSize;j++){
                res=Math.max(res,grid[i+startRow][j+startCol]);
            }
        }
        return res;
    }
    
}
public class MaxPoolingForwardDynamicMT {


    /* ==================================
       Implement Your Code using Callable
       ================================== */
    public static int[][] maxPoolForward (int[][] grid,int poolSize,int stride) throws Exception{
        int H = grid.length;
        int W = grid[0].length;

        int outH = (H - poolSize) / stride + 1;
        int outW = (W - poolSize) / stride + 1;
        int[][] output=new int[outH][outW];
        ExecutorService executor=Executors.newFixedThreadPool(100);
        List<Future<Integer>> futures = new ArrayList<>();
        for(int i=0;i<outH;i++){
            for(int j=0;j<outW;j++){
                Idk task=new Idk(grid,i*stride,j*stride,poolSize);
                futures.add(executor.submit(task));
            }
        }
        int ind=0;
        for(int i=0;i<outH;i++){
            for(int j=0;j<outW;j++){
                output[i][j]=futures.get(ind++).get();
            }
        }
        executor.shutdown();
        return output;
        
    }
    
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Read matrix dimensions
        int H = sc.nextInt();
        int W = sc.nextInt();

        int[][] input = new int[H][W];

        // Read matrix
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                input[i][j] = sc.nextInt();
            }
        }

        // Read pooling parameters
        int poolSize = sc.nextInt();
        int stride = sc.nextInt();
        
        
        int[][] output =
                maxPoolForward(input, poolSize, stride);

        // Print output
        for (int[] row : output) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}