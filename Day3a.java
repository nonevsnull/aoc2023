import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Day3a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));

        List<String> lst = new ArrayList<>();
        
        while (br.ready()) {
            lst.add(br.readLine());    
        }
        char[][] arr = new char[lst.size()][lst.get(0).length()];
        int sum = 0;
        for(int i = 0;i < lst.size();i++){
            arr[i] = lst.get(i).toCharArray();
        }
        for(int i = 0;i < arr.length;i++){
            for(int j = 0;j < arr[0].length;j++){
                if(!Character.isDigit(arr[i][j]) && arr[i][j] != '.'){
                    sum += getNums(arr, i, j);
                }
            }
        }
        System.out.println(sum);


    }

    static int getNums(char[][] arr, int row, int col){
        int sum = 0;
        //upper left
        if(row - 1 >= 0 && col - 1 >= 0 && Character.isDigit(arr[row - 1][col - 1])){
            int num = getNum(arr, row - 1, col - 1);            
            sum += num;
        }
        //upper center
        if(row - 1 >= 0 && Character.isDigit(arr[row - 1][col])){
            int num = getNum(arr, row - 1, col);            
            sum += num;
        }
        //upper right
        if(row - 1 >= 0 && col + 1 < arr[0].length && Character.isDigit(arr[row - 1][col + 1])){
            int num = getNum(arr, row - 1, col + 1);            
            sum += num;
        }
        //right
        if(col + 1 < arr[0].length && Character.isDigit(arr[row][col + 1])){
            int num = getNum(arr, row, col + 1);            
            sum += num;
        }
        //lower right
        if(row + 1 < arr.length && col + 1 < arr[0].length && Character.isDigit(arr[row + 1][col + 1])){
            int num = getNum(arr, row + 1, col + 1);            
            sum += num;
        }
        //lower center
        if(row + 1 < arr.length && Character.isDigit(arr[row + 1][col])){
            int num = getNum(arr, row + 1, col);            
            sum += num;
        }
        //lower left
        if(row + 1 < arr.length && col - 1 >= 0 && Character.isDigit(arr[row + 1][col - 1])){
            int num = getNum(arr, row + 1, col - 1);            
            sum += num;
        }
        //left
        if(col - 1 >= 0 && Character.isDigit(arr[row][col - 1])){
            int num = getNum(arr, row, col - 1);            
            sum += num;
        }
        return sum;
    }

    static int getNum(char[][] arr, int row, int col){
        Deque<Integer> dq = new LinkedList<>();
        int pos = col;

        while(pos >= 0 && Character.isDigit(arr[row][pos])){
            dq.addFirst(arr[row][pos] - '0');
            arr[row][pos] = '.';
            pos--;
        }
        pos = col + 1;
        while(pos < arr[0].length && Character.isDigit(arr[row][pos])){
            dq.addLast(arr[row][pos] - '0');
            arr[row][pos] = '.';
            pos++;
        }
        StringBuilder sb = new StringBuilder();
        while(!dq.isEmpty()){
            sb.append(dq.pollFirst());
        }
        return Integer.parseInt(sb.toString());
    }
}
