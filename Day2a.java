import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day2a {
    static HashMap<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        map = new HashMap<>();
        map.put("red", 12);
        map.put("green", 13);
        map.put("blue", 14);
        int game = 1;
        int sum = 0;
        while(br.ready()){
            String[] rounds = getRounds(br.readLine());
            if(checkRounds(rounds)){
                System.out.println("Game " + game + " is valid.");
                sum += game;
            }
            game++;
        }
        System.out.println(sum);
    }

    static String[] getRounds(String game){
        String noTitle = game.split(":")[1];
        String[] rounds = noTitle.split(";");
        return rounds;
    }
    static boolean checkRounds(String[] rounds){
        for(String round : rounds){
            String[] plays = round.split(",");
            for(String play : plays){
                play = play.strip();
                int num = Integer.parseInt(play.split(" ")[0]);
                String color = play.split(" ")[1];
                if(num > map.get(color)){
                    return false;
                }
            }
        }
        return true;
    }
}
