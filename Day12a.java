import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        int sum = 0;
        while (br.ready()) {
            Input input = load(br.readLine());
            int count = arrange(input);
            sum += count;
            System.out.println(count);
        }
        System.out.println(sum);
    }

    static int arrange(Input input){
        int sum = 0;
        for(int i = 0;i < (int)Math.pow(2, input.totalQm);i++){
            String map = Integer.toBinaryString(i);
            map = "0".repeat(input.totalQm - map.length()) + map;
            char[] arr = input.original.toCharArray(); 
            fill(arr, '#', input.ip, map);
            if(check(arr, input.arrangments)){
                sum++;
            }
        }
        return sum;

    }
    static boolean check(char[] arr, int[] arrangments){
        String arrstr = new String(arr);
        String[] arrstrs = arrstr.split("\\.");
        List<String> strs = new ArrayList<>();
        for(String s : arrstrs){
            if(!s.isEmpty()){
                strs.add(s);
            }
            
        }
        if(strs.size() != arrangments.length){
            return false;
        }

        for(int i = 0;i < strs.size();i++){
            if(strs.get(i).length() != arrangments[i]){
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
        List<Integer> ip = new ArrayList<>();
        int totalQm = 0;
        for(int i = 0;i < ss[0].length();i++){
            char c = ss[0].charAt(i);
            if(c == '?'){
                ip.add(i);
                totalQm++;
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
        return new Input(ss[0], totalArr, totalQm, ip, arrangments);
    }

    static class Input{
        String original;
        int totalArr;
        int totalQm;
        List<Integer> ip;
        int[] arrangments;

        Input(String original, int totalArr, int totalQm, List<Integer> ip, int[] arrangments){
            this.original = original;
            this.totalArr = totalArr;
            this.totalQm = totalQm;
            this.ip = ip;
            this.arrangments = arrangments;
        }
    }
}
