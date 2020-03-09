package Baekjoon;
import java.util.Scanner;

public class BJ_2941 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String[] croArray = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        String input = s.next();

        for (int i = 0; i < croArray.length; i++) {
            input = input.replace(croArray[i], "r");
        }

        System.out.print(input.length());
    }
}
