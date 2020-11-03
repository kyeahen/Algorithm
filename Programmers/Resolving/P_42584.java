package Programmers.Resolving;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//주식가격 - 20.11.03 (스택/큐)
// - "원래는 배열로 풀이해서 큐로 재풀이해봤으나 속도는 배열이 더 빠른듯"
public class P_42584 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 3};
        System.out.print(Arrays.toString(solution(arr)));
    }

    public static int[] solution(int[] prices) {
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            q.add(prices[i]);
        }

        int i = 0;
        while (!q.isEmpty()) {
            int n = q.peek();

            int count = 0;
            for (int j = i + 1; j < prices.length; j++) {
                count++;

                if (n > prices[j]) {
                    break;
                }
            }

            q.poll();
            result[i] = count;
            i++;
        }

        return result;
    }
}
