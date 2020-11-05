package Baekjoon.Greedy;
import java.util.Scanner;

//거스름돈 - Greedy
public class BJ_5585 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int money = s.nextInt();
        int change = 1000 - money;

        int count = 0;
        while (change > 0) {

            if (change >= 500) {
                change -= 500;
            } else if (change >= 100) {
                change -= 100;
            } else if (change >= 50) {
                change -= 50;
            } else if (change >= 10) {
                change -= 10;
            } else if (change >= 5) {
                change -= 5;
            } else {
                change -= 1;
            }

            count++;
        }

        System.out.print(count);
    }
}
