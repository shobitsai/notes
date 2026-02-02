//"Emphatic Pronunciation" of a given word is where we take the word and
//replicate some of the letter to emphasize their impact.
//
//Instead of saying 'oh my god', someone may say "ohhh myyy goddd",
//We define emphatic pronunciation of a word, which is derived by replicating
//a group (or single) of letters in the original word.
//
//So that the replicated group is atleast 3 characters or more and
//greater than or equal to size of original group.
//For example Good -> Goood is an emphatic pronunciation,
//but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
//
//In the question you are given the "Emphatic pronunciation" word,
//you have to findout how many words can legal result in the
//"emphatic pronunciation" word.
//
//Input Format:
//        -------------
//Line-1 -> A String contains a single word, Emphatic Pronunciation word
//Line-2 -> Space seperated word/s
//
//Output Format:
//        --------------
//Print an integer as your result
//
//
//Sample Input-1:
//        ---------------
//goood
//good goodd
//
//Sample Output-1:
//        ----------------
//        1
//
//
//Sample Input-2:
//        ---------------
//heeelllooo
//hello hi helo
//
//Sample Output-2:
//        ----------------
//        2

import java.util.*;

class D07P1 {
    public static boolean verify(String a, String b) {
        int i = 0, j = 0;
        int ax = a.length(), bx = b.length();

        while (i < ax && j < bx) {
            char bh = b.charAt(j);
            char ah = a.charAt(i);

            if (ah != bh) return false;

            int fb = 0, fa = 0;
            while (j < bx && b.charAt(j) == bh) {
                fb++;
                j++;
            }

            while (i < ax && a.charAt(i) == ah) {
                fa++;
                i++;
            }

            if (fa == fb || (fb >= 3 && fa < fb)) {
                continue;
            } else {
                return false;
            }
        }

        return (i == ax && j == bx);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] words = sc.nextLine().split(" ");

        int ans = 0;
        for (String sx : words) {
            if (verify(sx, s))
                ans++;
        }

        System.out.println(ans);
    }
}