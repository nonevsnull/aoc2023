import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/*
 * Solution: 1m
 * Coding: 4m
 * Time: 5m
 *
 */
public class Day1a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        while(br.ready()){
            String s = br.readLine();
            int ln = 0;
            for(int i = 0;i < s.length();i++){
                if(Character.isDigit(s.charAt(i))){
                    ln = s.charAt(i) - '0';
                    break;
                }
            }
            int rn = 0;
            for(int i = s.length() - 1;i >= 0;i--){
                if(Character.isDigit(s.charAt(i))){
                    rn = s.charAt(i) - '0';
                    break;
                }
            }
            sum += ln * 10 + rn;
        }
        System.out.println(sum);
    }
}