import java.util.*;

class D09P2 {
    public static List<List<Integer>> ans;
    public static List<Integer> fac;
    public static int fs;

    public static void backtrack(int n, int prod, ArrayList<Integer> temp, int min) {
        if (prod == n) {
            ArrayList<Integer> x = new ArrayList<>(temp);
            ans.add(x);
        }

        if (prod > n)
            return;

        int s = temp.size();

        for (int i = min; i < fs; i++) {
            temp.add(fac.get(i));
            backtrack(n, prod * fac.get(i), temp, i);
            temp.remove(s);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ans = new ArrayList<>();
        fac = new ArrayList<>();

        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                fac.add(i);
        }

        fs = fac.size();

        ArrayList<Integer> temp = new ArrayList<>();
        backtrack(n, 1, temp, 0);

        System.out.println(ans);
    }
}