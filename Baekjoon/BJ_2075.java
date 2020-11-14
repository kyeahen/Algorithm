package Baekjoon;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//n번째 큰수 - 우선순위 큐
public class BJ_2075 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = s.nextInt();
                pq.add(a);
            }
        }

        int ans = 0;
        int count = 1;
        while (!pq.isEmpty()) {

            if (count == n) {
                ans = pq.peek();
                break;
            }

            pq.poll();
            count++;
        }

        System.out.println(ans);
    }
}
