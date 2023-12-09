import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day8b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        HashMap<String, List<String>> map = new HashMap<>();
        String directions = br.readLine();
        br.readLine();
        List<String> starts = new ArrayList<>();
        while (br.ready()) {
            String startOpt = fillMap(br.readLine(), map);    
            if(!startOpt.isEmpty()){
                starts.add(startOpt);
            }
        }
        String[] curs = new String[starts.size()];
        for(int i = 0;i < curs.length;i++){
            curs[i] = starts.get(i);
        }
        
        List<Long> lst = new ArrayList<>();
        for(int j = 0;j < starts.size();j++){
            String cur = starts.get(j);
            long count = 0;
            while(true){
                boolean found = false;
                for(int i = 0;i < directions.length();i++){
                    char c = directions.charAt(i);
                    int d = c == 'L'?0 : 1;
                    cur = map.get(cur).get(d);
                    count++;
                    if(cur.endsWith("Z")){
                        found = true;
                        break;
                    }
                }
                if(found){
                    break;
                }
            }
            lst.add(count);
        }
        long sum = 1;
        long target = -1;
        for(long i : lst){
            System.out.println(i);
        }
        // for(long i = Collections.max(lst);i < Collections.max(lst) * Collections.max(lst);i++){
        //     boolean found = true;
        //     for(long l : lst){
        //         if(i % l != 0){
        //             found = false;
        //             break;
        //         }
        //     }
        //     if(found){
        //         target = i;
        //         break;
        //     }
        // }
        System.out.println(target);
    }

    static String fillMap(String s, HashMap<String, List<String>> map){
        String[] es = explode(s);
        List<String> lst = new ArrayList<>();
        lst.add(es[1]);
        lst.add(es[2]);
        map.put(es[0], lst);
        String start = "";
        if(es[0].endsWith("A")){
            start = es[0];
        }
        return start;
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
