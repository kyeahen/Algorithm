package Baekjoon.Implementation;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_2750 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        int[] inputArr = new int[num];

        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = s.nextInt();
        }

        Arrays.sort(inputArr);

        for (int i = 0; i < inputArr.length; i++) {
            System.out.println(inputArr[i]);
        }

    }

}
