import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day2b {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        int sum = 0;
        while(br.ready()){
            HashMap<String, Integer> map = new HashMap<>();
            map.put("red", 0);
            map.put("green", 0);
            map.put("blue", 0);
            String[] rounds = getRounds(br.readLine());
            for(String round : rounds){
                String[] plays = round.split(",");
                for(String play : plays){
                    play = play.strip();
                    int num = Integer.parseInt(play.split(" ")[0]);
                    String color = play.split(" ")[1];
                    if(num > map.get(color)){
                        map.put(color, num);
                    }
                }
            }
            int m = 1;
            for(int c : map.values()){
                m *= c;
            }
            sum += m;
        }
        System.out.println(sum);
    }

    static String[] getRounds(String game){
        String noTitle = game.split(":")[1];
        String[] rounds = noTitle.split(";");
        return rounds;
    }

}
