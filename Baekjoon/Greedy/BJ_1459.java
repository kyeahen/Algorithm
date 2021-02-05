package Baekjoon.Greedy;

import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 1459 걷기
 * Date: 2021/02/02
 */
public class BJ_1459 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long x = sc.nextInt();
        long y = sc.nextInt();

        long w = sc.nextInt(); //가로, 세로
        long s = sc.nextInt(); //대각선

        // 1. 가로, 세로 이동
        long cost1 = (x + y) * w;

        // 2. 대각선 이동
        long cost2 = 0;
        if ((x + y) % 2 == 0) { //짝수 - 대각선으로만 이동 가능
            cost2 = Math.max(x, y) * s;
        } else { //홀수 - 대각선 이동 후, 가로 이동 1칸
            cost2 = (Math.max(x, y) - 1) * s + w;
        }

        // 3. 가로, 세로 + 대각선 이동
        // - 최소값이 될 수 있도록 대각선으로 최소한 이동한 후,
        long cost3 = Math.min(x, y) * s + Math.abs(x - y) * w;

        System.out.println(Math.min(Math.min(cost1, cost2), cost3));
    }
}
