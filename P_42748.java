import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//K번째 수 - level1
public class P_42748 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        System.out.print(Arrays.toString(solution(array, commands)));
    }

    public static int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> tempArr = new ArrayList<>();
        ArrayList<Integer> resultArr = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            tempArr.clear();
            int a = commands[i][0] - 1;
            int b = commands[i][1] - 1;
            int k = commands[i][2] - 1;

            for (int j = a; j <= b; j++) {
                tempArr.add(array[j]);
            }

            Collections.sort(tempArr);
            resultArr.add(tempArr.get(k));
        }

        return resultArr.stream().mapToInt(i -> i).toArray();
    }
}
