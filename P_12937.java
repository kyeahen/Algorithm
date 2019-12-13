//짝수와 홀수 - level1
public class P_12937 {
    public static void main(String[] args) {
        int num = 2;
        System.out.print(solution(num));
    }

    public static String solution(int num) {
        if (num % 2 == 0) {
            return "Even";
        }

        return "Odd";
    }
}
