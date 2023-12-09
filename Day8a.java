import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        HashMap<String, List<String>> map = new HashMap<>();
        String directions = br.readLine();
        br.readLine();
        
        while (br.ready()) {
            fillMap(br.readLine(), map);    
        }
        int count = 0;
        String cur = "AAA";
        while(true){
            boolean found = false;
            for(int i = 0;i < directions.length();i++){
                char c = directions.charAt(i);
                int d = c == 'L'?0 : 1;
                cur = map.get(cur).get(d);
                count++;
                if(cur.equals("ZZZ")){
                    found = true;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        System.out.println(count);
    }

    static void fillMap(String s, HashMap<String, List<String>> map){
        String[] es = explode(s);
        List<String> lst = new ArrayList<>();
        lst.add(es[1]);
        lst.add(es[2]);
        map.put(es[0], lst);
    }

    static String[] explode(String s){
        String[] ss = s.split("=");
        String[] es = new String[3];
        es[0] = ss[0].strip();
        es[1] = ss[1].strip().substring(1, 4);
        es[2] = ss[1].strip().substring(6, 9);
        return es;
    }
}
