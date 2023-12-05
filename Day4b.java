import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day4b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        HashMap<Integer, Integer> map = new HashMap<>();
        int card = 1;
        while (br.ready()) {
            getMatch(br.readLine(), map, card);
            card++;
        }
        int sum = 0;
        for(int i : map.values()){
            sum += i;
        }
        System.out.println(sum);
    }

    static void getMatch(String s, HashMap<Integer, Integer> map, int card){
        int count = 0;
        map.put(card, map.getOrDefault(card, 0) + 1);
        int multiplier = map.get(card);
        s = s.split(":")[1];
        String[] arr = s.split("\\|");
        HashSet<Integer> set = new HashSet<>();
            
        for(String n : arr[0].strip().split(" ")){
            if(n.isEmpty()){
                continue;
            }
            set.add(Integer.parseInt(n));
        }
        for(String i : arr[1].strip().split(" ")){
            if(i.isEmpty()){
                continue;
            }
            int num = Integer.parseInt(i);
            if(set.contains(num)){
                count++;
            }
            
        }
        
        for(int i = 1;i <= count;i++){
            int k = card + i;
            if(!map.containsKey(k)){
                map.put(k, 0);
            }
            map.put(k, map.get(k) + multiplier);
        }
    }
}
