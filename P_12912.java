//두 정수 사이의 합 - level1
public class P_12912 {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        System.out.print(solution(a, b));
    }

    public static long solution(int a, int b) {
        long sum = 0;

        if (a < b) {
            for (int i = a; i <= b; i++) {
                sum += i;
            }

        } else {
            for (int i = b; i <= a; i++) {
                sum += i;
            }

        }

        return sum;
    }
}
