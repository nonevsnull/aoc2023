import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Day7b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        TreeMap<String, Integer> map = new TreeMap<>(new CardComparator());
        int rank = 0;
        while(br.ready()){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
            rank++;
        }
        br.close();
        int sum = 0;
        for(Map.Entry<String, Integer> e : map.entrySet()){
            sum += rank * e.getValue();
            rank--;
        }
        System.out.println(sum);

    }

    static class CardComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int t1 = checkType(s1);
            int t2 = checkType(s2);
            if(t1 != t2){
                return Integer.compare(t1, t2) * -1;
            } else {
                return compareCard(s1, s2) * -1;
            }
        }
    }

    static void fill(String s,  HashMap<Character, Integer> map){
        int jcount = 0;
        int max = 0;
        char maxc = 'a';
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(c == 'J'){
                jcount++;
            } else {
                if(!map.containsKey(c)){
                    map.put(c, 0);
                }
                map.put(c, map.get(c) + 1);
                if(map.get(c) > max){
                    max = map.get(c);
                    maxc = c;
                }
            }
        }
        if(jcount == 5){
            map.put('A', 5);
        } else {
            map.put(maxc, map.get(maxc) + jcount);
        }
        
    }

    static int checkType(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        fill(s, map);
        if(map.size() == 1){
            return 6;
        } else if(map.size() == 2){
            int max = 0;
            for(int v : map.values()){
                max = Math.max(max, v);
            }
            if(max == 4){
                return 5;
            } else {
                return 4;
            }
        } else if(map.size() == 3){
            int max = 0;
            for(int v : map.values()){
                max = Math.max(max, v);
            }
            if(max == 3){
                return 3;
            } else {
                return 2;
            }
        } else if(map.size() == 4){
            return 1;
        } else {
            return 0;
        }
    }

    static int compareCard(String s1, String s2){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 13);
        map.put('K', 12);
        map.put('Q', 11);
        map.put('T', 9);
        map.put('9', 8);
        map.put('8', 7);
        map.put('7', 6);
        map.put('6', 5);
        map.put('5', 4);
        map.put('4', 3);
        map.put('3', 2);
        map.put('2', 1);
        map.put('J', 0);
        int pos = 0;
        while(pos < s1.length() && s1.charAt(pos) == s2.charAt(pos)){
            pos++;
        }
        if(pos == s1.length()){
            return 0;
        }
        return Integer.compare(map.get(s1.charAt(pos)), map.get(s2.charAt(pos)));

    }
}
