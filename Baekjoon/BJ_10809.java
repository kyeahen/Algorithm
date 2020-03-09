package Baekjoon;
import java.util.Scanner;

public class BJ_10809 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String input = s.next();

        for (char c = 'a'; c <= 'z'; c++) {
            System.out.print(input.indexOf(c) + " ");
        }
    }
}
