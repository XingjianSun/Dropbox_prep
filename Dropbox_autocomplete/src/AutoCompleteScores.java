import java.util.*;
import java.lang.*;


public class AutoCompleteScores {

    static class TrieNode{
        Map<Character, TrieNode> children = new HashMap<> ();
        int score = 0;
    }

    static TrieNode root;
    public static List<Integer> getAutocompleteScores(List<String> documentTitles, List<String> documentBodies, List<String> queries) {
        //todo
        root = new TrieNode();
        List<Integer> res = new ArrayList<> ();
        for(String t: documentTitles){
            for(String i: t.split(" ")){
                buildTrie (i, 10);
            }
        }

        for(String b: documentBodies){
            for(String i: b.split (" ")){
                buildTrie (i, 1);
            }
        }

        for(String q: queries){
            TrieNode prefix = getPrefixTrieNode (q);
            if(prefix != null) res.add(getMaxScore (prefix));
            else res.add(0);
        }

        return res;
    }

    public static void buildTrie(String s, int score){
        TrieNode cur = root;
        for(int i = 0; i < s.length (); i++){
            char c = s.charAt (i);
            TrieNode next = cur.children.get(c);
            if(next == null){
                next = new TrieNode();
                cur.children.put(c, next);
            }
            cur = next;
            if(i == s.length () - 1) {
                cur.score += score;
            }
        }
    }

    public static int getMaxScore(TrieNode prefix){
        int score = prefix.score;
        Queue<TrieNode> q = new LinkedList<> ();
        q.offer (prefix);
        while(q.size() != 0){
            TrieNode n = q.poll();
            score = Math.max(score, n.score);
            for(TrieNode c: n.children.values ()){
                q.offer (c);
            }
        }
        return score;
    }

    public static TrieNode getPrefixTrieNode(String s){
        TrieNode cur = root;
        for(int i = 0; i < s.length (); i++){
            char c = s.charAt (i);
            cur = cur.children.get (c);
            if(cur == null){
                return null;
            }
        }
        return cur;
    }

    public static void main(String[] args){
        List<String> title = Arrays.asList(new String[]{"ANIMALS" , "DOG FACTS"});
        List<String> body = Arrays.asList(new String[]{"ANT ANTELOPE CAMEL CAT DOG", "FURRY FURRY LOYAL"});
        List<String> query = Arrays.asList(new String[]{"AN", "CAT", "DOG", "FURRY", "DO", "FUR", "ELEPH "});
        for(int i : getAutocompleteScores(title, body, query)){
            System.out.println(i);
        }
    }

}
