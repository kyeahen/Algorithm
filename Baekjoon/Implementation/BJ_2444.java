package Baekjoon.Implementation;
import java.util.Scanner;

public class BJ_2444 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        int center_num = num + 3 / 2;

        for (int i = 1; i <= num; i++) {

            for (int k = 1; k <= center_num - i - 1; k++) {
                System.out.print(" ");
            }

            for (int j = 1; j<= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();

        }

        for (int i = num - 1; i >= 1; i--) {

            for (int j = i + 1; j < center_num; j++) {
                System.out.print(" ");
            }

            for (int k = 2 * i - 1; k >= 1; k--) {
                System.out.print("*");
            }
            System.out.println();

        }

    }
}

