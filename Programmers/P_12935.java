package Programmers;
import java.util.ArrayList;
import java.util.Arrays;

//제일 작은 수 제거하기 - level1
public class P_12935 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        System.out.print(Arrays.toString(solution(arr)));
    }

    public static int[] solution(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != min) {
                resultArr.add(arr[i]);
            }
        }

        if (resultArr.size() == 0) {
            resultArr.add(-1);
        }

        return resultArr.stream().mapToInt(i -> i).toArray();
    }
}
