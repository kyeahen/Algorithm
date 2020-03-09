package Baekjoon;
import java.util.Scanner;

public class BJ_10818 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int min = 1000000, max = -1000000;

        for (int i = 0; i < num; i++) {
            int n = s.nextInt();

            if (min > n) {
                min = n;
            }

            if (max < n) {
                max = n;
            }
        }

        System.out.print(min + " " + max);
    }
}
