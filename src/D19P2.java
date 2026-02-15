import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    TrieNode() {
        this.children = new TrieNode[26];
        isEnd = false;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        this.root = new TrieNode();
    }

    void add(String s) {
        TrieNode temp = this.root;

        for (char ch : s.toCharArray()) {
            if (temp.children[ch - 'a'] == null) {
                temp.children[ch - 'a'] = new TrieNode();
            }

            temp = temp.children[ch - 'a'];
        }
        temp.isEnd=true;
    }

    boolean isPrefix(String s) {
        TrieNode temp = this.root;

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (temp.children[index] == null)
                return false;

            temp = temp.children[index];
        }

        return !temp.isEnd;
    }

    boolean search(String s) {
        TrieNode temp = this.root;

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (temp.children[index] == null)
                return false;

            temp = temp.children[index];
        }

        return temp.isEnd;
    }
}

class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String[] s=sc.nextLine().split(" ");
        Arrays.sort(s,(a,b)->{
            return b.length()-a.length();
        });
        Trie t=new Trie();
        int res=0;
        for(String i:s){
            StringBuilder sb=new StringBuilder(i);
            sb.reverse();
            String curr=sb.toString();
            if(!t.isPrefix(curr) && !t.search(curr)){
                t.add(curr);
                res+=curr.length()+1;
            }
        }
        System.out.println(res);
        
    }
}