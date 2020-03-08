import java.util.ArrayList;
import java.util.Arrays;

//같은 숫자는 싫어 - level1
public class P_12906 {
    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 3, 3};

        System.out.print(Arrays.toString(solution(arr)));
    }

    public static int[] solution(int[] arr) {
        ArrayList<Integer> resultArr = new ArrayList<>();

        int temp = -1;
        for (int i : arr) {
            if (temp != i) {
                resultArr.add(i);
            }

            temp = i;
        }

        return resultArr.stream().mapToInt(i -> i).toArray();
    }
}
