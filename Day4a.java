import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day4a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        int sum = 0;
        
        while (br.ready()) {
            int c = getMatch(br.readLine());
            sum += c;
        }
        System.out.println(sum);
    }

    static int getMatch(String s){
        int count = 0;
        int sum = 0;
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
                sum = (int)Math.pow(2, count);
                count++;
            }
            
        }
        return sum;
    }
}
