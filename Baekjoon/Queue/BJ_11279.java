package Baekjoon.Queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 11279 최대 힙
 * Category : 우선순위 큐
 * Date: 2021/02/03
 */

public class BJ_11279 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            int x = s.nextInt();

            if (x != 0) {
                pq.offer(x);
            } else {

                if (!pq.isEmpty()) {
                    System.out.println(pq.poll());
                } else {
                    System.out.println(0);
                }
            }
        }

    }
}
