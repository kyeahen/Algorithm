import java.util.Scanner;

public class BJ_2445 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        for (int i = 0; i < num; i++) {

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            for (int k = 0; k < (num - i - 1) * 2; k++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < num; i++) {

            for (int j = num - i - 1; j > 0; j--) {
                System.out.print("*");
            }

            for (int k = 0; k <= i * 2 + 1; k++) {
                System.out.print(" ");
            }

            for (int j = num - i - 1; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
