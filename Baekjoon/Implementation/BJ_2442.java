package Baekjoon.Implementation;
import java.util.Scanner;

public class BJ_2442 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int center_num = num + 3 / 2;

        for (int i = 1; i <= num; i++) {

            for (int k = 1; k <= center_num - i - 1; k++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

}
