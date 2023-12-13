import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        List<char[][]> lst = new ArrayList<>();
        List<String> strs = new ArrayList<>();
        long sum = 0;
        while (br.ready()) {
            String s = br.readLine();
            if(s.isEmpty()){
                char[][] pattern = load(strs);
                lst.add(pattern);
                strs.clear();
            } else {
                strs.add(s);
            }
        }
        char[][] p = load(strs);
        lst.add(p);
        

        for(int i = 0;i < lst.size();i++){
            char[][] pattern = lst.get(i);
            int[] mirror = findMirrorOpt(pattern);
            System.out.println((mirror[0] == 0? "row mirror at " : "col mirror at ") + mirror[1]);
            sum += mirror[0] == 0 ? 100 * mirror[1] : mirror[1];
        }
        System.out.println(sum);
    }

    static int[] findMirrorOpt(char[][] pattern){
        for(int i = 0;i < pattern.length - 1;i++){
            for(int j = 0;j < pattern[0].length - 1;j++){
                if(checkColOpt(pattern, j, j + 1)){
                    return new int[]{1, j + 1};
                }  
                if(checkRowOpt(pattern, i, i + 1)){
                    return new int[]{0, i + 1};
                } 
            }
        }
        return new int[]{-1, 0};
    }

    static int[] findMirror(char[][] pattern){
        for(int i = 0;i < pattern.length - 1;i++){
            for(int j = 0;j < pattern[0].length - 1;j++){
                if(checkCol(pattern, j, j + 1)){
                    return new int[]{1, j + 1};
                }  
                if(checkRow(pattern, i, i + 1)){
                    return new int[]{0, i + 1};
                } 
            }
        }
        return new int[]{-1, 0};
    }

    static boolean checkCol(char[][] pattern, int left, int right){
        while(left >= 0 && right < pattern[0].length){
            for(int i = 0;i < pattern.length;i++){
                if(pattern[i][left] != pattern[i][right]){
                    return false;
                }
            }
            left--;
            right++;
        }
        return true;
    }
    
    static boolean checkColOpt(char[][] pattern, int left, int right){
        int count = 0;
        while(left >= 0 && right < pattern[0].length && count < 2){
            for(int i = 0;i < pattern.length;i++){
                if(pattern[i][left] != pattern[i][right]){
                    count++;
                }
            }
            left--;
            right++;
        }
        return count == 1;
    }

    static boolean checkRowOpt(char[][] pattern, int up, int down){
        int count = 0;
        while(up >= 0 && down < pattern.length && count < 2){
            for(int i = 0;i < pattern[0].length;i++){
                if(pattern[up][i] != pattern[down][i]){
                    count++;
                }
            }
            up--;
            down++;
        }
        return count == 1;
    }

    static boolean checkRow(char[][] pattern, int up, int down){
        while(up >= 0 && down < pattern.length){
            for(int i = 0;i < pattern[0].length;i++){
                if(pattern[up][i] != pattern[down][i]){
                    return false;
                }
            }
            up--;
            down++;
        }
        return true;
    }

    static char[][] load(List<String> strs){
        char[][] arr = new char[strs.size()][strs.get(0).length()];

        for(int i = 0;i < strs.size();i++){
            arr[i] = strs.get(i).toCharArray();
        }
        return arr;
    }
}
