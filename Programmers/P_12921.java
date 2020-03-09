package Programmers;
import java.util.Scanner;

//소수 찾기 - level1
public class P_12921 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        System.out.print(solution2(n));
    }

    public static int solution1(int n) {
        int total = 0;
        for (int i = 2; i <= n; i++) {

            int count = 0;
            for (int j = 2; j <= n; j++) {
                if (i % j == 0) {
                    count++;
                }
            }

            if (count == 1) {
                total++;
            }
        }

        return total;
    }

    public static int solution2(int n) {

        int count = 0;
        for (int i = 2; i <= n; i++) {

            boolean flag = true;
            int a = (int) Math.sqrt(i);
            for (int j = 2; j <= a; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag == true) {
                count++;
            }
        }

        return count;
    }
}
