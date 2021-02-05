package Baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 11286 절댓값 힙
 * Category : 우선순위 큐
 * Date: 2021/02/03
 */

class X implements Comparable<X> {
    int num;
    int abs;

    public X(int num, int abs) {
        this.num = num;
        this.abs = abs;
    }

    @Override
    public int compareTo(X o) {
        if (this.abs == o.abs) {
            return Integer.compare(this.num, o.num); //오름차순
        }

        return Integer.compare(this.abs, o.abs); //오름차순
    }
}

public class BJ_11286 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        PriorityQueue<X> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int x = s.nextInt();

            if (x != 0) {
                pq.offer(new X(x, Math.abs(x)));
            } else {

                if (!pq.isEmpty()) {
                    System.out.println(pq.poll().num);
                } else {
                    System.out.println(0);
                }
            }
        }
    }
}
