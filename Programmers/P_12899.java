//124 나라의 숫자 - level2
public class P_12899 {

    public static void main(String[] args) {
        int n = 10;
        System.out.print(solution(n));
    }

    public static String solution(int n) {
        String result = "";

        while (n != 0) {
            int next = n % 3;
            n = n / 3;

            if (next == 0) {
                next = 4;
                n -= 1;
            }

            result = next + result;
        }

        return result;
    }
}
