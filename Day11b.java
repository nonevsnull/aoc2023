import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Day11b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<String> strs = new ArrayList<>();

        while(br.ready()){
            strs.add(br.readLine());
        }
        List<List<Character>> graph = new ArrayList<>();
        int[][] vacs = build(strs, graph);
        int m = 1000000;
        for(List<Character> l : graph){
            System.out.println(l);
        }
        List<int[]> glas = getGlas(graph);

        long dis = 0;
        for(int i = 0;i < glas.size();i++){
            int[] gla = glas.get(i);
            for(int j = i + 1;j < glas.size();j++){
                int[] glb = glas.get(j);
                dis += Math.abs(gla[0] - glb[0]) + Math.abs(gla[1] - glb[1]);
                int[] vac = checkVac(vacs, gla, glb);
                dis += (m - 1) * (vac[0] + vac[1]);
            }
        }
        System.out.println(dis);

    }   

    static int[] checkVac(int[][] vacs, int[] gla, int[] glb){
        int[] vac = new int[2];
        for(int i = Math.min(gla[0], glb[0]);i <= Math.max(gla[0], glb[0]);i++){
            if(vacs[0][i] == 0){
                vac[0]++;
            }
        }
        for(int i = Math.min(gla[1], glb[1]);i <= Math.max(gla[1], glb[1]);i++){
            if(vacs[1][i] == 0){
                vac[1]++;
            }
        }
        return vac;
    }

    static int[][] build(List<String> strs, List<List<Character>> graph){        
        int[] rows = new int[strs.size()];
        int[] cols = new int[strs.get(0).length()];

        for(int i = 0;i < strs.size();i++){
            List<Character> row = new ArrayList<>();
            for(int j = 0;j < strs.get(i).length();j++){
                char c = strs.get(i).charAt(j);
                row.add(c);
                if(c == '#'){
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
            graph.add(row);
        }
        return new int[][]{rows, cols};
    }

    static List<int[]> getGlas(List<List<Character>> graph){
        List<int[]> glas = new ArrayList<>();

        for(int i = 0;i < graph.size();i++){
            for(int j = 0;j < graph.get(i).size();j++){
                if(graph.get(i).get(j).equals('#')){
                    glas.add(new int[]{i, j});
                }
            }
        }
        return glas;
    }

}
