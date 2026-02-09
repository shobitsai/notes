import java.util.*;
import java.util.concurrent.*;

/**
 * STUDENT TASK:
 * Implement the logic inside FeatureStumpTask.call()
 * Each feature must act as a binary decision stump (depth-1 tree)
 */
public class RFFeatureStump {

    /* ==========================
       FEATURE STUMP TASK
       ========================== */
    static class FeatureStumpTask implements Callable<Integer> {

        private final double[][] X;   // training features
        private final int[] y;        // class labels (0 or 1)
        private final int featureIdx; // index of feature handled by this task
        private final double testVal; // test sample value for this feature

        FeatureStumpTask(double[][] X, int[] y,
                          int featureIdx, double testVal) {
            this.X = X;
            this.y = y;
            this.featureIdx = featureIdx;
            this.testVal = testVal;
        }

        
        public double gini(List<Integer> left,List<Integer> right){
            int left_ones=0;
            int left_zeroes=0;
            for(int i:left){
                if(i==0)left_zeroes++;
                else left_ones++;
            }
            int right_ones=0;
            int right_zeroes=0;
            for(int i:right){
                if(i==0)right_zeroes++;
                else right_ones++;
            }
            if(left.size()==0 || right.size()==0)return 0;
            int n=left.size()+right.size();
            
            double left_gini = 1 - (Math.pow((double)left_ones / (left_zeroes + left_ones), 2)
                        + Math.pow((double)left_zeroes / (left_zeroes + left_ones), 2));
            double right_gini = 1 - (Math.pow((double)right_ones / (right_zeroes + right_ones), 2)
                        + Math.pow((double)right_zeroes / (right_zeroes + right_ones), 2));

            
           double weighted_gini = ((double)(left.size() / n) * left_gini) + ((double)(right.size() / n) * right_gini);

            return weighted_gini;
            
        }
        @Override
        public Integer call() {
            // Implement your code here.
            Set<Double> thresholds=new HashSet<>();
            for(int i=0;i<X.length;i++){
                thresholds.add(X[i][featureIdx]);
            }
            
            double best_t=0;
            double miniImpurity=Double.MAX_VALUE;
            for(double t:thresholds){
                List<Integer> left=new ArrayList<>();
                List<Integer> right=new ArrayList<>();
                for(int i=0;i<X.length;i++){
                    if(X[i][featureIdx]<t)left.add(y[i]);
                    else right.add(y[i]);
                }
                if (left.isEmpty() || right.isEmpty()) continue;
                double impurity=gini(left,right);
                if(impurity<miniImpurity){
                    miniImpurity=impurity;
                    best_t=t;
                }
            }
            
            int count0 = 0, count1 = 0;
            for(int i = 0; i < X.length; i++){
                if(testVal <= best_t && X[i][featureIdx] <= best_t){
                    if(y[i] == 0) count0++;
                    else count1++;
                }
                else if(testVal > best_t && X[i][featureIdx] > best_t){
                    if(y[i] == 0) count0++;
                    else count1++;
                }
            }
            
            return (count1 > count0) ? 1 : 0;
        }
    }

    /* ==========================
       MAIN METHOD (DO NOT MODIFY)
       ========================== */
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Read number of samples and features
        int N = sc.nextInt();
        int M = sc.nextInt();

        // Read training data
        double[][] X = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                X[i][j] = sc.nextDouble();
            }
        }

        // Read class labels
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            y[i] = sc.nextInt();
        }

        // Read test sample
        double[] testSample = new double[M];
        for (int i = 0; i < M; i++) {
            testSample[i] = sc.nextDouble();
        }

        // One thread per feature
        ExecutorService executor = Executors.newFixedThreadPool(M);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int f = 0; f < M; f++) {
            futures.add(
                executor.submit(
                    new FeatureStumpTask(X, y, f, testSample[f])
                )
            );
        }

        // Collect feature predictions
        List<Integer> featurePredictions = new ArrayList<>();
        for (Future<Integer> f : futures) {
            featurePredictions.add(f.get());
        }

        executor.shutdown();

        // Majority voting
        int count0 = 0, count1 = 0;
        for (int p : featurePredictions) {
            if (p == 0) count0++;
            else count1++;
        }

        int finalPrediction = (count1 > count0) ? 1 : 0;

        // Output
        System.out.println("Feature Predictions: " + featurePredictions);
        System.out.println("Final Predicted Class: " + finalPrediction);

        sc.close();
    }
}
