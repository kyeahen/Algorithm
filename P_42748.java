import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//K번째 수 - level1
public class P_42748 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        System.out.print(Arrays.toString(solution1(array, commands)));
    }

    public static int[] solution1(int[] array, int[][] commands) {
        ArrayList<Integer> tempArr = new ArrayList<>();
        int[] resultArr = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            tempArr.clear();
            int a = commands[i][0] - 1;
            int b = commands[i][1] - 1;
            int k = commands[i][2] - 1;

            for (int j = a; j <= b; j++) {
                tempArr.add(array[j]);
            }

            Collections.sort(tempArr);
            resultArr[i] = resultArr[k];
        }

        return resultArr;
    }

    public static int[] solution2(int[] array, int[][] commands) {
        int[] resultArr = new int[commands.length];

        for (int i = 0 ; i < commands.length; i++) {
            int a = commands[i][0] - 1;
            int b = commands[i][1] - 1;
            int k = commands[i][2] - 1;

            int[] temp = Arrays.copyOfRange(array, a, b);
            Arrays.sort(temp);
            resultArr[i] = temp[k];
        }

        return resultArr;
    }
}
