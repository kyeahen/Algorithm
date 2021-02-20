package Baekjoon.Implementation;
import java.util.Scanner;

public class BJ_10871 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int inputCount = s.nextInt();
        int standard = s.nextInt();

        int[] numList = new int[inputCount];
        for (int i = 0; i < numList.length; i++) {
            numList[i] = s.nextInt();
        }

        for (int i = 0; i < numList.length; i++) {
            if (numList[i] < standard) {
                System.out.print(numList[i] + " ");
            }
        }
    }
}
