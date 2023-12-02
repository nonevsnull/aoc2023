import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;

/*
 * Solution: 1m
 * Coding: 4m
 * Time: 5m
 *
 */
public class Day1b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);
        map.put("five",5);
        map.put("six",6);
        map.put("seven",7);
        map.put("eight",8);
        map.put("nine",9);
        int sum = 0;
        while(br.ready()){
            String s = br.readLine();
            int ln = 0;
            int li = Integer.MAX_VALUE;
            for(int i = 0;i < s.length();i++){
                if(Character.isDigit(s.charAt(i))){
                    ln = s.charAt(i) - '0';
                    li = i;
                    break;
                }
            }
            int rn = 0;
            int ri = Integer.MIN_VALUE;
            for(int i = s.length() - 1;i >= 0;i--){
                if(Character.isDigit(s.charAt(i))){
                    rn = s.charAt(i) - '0';
                    ri = i;
                    break;
                }
            }
            int wl = Integer.MAX_VALUE, wr = Integer.MIN_VALUE;
            int wlv = 0, wrv = 0;
            for(String k : map.keySet()){
                if(s.indexOf(k) >= 0 && wl > s.indexOf(k)){
                    wl = s.indexOf(k);
                    wlv = map.get(k);
                }
                if(s.lastIndexOf(k) >= 0 && wr < s.lastIndexOf(k)){
                    wr = s.lastIndexOf(k);
                    wrv = map.get(k);
                }
            }
            int ll = 0, rr = 0;
            if(wl < li){
                ll = wlv;
            } else {
                ll = ln;
            }
            if(wr > ri){
                rr = wrv;
            } else {
                rr = rn;
            }
            sum += ll * 10 + rr;
        }
        System.out.println(sum);
    }
}