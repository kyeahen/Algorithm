package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//카드2 - 큐
public class BJ_2164 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Queue<Integer> q = new LinkedList<>();
        int n = s.nextInt();

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        while (!q.isEmpty()) {

            if (q.size() == 1) {
                break;
            }

            int first = q.poll();
            int second = q.poll();
            q.add(second);
        }

        System.out.println(q.poll());
    }
}
