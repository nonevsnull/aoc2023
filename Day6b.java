import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day6b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        long time = load(br.readLine());
        long dists = load(br.readLine());

        int sum = 1;
        sum *= findWinCount(time, dists);
        System.out.println(sum);

    }

    static long load(String s){
        s = s.split(":")[1].strip();
        StringTokenizer st = new StringTokenizer(s);
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
        }
        return Long.parseLong(sb.toString());
    }

    static long findWinCount(long time, long dis){
        long l = 0;
        while((time - l) * l <= dis){
            l++;
        }
        long r = time;
        while((time - r) * r <= dis){
            r--;
        }
        return r - l + 1;

    }
}
