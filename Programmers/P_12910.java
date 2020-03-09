package Programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//나누어 떨어지는 숫자 배열 - level1
public class P_12910 {

    public static void main(String[] args) {
        int[] arr = {2, 36, 1, 3};
        int divisor = 1;

        System.out.print(Arrays.toString(solution(arr, divisor)));
    }

    public static int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> resultArr = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                resultArr.add(arr[i]);
            }
        }

        if (resultArr.size() == 0) {
            resultArr.add(-1);
        }

        Collections.sort(resultArr);

        return resultArr.stream().mapToInt(i -> i).toArray();
    }
}
