package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1021 회전하는 큐
 * Category : 자료구조, 덱
 * Date: 2021/03/16
 * ref -
 */
public class BJ_1021 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //큐의 크기
        int M = Integer.parseInt(st.nextToken()); //뽑아내려고 하는 수의 개수

        st = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 1; i <= N; i++) { //지민이가 뽑아내려고 하는 수의 위치
            dq.offer(i);
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            int pos = Integer.parseInt(st.nextToken());

            while (true) {
                int index = 0;
                Iterator<Integer> it = dq.iterator();

                while (it.hasNext()) {

                    if (it.next() == pos) {
                        break;
                    }

                    index++;
                }

                if (index == 0) {
                    dq.pollFirst();
                    break;
                } else if (index > dq.size()/ 2) {
                    dq.addFirst(dq.removeLast());
                    count++;
                } else {
                    dq.addLast(dq.removeFirst());
                    count++;
                }

            }
        }

        System.out.println(count);

    }
}
