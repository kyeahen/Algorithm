package Programmers;
import java.util.Arrays;

//정수 내림차순으로 배치하기 - level1
public class P_12933 {
    public static void main(String[] args) {
        int n = 118372;
        System.out.print(solution(n));
    }

    public static long solution(long n) {
        String num = Long.toString(n);
        char[] numArr = num.toCharArray();

        Arrays.sort(numArr);

        String result = "";
        for (int i = numArr.length - 1; i >= 0; i--) {
            result += numArr[i];

        }

        return Long.parseLong(result);
    }
}
