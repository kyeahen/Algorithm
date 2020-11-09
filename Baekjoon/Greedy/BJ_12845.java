package Baekjoon.Greedy;

import java.util.Scanner;
import java.util.Arrays;

//모두의 마블
public class BJ_12845 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[] card = new int[n];

        for (int i = 0; i < n; i++) {
            card[i] = s.nextInt();
        }

        Arrays.sort(card);

        int sum = 0;
        int lv = card[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            lv = Math.max(lv, card[i]);
            sum += lv + card[i];
        }

        System.out.println(sum);
    }
}
