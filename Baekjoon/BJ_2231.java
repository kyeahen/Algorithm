import java.util.Scanner;

public class BJ_2231 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int result = 0;
        for (int i = num; i >= 0; i--) {

            if (getSum(i) == num) {
                result = i;
            }
        }

        System.out.print(result);
    }

    //분해 합을 구하는 메소드
    public static int getSum(int n) {
        int sum = n;
        while (n != 0) {
            int next = n % 10;
            sum += next;
            n /= 10;
        }

        return sum;
    }
}
