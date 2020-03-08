import java.util.Scanner;

public class BJ_11050 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int k = s.nextInt();

        int result = factorial(n) / (factorial(n - k) * factorial(k));
        System.out.print(result);
    }

    public static int factorial(int num) {

        if (num == 1 || num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
