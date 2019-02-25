import java.util.Scanner;

public class BJ_2747 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int input = s.nextInt();
        System.out.print(fibo(input));
    }

    public static int fibo(int num) {
        if (num == 0 || num == 1) {
            return num;
        } else {
            return fibo(num - 1) + fibo(num - 2);
        }
    }
}
