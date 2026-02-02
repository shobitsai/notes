import java.util.*;

class dsuSet {
    int[] parent;

    dsuSet() {
        this.parent = new int[26];

        for (int i = 0; i < 26; i++)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);

        return parent[x];
    }

    public void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa == fb)
            return;

        if (fa < fb)
            parent[fb] = fa;
        else
            parent[fa] = fb;
    }
}

class D09P1 {
    public static boolean check(String[] parts) {
        dsuSet d = new dsuSet();

        for (String e : parts) {
            int a = e.charAt(0) - 'a';
            int b = e.charAt(3) - 'a';

            if (e.substring(1, 3).equals("==")) {
                d.union(a, b);
            }
        }

        for (String e : parts) {
            int a = e.charAt(0) - 'a';
            int b = e.charAt(3) - 'a';

            if (d.find(a) == d.find(b)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eqs = sc.nextLine();
        String[] parts = eqs.split(" ");

        System.out.println(check(parts));
    }
}