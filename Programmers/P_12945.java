package Programmers;

//피보나치 수 - level2
public class P_12945 {

    public static void main (String[] args) {
        int n = 1;

        System.out.print(solution(n));
    }

    public static int solution (int n) {

        int a = 0, b = 1; // F(0), F(1)
        for (int i = 1; i <= n; i++) {
            int sum = a + b; // F(2) = F(0) + F(1)
            sum %= 1234567;
            a = b; //F(0) <- F(1)
            b = sum; // F(1) <- F(2) (F(0) + F(1))
        }

        return a;
    }
}
