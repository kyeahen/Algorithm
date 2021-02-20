package Baekjoon.String;
import java.util.Scanner;

public class BJ_11720 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int inputNum = s.nextInt();
        String input = s.next();

        int sum = 0;
        for(int i = 0; i < inputNum; i++) {
            int num = Character.digit(input.charAt(i), 10);
            sum += num;
        }

        System.out.print(sum);
    }
}
