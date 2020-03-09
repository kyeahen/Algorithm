package Programmers;

//자릿수 더하기 - level1
public class P_12931 {
    public static void main(String[] args) {
        int n = 987;
        System.out.print(solution(n));
    }

    public static int solution(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }

        return sum;
    }
}
