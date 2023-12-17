import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day16b {
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        
        List<String> strs = new ArrayList<>();
        while (br.ready()) {
            strs.add(br.readLine());
        }

        
        int count = 0;
        for(int r = 0;r < strs.size();r++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            count = Math.max(count, dfs(arr, "e", r, 0, visited));
            
        }
        for(int c = 0;c < strs.get(0).length();c++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            count = Math.max(count, dfs(arr, "n", arr.length - 1, c, visited));
        }
        for(int r = 0;r < strs.size();r++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            count = Math.max(count, dfs(arr, "w", r, arr[0].length - 1, visited));
        }
        for(int c = 0;c < strs.get(0).length();c++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            count = Math.max(count, dfs(arr, "s", 0, c, visited));
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    System.out.print(visited[i][j] + "   ");
                    // if(visited[i][j] == 1){
                    //     count++;
                    // }
                }
                System.out.println("");
            }
            System.out.println("");
        }
        System.out.println(count);


    }
    static int dfs(String[][] arr, String direction, int x, int y, int[][] visited){
        if(x > arr.length - 1 || x < 0 || y > arr[0].length - 1 || y < 0 || arr[x][y].contains(direction)){
            return 0;
        }
        if(map.containsKey(direction + "," + x + "," + y)){
            return map.get(direction + "," + x + "," + y);
        }
        int cur = 0;
        if(visited[x][y] == 0){
            cur = 1;
            visited[x][y] = 1;    
        }
        int count = 0;
        if(arr[x][y].equals("\\")){
            if(direction.equals("e")){
                count = cur + dfs(arr, "s", x + 1, y, visited);
            } else if(direction.equals("s")){
                count =  cur + dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("w")){
                count = cur + dfs(arr, "n", x - 1, y, visited);
            } else {
                count = cur + dfs(arr, "w", x, y - 1, visited);
            }
        } else if(arr[x][y].equals("/")){
            if(direction.equals("e")){
                count = cur + dfs(arr, "n", x - 1, y, visited);
            } else if(direction.equals("s")){
                count = cur + dfs(arr, "w", x, y - 1, visited);
            } else if(direction.equals("w")){
                count = cur + dfs(arr, "s", x + 1, y, visited);
            } else {
                count = cur + dfs(arr, "e", x, y + 1, visited);
            }
        } else if(arr[x][y].equals("-")){
            if(direction.equals("e")){
                count = cur + dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("w")){
                count = cur + dfs(arr, "w", x, y - 1, visited);
            } else {
                count = cur + dfs(arr, "e", x, y + 1, visited) + dfs(arr, "w", x, y - 1, visited);
            }
        } else if(arr[x][y].equals("|")){
            if(direction.equals("n")){
                count = cur + dfs(arr, "n", x - 1, y, visited);
            } else if(direction.equals("s")){
                count = cur + dfs(arr, "s", x + 1, y, visited);
            } else {
                count = cur + dfs(arr, "n", x - 1, y, visited) + dfs(arr, "s", x + 1, y, visited);
            }
        } else {
            if(arr[x][y].equals(".")){
                arr[x][y] = direction;
            } else {
                arr[x][y] += direction;
            }
            if(direction.equals("e")){
                count = cur + dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("s")){
                count = cur + dfs(arr, "s", x + 1, y, visited);
            } else if(direction.equals("w")){
                count = cur + dfs(arr, "w", x, y - 1, visited);
            } else {
                count = cur + dfs(arr, "n", x - 1, y, visited);
            }
            
        }
        map.put(direction + "," + x + "," + y, count);
        return count;
    }
}
