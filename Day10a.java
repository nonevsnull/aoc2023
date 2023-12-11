import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day10a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        List<String> strs = new ArrayList<>();

        while (br.ready()) {
            strs.add(br.readLine());
        }
        
        char[][] graph = new char[strs.size()][strs.get(0).length()];
        char[][] visited = new char[strs.size()][strs.get(0).length()];
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
        List<Character> path = bfs(graph, row, col + 1, 'w');
        if(path.isEmpty()){
            path = bfs(graph, row + 1, col, 'n');
            if(path.isEmpty()){
                path = bfs(graph, row, col - 1, 'e');
                if(path.isEmpty()){
                    path = bfs(graph, row - 1, col, 's');
                }
            }
        }
        System.out.println((path.size() + 1) / 2);
        
    }

    static List<Character> bfs(char[][] graph, int sx, int sy, char from) {
        boolean found = false;
        int row = sx, col = sy;
        List<Character> path = new ArrayList<>();
        while(!found && !(row < 0 || row >= graph.length || col < 0 || col >= graph[0].length)){
            char c = graph[row][col];
            if(c == 'S'){
                found = true;
            } else {
                path.add(c);
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
            return path;
        } else {
            return new ArrayList<>();
        }
    }


    static boolean dfs(char[][] graph, char[][] visited, List<Character> path, int row, int col, char from){
        char c = graph[row][col];
        if(row < 0 || row >= graph.length || col < 0 || col >= graph[0].length){
            return false;
        }
        if(c == 'S' && path.size() > 0){
            return true;
        }
        if(c == '.'){
            return false;
        }
        path.add(c);
        visited[row][col] = 1;
        boolean found = false;
        if(c == '|'){
            if(from  == 'n'){
                found = dfs(graph, visited, path, row + 1, col, 'n');
            } else if(from  == 's') {
                found = dfs(graph, visited, path, row - 1, col, 's');
            } else {
                return false;
            }
            
        } else if(c == '-'){
            if(from  == 'w'){
                found = dfs(graph, visited, path, row, col + 1, 'w');
            } else if(from == 'e') {
                found = dfs(graph, visited, path, row, col - 1, 'e');
            } else {
                return false;
            }
        } else if(c == 'L'){
            if(from  == 'n'){
                found = dfs(graph, visited, path, row, col + 1, 'w');
            } else if(from  == 'e') {
                found = dfs(graph, visited, path, row - 1, col, 's');
            } else {
                return false;
            }
        } else if(c == 'J'){
            if(from  == 'n'){
                found = dfs(graph, visited, path, row, col - 1, 'e');
            } else if(from == 'w'){
                found = dfs(graph, visited, path, row - 1, col, 's');
            } else {
                return false;
            }
        } else if(c == '7'){
            if(from  == 's'){
                found = dfs(graph, visited, path, row, col - 1, 'e');
            } else if(from  == 'w') {
                found = dfs(graph, visited, path, row + 1, col, 'n');
            } else {
                return false;
            }
        } else if(c == 'F'){
            if(from  == 's'){
                found = dfs(graph, visited, path, row, col + 1, 'w');
            } else if(from  == 'e') {
                found = dfs(graph, visited, path, row + 1, col, 'n');
            } else {
                return false;
            }
        }

        return found;


        
    }
}
