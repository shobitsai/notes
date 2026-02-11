import java.util.*;
class Node{
    boolean endOfWord;
    Node[] children;
    Node(){
        this.endOfWord=false;
        this.children=new Node[26];
    }
}
class Trie {
    Node root;
    public Trie() {
        root=new Node();
    }
    public void insert(String word) {
        Node curr=root;
        for(char ch:word.toCharArray()){
            int ind=ch-'a';
            if(curr.children[ind]==null){
                curr.children[ind]=new Node();
            }
            curr=curr.children[ind];
        }
        curr.endOfWord=true;
    }
    
    public boolean search(String word) {
        Node curr=root;
        for(char ch:word.toCharArray()){
            int ind=ch-'a';
            if(curr.children[ind]==null)return false;
            curr=curr.children[ind];
        }
        return curr.endOfWord;
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        Trie t=new Trie();
        int res=0;
        for(int i=0;i<s.length();i++){
            Node curr=t.root;
            for(int j=i;j<s.length();j++){
                int ind=s.charAt(j)-'a';
                if(curr.children[ind]==null){
                    res++;
                    curr.children[ind]=new Node();
                }
                curr=curr.children[ind];
                
            }
        }
        System.out.println(res);
    }
}