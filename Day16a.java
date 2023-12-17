import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day16a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        
        List<String> strs = new ArrayList<>();
        while (br.ready()) {
            strs.add(br.readLine());
        }

        
        int max = 0;
        for(int k = 0;k < strs.get(0).length();k++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            dfs(arr, "s", 0, k, visited);
            int count = 0;
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    // System.out.print(arr[i][j] + "   ");
                    if(visited[i][j] == 1){
                        count++;
                    }
                }
                // System.out.println("");
            }
            max = Math.max(max, count);
            System.out.println(max);
        }
        for(int k = 0;k < strs.get(0).length();k++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            dfs(arr, "n", arr.length, k, visited);
            int count = 0;
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    // System.out.print(arr[i][j] + "   ");
                    if(visited[i][j] == 1){
                        count++;
                    }
                }
                // System.out.println("");
            }
            max = Math.max(max, count);
            System.out.println(max);
        }
        for(int k = 0;k < strs.size();k++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            dfs(arr, "e", k, 0, visited);
            int count = 0;
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    // System.out.print(arr[i][j] + "   ");
                    if(visited[i][j] == 1){
                        count++;
                    }
                }
                // System.out.println("");
            }
            max = Math.max(max, count);
            System.out.println(max);
        }
        for(int k = 0;k < strs.size();k++){
            String[][] arr = new String[strs.size()][strs.get(0).length()];
        
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    arr[i][j] = String.valueOf(strs.get(i).charAt(j));
                }
            }
            int[][] visited = new int[arr.length][arr[0].length];
            dfs(arr, "w", k, arr[0].length - 1, visited);
            int count = 0;
            for(int i = 0;i < arr.length;i++){
                for(int j = 0;j < arr[0].length;j++){
                    // System.out.print(arr[i][j] + "   ");
                    if(visited[i][j] == 1){
                        count++;
                    }
                }
                // System.out.println("");
            }
            max = Math.max(max, count);
            System.out.println(max);
        }


        


    }
    static void dfs(String[][] arr, String direction, int x, int y, int[][] visited){
        if(x > arr.length - 1 || x < 0 || y > arr[0].length - 1 || y < 0 || arr[x][y].contains(direction)){
            return;
        }
        visited[x][y] = 1;
        if(arr[x][y].equals("\\")){
            if(direction.equals("e")){
                dfs(arr, "s", x + 1, y, visited);
            } else if(direction.equals("s")){
                dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("w")){
                dfs(arr, "n", x - 1, y, visited);
            } else {
                dfs(arr, "w", x, y - 1, visited);
            }
        } else if(arr[x][y].equals("/")){
            if(direction.equals("e")){
                dfs(arr, "n", x - 1, y, visited);
            } else if(direction.equals("s")){
                dfs(arr, "w", x, y - 1, visited);
            } else if(direction.equals("w")){
                dfs(arr, "s", x + 1, y, visited);
            } else {
                dfs(arr, "e", x, y + 1, visited);
            }
        } else if(arr[x][y].equals("-")){
            if(direction.equals("e")){
                dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("w")){
                dfs(arr, "w", x, y - 1, visited);
            } else {
                dfs(arr, "e", x, y + 1, visited);
                dfs(arr, "w", x, y - 1, visited);
            }
        } else if(arr[x][y].equals("|")){
            if(direction.equals("n")){
                dfs(arr, "n", x - 1, y, visited);
            } else if(direction.equals("s")){
                dfs(arr, "s", x + 1, y, visited);
            } else {
                dfs(arr, "n", x - 1, y, visited);
                dfs(arr, "s", x + 1, y, visited);
            }
        } else {
            if(arr[x][y].equals(".")){
                arr[x][y] = direction;
            } else {
                arr[x][y] += direction;
            }
            if(direction.equals("e")){
                dfs(arr, "e", x, y + 1, visited);
            } else if(direction.equals("s")){
                dfs(arr, "s", x + 1, y, visited);
            } else if(direction.equals("w")){
                dfs(arr, "w", x, y - 1, visited);
            } else {
                dfs(arr, "n", x - 1, y, visited);
            }
            
        }
    }
}
