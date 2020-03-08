import java.util.Arrays;

//최대공약수와 최대공배수 - level1
public class P_12940 {
    public static void main(String[] args) {
        System.out.print(Arrays.toString(solution(2, 5)));
    }

    public static int[] solution(int n, int m) {
        int[] arr = new int[2];

        //최대공약수
        for (int i = 1; i <= m; i++) {
            if (n % i == 0 && m % i == 0) {
                arr[0] = i;
            }
        }

        //최소공배수
        arr[1] = (n * m) / arr[0];

        return arr;
    }
}

