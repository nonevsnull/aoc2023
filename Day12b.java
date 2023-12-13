import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day12b {
    static HashMap<String, Long> cache = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        long sum = 0;
        while (br.ready()) {
            cache.clear();
            Input input = load(br.readLine());
            // int count = arrange(input);
            List<String> strs = new ArrayList<>();
            int total = 0;
            for(int i = 0;i < input.arrangments.length;i++){
                String s = "#".repeat(input.arrangments[i]) ;
                if(i < input.arrangments.length - 1){
                    s += ".";
                }
                strs.add(s);
                total += s.length();
            }
            long count = dfs(input.original, strs, total, new StringBuilder());
            sum += count;
            System.out.println(count);
            // System.out.println(Arrays.toString(input.arrangments));
        }
        System.out.println(sum);
    }


    static long dfs(String s, List<String> arrs, int total, StringBuilder sb){
        if(cache.containsKey(s + arrs.size())){
            return cache.get(s + arrs.size());
        }
        if(s.length() < total){
            return 0;
        }
        if(arrs.size() == 0){
            if(s.contains("#")){
                return 0;
            }
            // System.out.println(sb.toString());
            return 1;
        }
        
        long sum = 0;
        for(int i = 0;i < s.length();i++){
            String str = arrs.get(0);
            int len = str.length();
            total -= len;
            if(i + len > s.length() || total > s.length() - len){
                break;
            }
            String sub = s.substring(i, i + len);
            if(checkstr(sub, str)){
                int sz = sb.length();
                sb.append(str);
                arrs.remove(0);
                sum += dfs(s.substring(i + len, s.length()), arrs, total, sb);
                arrs.add(0, str);
                sb.delete(sz, sz + len);
            } else if(s.charAt(i) != '#'){
                sb.append(s.charAt(i));
                continue;
            }
            
            if(s.charAt(i) == '#'){
                break;
            }
            sb.append(s.charAt(i));
        }
        cache.put(s + arrs.size(), sum);
        return sum;
    }
    static boolean checkstr(String s1, String s2){
        for(int i = 0;i < s1.length();i++){
            if(s1.charAt(i) == s2.charAt(i) || s1.charAt(i) == '?'){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    static void fill(char[] arr, char aoq, List<Integer> ip, String map){
        char aoqb = aoq == '#'? '.' : '#';
        for(int i = 0;i < map.length();i++){
            if(map.charAt(i) == '1'){
                arr[ip.get(i)] = aoq;
            } else {
                arr[ip.get(i)] = aoqb;
            }
        }
    }

    static Input load(String s){
        String[] ss = s.split(" ");
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        for(int i = 0;i < 5;i++){
            sb0.append(ss[0]);
            sb0.append("?");
            sb1.append(ss[1]);
            sb1.append(",");
        }
        ss[0] = sb0.toString().substring(0, sb0.length() - 1);
        ss[1] = sb1.toString().substring(0, sb1.length() - 1);
        List<Integer> ip = new ArrayList<>();
        int totalQm = 0;
        int totalHash = 0;
        for(int i = 0;i < ss[0].length();i++){
            char c = ss[0].charAt(i);
            if(c == '?'){
                ip.add(i);
                totalQm++;
            } else if(c == '#'){
                totalHash++;
            }
        }
        String[] ars = ss[1].split(",");
        int[] arrangments = new int[ars.length];
        int totalArr = 0;
        for(int i = 0;i < arrangments.length;i++){
            int count = Integer.parseInt(ars[i]);
            arrangments[i] = count;
            totalArr += count;
        }
        return new Input(ss[0], totalArr, totalQm, totalHash, ip, arrangments);
    }

    static class Input{
        String original;
        int totalArr;
        int totalQm;
        int totalHash;
        List<Integer> ip;
        int[] arrangments;

        Input(String original, int totalArr, int totalHash, int totalQm, List<Integer> ip, int[] arrangments){
            this.original = original;
            this.totalArr = totalArr;
            this.totalQm = totalQm;
            this.totalHash = totalHash;
            this.ip = ip;
            this.arrangments = arrangments;
        }
    }
}
