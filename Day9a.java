import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Day9a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        int sum = 0;
        while (br.ready()) {
            List<Integer> lst = load(br.readLine());
            sum += predict(lst);
        }
        System.out.println(sum);
    }
    static List<Integer> load(String s){
        StringTokenizer st = new StringTokenizer(s);
        List<Integer> lst = new ArrayList<>();

        while(st.hasMoreTokens()){
            lst.add(Integer.parseInt(st.nextToken()));
        }
        return lst;
    }

    static int predict(List<Integer> lst){
        HashSet<Integer> set = new HashSet<>(lst);
        if(set.size() == 1 && set.contains(0)){
            return 0;
        } 
        List<Integer> nextList = new ArrayList<>();
        for(int i = 0;i < lst.size() - 1;i++){
            nextList.add(lst.get(i + 1) - lst.get(i));
        }
        return lst.get(lst.size() - 1) + predict(nextList);
    }
}
