import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day6a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<Integer> times = load(br.readLine());
        List<Integer> dists = load(br.readLine());

        int sum = 1;
        for(int i = 0;i < times.size();i++){
            sum *= findWinCount(times.get(i), dists.get(i));
        }

        System.out.println(sum);

    }

    static List<Integer> load(String s){
        s = s.split(":")[1].strip();
        StringTokenizer st = new StringTokenizer(s);
        List<Integer> lst = new ArrayList<>();

        while (st.hasMoreTokens()) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        return lst;
    }

    static int findWinCount(int time, int dis){
        int l = 0;
        while((time - l) * l <= dis){
            l++;
        }
        int r = time;
        while((time - r) * r <= dis){
            r--;
        }
        return r - l + 1;

    }
}
