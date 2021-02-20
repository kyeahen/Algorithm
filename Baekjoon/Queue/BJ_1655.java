package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * Created by kyeahen.
 * Title : 1655 가운데를 말해요
 * Category : 우선순위 큐
 * Date: 2021/02/03
 */
public class BJ_1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minQ = new PriorityQueue<>(); //오름차순 poll
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> o2 - o1); //내림차순 poll

        // bufferedReader + system 출력시 - 시간초과 발생
        // => stringbuilder 사용하여 시간초과 해결
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (minQ.size() == maxQ.size()) { //큐의 크기가 같은 경우, max에 저장
                maxQ.offer(num);
            } else { //큐의 크기가 다른 경우, min에 저장
                minQ.offer(num);
            }

            if (!minQ.isEmpty() && !maxQ.isEmpty()) {

                if (minQ.peek() < maxQ.peek()) { //maxQ의 최댓값이 minQ의 최솟값보다 클 경우, swap
                    int min = minQ.poll();
                    int max = maxQ.poll();

                    maxQ.offer(min);
                    minQ.offer(max);
                }
            }

            sb.append(maxQ.peek() + "\n"); //max top 값이 중간값
        }

        System.out.println(sb.toString());

    }
}
