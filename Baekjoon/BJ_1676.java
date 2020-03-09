package Baekjoon;
import java.util.Scanner;

public class BJ_1676 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 5 == 0) {
                count++;
            }

            if(i % 25 == 0) {
                count++;
            }

            if (i % 125 == 0) {
                count++;
            }
        }

        System.out.print(count);
    }

}
