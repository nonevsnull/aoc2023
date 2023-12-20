import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day17a {
    static HashMap<String, Integer> map = new HashMap<>();
    static int max = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<String> strs = new ArrayList<>();
        while (br.ready()) {
            strs.add(br.readLine());
        }
        int[][] arr = new int[strs.size()][strs.get(0).length()];
        String[][] visited = new String[strs.size()][strs.get(0).length()];

        for(int i = 0;i < arr.length;i++){
            for(int j = 0;j < arr[0].length;j++){
                arr[i][j] = strs.get(i).charAt(j) - '0';
                visited[i][j] = "";
            }
        }
        long cost = dfs(arr, 0, 1, "e", 3, visited, 0);
        System.out.println(cost);
    }
    
    static int dfs(int[][] arr, int x, int y, String direction, int budget, String[][] visited, int sum){
        if(map.containsKey(direction + ","+x+","+y + ","+budget)){
            return map.get(direction + ","+x+","+y + ","+budget);
        }
        // for(String[] ss : visited){
        //     for(String s : ss){
        //         System.out.print((s.isEmpty() ? "." : s) + "  ");
        //     }
        //     System.out.println("");
        // }
        // System.out.println("");
        sum += arr[x][y];
        if(sum > max){
            return max;
        }
        if(x == arr.length - 1 && y == arr[0].length - 1){
            max = sum;
            return arr[x][y];
        }
        
        budget--;
        int cost = max;
        if(x == 4 && y == 10 && direction == "s"){
            // for(String[] ss : visited){
            //     for(String s : ss){
            //         System.out.print((s.isEmpty() ? "." : s) + "  ");
            //     }
            //     System.out.println("");
            // }
            // System.out.println("");
            // System.out.println(cost);
        }
        if(direction.equals("e")){
            if(!(x - 1 < 0 || x - 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x - 1][y].isEmpty())){
                visited[x - 1][y] = "n";
                cost = Math.min(cost, dfs(arr, x - 1, y, "n", 3, visited, sum));
                visited[x - 1][y] = "";
            }
            if(!(x < 0 || x >= arr.length || y + 1 < 0 || y + 1 >= arr[0].length || !visited[x][y + 1].isEmpty() || budget == 0)){
                visited[x][y + 1] = "e";
                cost = Math.min(cost, dfs(arr, x, y + 1, "e", budget, visited, sum));
                visited[x][y + 1] = "";
            }
            if(!(x + 1 < 0 || x + 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x + 1][y].isEmpty())){
                visited[x + 1][y] = "s";
                cost = Math.min(cost, dfs(arr, x + 1, y, "s", 3, visited, sum));
                visited[x + 1][y] = "";
            }
        } else if(direction.equals("s")){
            if(!(x < 0 || x >= arr.length || y + 1 < 0 || y + 1 >= arr[0].length || !visited[x][y + 1].isEmpty())){
                visited[x][y + 1] = "e";
                cost = Math.min(cost, dfs(arr, x, y + 1, "e", 3, visited, sum));
                visited[x][y + 1] = "";
            }
            if(!(x + 1 < 0 || x + 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x + 1][y].isEmpty() || budget == 0)){
                visited[x + 1][y] = "s";
                cost = Math.min(cost, dfs(arr, x + 1, y, "s", budget, visited, sum));
                visited[x + 1][y] = "";
            }
            if(!(x < 0 || x >= arr.length || y - 1 < 0 || y - 1 >= arr[0].length || !visited[x][y - 1].isEmpty())){
                visited[x][y - 1] = "w";
                cost = Math.min(cost, dfs(arr, x, y - 1, "w", 3, visited, sum));
                visited[x][y - 1] = "";
            }
            
        } else if(direction.equals("w")){
            if(!(x + 1 < 0 || x + 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x + 1][y].isEmpty())){
                visited[x + 1][y] = "s";
                cost = Math.min(cost, dfs(arr, x + 1, y, "s", 3, visited, sum));
                visited[x + 1][y] = "";
            }
            if(!(x < 0 || x >= arr.length || y - 1 < 0 || y - 1 >= arr[0].length || !visited[x][y - 1].isEmpty() || budget == 0)){
                visited[x][y - 1] = "w";
                cost = Math.min(cost, dfs(arr, x, y - 1, "w", budget, visited, sum));
                visited[x][y - 1] = "";
            }
            if(!(x - 1 < 0 || x - 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x - 1][y].isEmpty())){
                visited[x - 1][y] = "n";
                cost = Math.min(cost, dfs(arr, x - 1, y, "n", 3, visited, sum));
                visited[x - 1][y] = "";
            }
        } else {
            if(!(x < 0 || x >= arr.length || y - 1 < 0 || y - 1 >= arr[0].length || !visited[x][y - 1].isEmpty())){
                visited[x][y - 1] = "w";
                cost = Math.min(cost, dfs(arr, x, y - 1, "w", 3, visited, sum));
                visited[x][y - 1] = "";
            }
            if(!(x - 1 < 0 || x - 1 >= arr.length || y < 0 || y >= arr[0].length || !visited[x - 1][y].isEmpty() || budget == 0)){
                visited[x - 1][y] = "n";
                cost = Math.min(cost, dfs(arr, x - 1, y, "n", budget, visited, sum));
                visited[x - 1][y] = "";
            }
            if(!(x < 0 || x >= arr.length || y + 1 < 0 || y + 1 >= arr[0].length || !visited[x][y + 1].isEmpty())){
                visited[x][y + 1] = "e";
                cost = Math.min(cost, dfs(arr, x, y + 1, "e", 3, visited, sum));
                visited[x][y + 1] = "";
            }
        }
        
        map.put(direction + ","+x+","+y + ","+(budget + 1), cost + arr[x][y]);
        

        return cost + arr[x][y];
    }
}
