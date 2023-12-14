import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day14a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<String> lst = new ArrayList<>();

        while (br.ready()) {
            lst.add(br.readLine());
        }
        char[][] arr = new char[lst.size()][lst.get(0).length()];
        char[][] original = new char[lst.size()][lst.get(0).length()];
        for(int i = 0;i < lst.size();i++){
            arr[i] = lst.get(i).toCharArray();
            original[i] = lst.get(i).toCharArray();
        }
        // printArr(arr);
        // toEast(arr);
        // printArr(arr);
        // int sum = count(arr);
        // System.out.println(sum);

        int count = 0;
        int found = -1;
        HashMap<String, Integer> visited = new HashMap<>();
        for(int i = 0;i < 9300;i++){
            count++;
            toNorth(arr);
            found = record(visited, arr, count);
            if(found > 0){
                break;
            }
            count++;
            toWest(arr);
            found = record(visited, arr, count);
            if(found > 0){
                break;
            }
            count++;
            toSouth(arr);
            found = record(visited, arr, count);
            if(found > 0){
                break;
            }
            count++;
            toEast(arr);
            found = record(visited, arr, count);
            if(found > 0){
                break;
            }
            printArr(arr);
        }
        System.out.println(count);
        System.out.println(found);
        int loop = count - found;
        long n = 4000000000L;
        n -= count;
        n %= loop;
        // int fp = found % 4;
        // toSouth(arr);
        // toEast(arr);
        // n -= 2;
        while(n > 0){
            
            if(n > 0){
                toSouth(arr);
                n--;
            }
            if(n > 0){
                toEast(arr);
                n--;
            }
            if(n > 0){
                toNorth(arr);
                n--;
            }
            
            if(n > 0){
                toWest(arr);
                n--;
            }
        }
        printArr(arr);
        System.out.println(count(arr));
    }

    static int record(HashMap<String, Integer> visited, char[][] arr, int count){
        StringBuilder sb = new StringBuilder();
        for(char[] cc : arr){
            sb.append(cc);
        }
        if(visited.containsKey(sb.toString())){
            return visited.get(sb.toString());
        } else {
            visited.put(sb.toString(), count);
            return -1;
        }
    }

    static boolean compare(char[][] arr1, char[][] arr2){
        for(int i = 0;i < arr1.length;i++){
            for(int j = 0;j < arr1[0].length;j++){
                if(arr1[i][j] != arr2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    static void printArr(char[][] arr){
        System.out.println("");
        for(char[] ar : arr){
            System.out.println(Arrays.toString(ar));
        }
    }

    static void toEast(char[][] arr){
        for(int i = 0;i < arr.length;i++){
            int lastRockCol = arr[i].length;
            for(int j = arr[i].length - 1;j >= 0;j--){
                if(arr[i][j] == 'O'){
                    if(lastRockCol - 1 > j){
                        arr[i][lastRockCol - 1] = 'O';
                        arr[i][j] = '.';
                    }
                    lastRockCol--;
                } else if(arr[i][j] == '#'){
                    lastRockCol = j;
                }
            }
        }
    }

    static void toWest(char[][] arr){
        for(int i = 0;i < arr.length;i++){
            int lastRockCol = -1;
            for(int j = 0;j < arr[i].length;j++){
                if(arr[i][j] == 'O'){
                    if(lastRockCol + 1 < j){
                        arr[i][lastRockCol + 1] = 'O';
                        arr[i][j] = '.';
                    }
                    lastRockCol++;
                } else if(arr[i][j] == '#'){
                    lastRockCol = j;
                }
            }
        }
    }

    static void toNorth(char[][] arr){
        for(int i = 0;i < arr[0].length;i++){
            int lastRockRow = -1;
            for(int j = 0;j < arr.length;j++){
                if(arr[j][i] == 'O'){
                    if(lastRockRow + 1 < j){
                        arr[lastRockRow + 1][i] = 'O';
                        arr[j][i] = '.';
                    }
                    lastRockRow++;
                } else if(arr[j][i] == '#'){
                    lastRockRow = j;
                }
            }
        }
    }

    static void toSouth(char[][] arr){
        for(int i = 0;i < arr[0].length;i++){
            int lastRockRow = arr.length;
            for(int j = arr.length - 1;j >= 0;j--){
                if(arr[j][i] == 'O'){
                    if(lastRockRow - 1 > j){
                        arr[lastRockRow - 1][i] = 'O';
                        arr[j][i] = '.';
                    }
                    lastRockRow--;
                } else if(arr[j][i] == '#'){
                    lastRockRow = j;
                }
            }
        }
    }

    static int count(char[][] arr){
        int sum = 0;
        for(int i = 0;i < arr.length;i++){
            int point = arr.length - i;
            for(int j = 0;j < arr[i].length;j++){
                if(arr[i][j] == 'O'){
                    sum += point;
                }
            }
        }
        return sum;
    }
}
