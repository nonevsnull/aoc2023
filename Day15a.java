import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day15a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        String[] steps = br.readLine().split(",");
        long sum = 0;
        for(int i = 0;i < steps.length;i++){
            int h = hash(steps[i]);
            System.out.println(h);
            sum += h;
        }
        System.out.println(sum);
    }

    static int hash(String s){
        int v = 0;
        for(int i = 0;i < s.length();i++){
            v += s.charAt(i) - 0;
            v *= 17;
            v %= 256;
        }
        return v;
    }
}
