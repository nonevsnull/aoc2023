import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day15b {
    static HashMap<String, Integer> hashmap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        String[] steps = br.readLine().split(",");
        long sum = 0;
        HashMap<Integer, HashMap<String, Integer>> map = new HashMap<>();

        for(int i = 0;i < steps.length;i++){
            String s = steps[i];
            if(s.contains("=")){
                String lens = s.split("=")[0];
                int box = hash(lens);
                int focus = Integer.parseInt(s.split("=")[1]);
                if(!map.containsKey(box)){
                    HashMap<String, Integer> lf = new LinkedHashMap<>();
                    map.put(box, lf);
                }
                HashMap<String, Integer> lensFocus = map.get(box);
                lensFocus.put(lens, focus);
            } else {
                String lens = s.split("-")[0];
                int box = hash(lens);
                if(map.containsKey(box) && map.get(box).containsKey(lens)){
                    map.get(box).remove(lens);
                }
            }
        }
        
        for(Map.Entry<Integer, HashMap<String, Integer>> e : map.entrySet()){
            int box = e.getKey() + 1;
            HashMap<String, Integer> lensFocus = e.getValue();
            int shot = 1;
            for(Map.Entry<String, Integer> lf : lensFocus.entrySet()){
                int focal = lf.getValue();
                sum += box * shot * focal;
                shot++;
            }
        }
        System.out.println(sum);
    }

    static int hash(String s){
        if(hashmap.containsKey(s)){
            return hashmap.get(s);
        }
        int v = 0;
        for(int i = 0;i < s.length();i++){
            v += s.charAt(i) - 0;
            v *= 17;
            v %= 256;
        }
        hashmap.put(s, v);
        return v;
    }
}
