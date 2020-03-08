import java.util.Scanner;

public class BJ_2441 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        for (int i = num; i > 0; i--) {

            for (int j = 0; j < num - i; j++) {
                System.out.print(" ");
            }

            for (int k = i; k > 0; k--) {
                System.out.print("*");
            }

            System.out.println();
        }

    }
}
