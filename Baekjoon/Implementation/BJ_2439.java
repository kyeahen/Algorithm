package Baekjoon.Implementation;
import java.util.Scanner;

public class BJ_2439 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        for (int i = num; i > 0; i--) {

            for (int j = i; j > 1; j--) {
                System.out.print(" ");
            }

            for (int k = 0; k < num - i + 1;  k++) {
                System.out.print("*");
            }

            System.out.println();
        }

    }

}
