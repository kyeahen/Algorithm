package Baekjoon;
import java.util.Scanner;

public class BJ_2438 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
