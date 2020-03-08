import java.util.Arrays;

//x만큼 간격이 있는 n개의 숫자 - level1
public class P_12954 {
    public static void main(String[] args) {
        int x = -4;
        int n = 2;
        System.out.print(Arrays.toString(solution(x, n)));
    }

    public static long[] solution(int x, int n) {
        long[] arr = new long[n];

        for (int i = 1; i <= n; i++) {
            arr[i - 1] = (long) x * i;
        }

        return arr;
    }
}
