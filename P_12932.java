import java.util.ArrayList;
import java.util.Arrays;

//자연수 뒤집어 배열로 만들기 - level1
public class P_12932 {
    public static void main(String[] args) {
        long n = 12345;
        System.out.print(Arrays.toString(solution(n)));
    }

    public static int[] solution(long n) {
        ArrayList<Integer> resultArr = new ArrayList<>();

        while (n != 0) {
            resultArr.add((int) (n % 10));
            n = n / 10;
        }

        return resultArr.stream().mapToInt(i -> i).toArray();
    }
}
