import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day10b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        List<String> strs = new ArrayList<>();

        while (br.ready()) {
            strs.add(br.readLine());
        }
        
        char[][] graph = new char[strs.size()][strs.get(0).length()];
        int row = 0, col = 0;
        for(int i = 0;i < strs.size();i++){
            for(int j = 0;j < strs.get(0).length();j++){
                graph[i][j] = strs.get(i).charAt(j);
                if(graph[i][j] == 'S'){
                    row = i;
                    col = j;
                }
            }
        }
        char[][] path = bfs(graph, row, col + 1, 'w');
        if(path.length == 0){
            path = bfs(graph, row + 1, col, 'n');
            if(path.length == 0){
                path = bfs(graph, row, col - 1, 'e');
                if(path.length == 0){
                    path = bfs(graph, row - 1, col, 's');
                }
            }
        }
        path[row][col] = 'S';
        for(char[] r : path){
            for(char c : r){
                System.out.print(c + " ");
            }
            System.out.println("");
        }
        System.out.println(calculate(path));
        
    }

    static int calculate(char[][] path){
        int sum = 0;
        for(int i = 0;i < path.length;i++){
            int count = 0;
            for(int j = 0;j < path[i].length;j++){
                if(path[i][j] == '1'){
                    if(count % 2 == 1){
                        sum++;
                    }
                } else if(path[i][j] != 'L' && path[i][j] != 'J' && path[i][j] != '-') {
                    count++;
                }
            }
        }
        return sum;
    }


    static char[][] bfs(char[][] graph, int sx, int sy, char from) {
        boolean found = false;
        int row = sx, col = sy;
        char[][] visited = new char[graph.length][graph[0].length];
        for(char[] r : visited){
            Arrays.fill(r, '1');
        }
        
        while(!found && !(row < 0 || row >= graph.length || col < 0 || col >= graph[0].length)){
            char c = graph[row][col];
            visited[row][col] = c;
            if(c == 'S'){
                found = true;
            } else {
                if(c == '|'){
                    if(from  == 'n'){
                        row++;
                    } else if(from  == 's') {
                        row--;
                    } else {
                        break;
                    }
                } else if(c == '-'){
                    if(from  == 'w'){
                        col++;
                        from = 'w';
                    } else if(from == 'e') {
                        col--;
                        from = 'e';
                    } else {
                        break;
                    }
                } else if(c == 'L'){
                    if(from  == 'n'){
                        col++;
                        from = 'w';
                    } else if(from  == 'e') {
                        row--;
                        from = 's';
                    } else {
                        break;
                    }
                } else if(c == 'J'){
                    if(from  == 'n'){
                        col--;
                        from = 'e';
                    } else if(from == 'w'){
                        row--;
                        from = 's';
                    } else {
                        break;
                    }
                } else if(c == '7'){
                    if(from  == 's'){
                        col--;
                        from = 'e';
                    } else if(from  == 'w') {
                        row++;
                        from = 'n';
                    } else {
                        break;
                    }
                } else if(c == 'F'){
                    if(from  == 's'){
                        col++;
                        from = 'w';
                    } else if(from  == 'e') {
                        row++;
                        from = 'n';
                    } else {
                        break;
                    }
                }
            }
        }
        if(found){
            return visited;
        } else {
            return new char[0][0];
        }
    }
}
