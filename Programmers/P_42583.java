package Programmers;

import java.util.LinkedList;
import java.util.Queue;

//다리를 지나는 트럭 - level2
// - 스택, 큐
public class P_42583 {

    public static void main(String[] args) {

        int[] arr = {7, 4, 5, 6};
        int[] arr1 = {10};
        int[] arr2 = {10,10,10,10,10,10,10,10,10,10};
        System.out.println(solution(100, 100, arr1));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();

        int time = 0;
        int sum = 0;
        int i = 0;

        while (true) {
            int w = truck_weights[i];

            if (q.isEmpty()) {
                q.add(w);
                sum += w;

                time += 1;
                i += 1;
            } else if (q.size() == bridge_length) {
                sum -= q.poll();
            } else {
                if (sum + w > weight) {
                    q.add(0);
                    time += 1;
                } else {
                    q.add(w);
                    sum += w;

                    time += 1;
                    i += 1;
                }
            }

            if (q.size() == 0) {
                break;
            }
        }

        return time + bridge_length;
    }
}
