package Baekjoon;
import java.util.Scanner;

public class BJ_3053 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int r  = s.nextInt();

        double euclidArea = Math.PI * r * r;
        double taxiArea = 2 * r * r;

        System.out.println(euclidArea);
        System.out.println(taxiArea);
    }
}
