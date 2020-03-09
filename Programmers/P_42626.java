package Programmers;
import java.util.PriorityQueue;

//더 맵게 - level2
public class P_42626 {

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;

        System.out.print(solution(scoville, k));
    }

    public static int solution(int[] scoville, int k) {

        // 우선순위 큐 (힙 정렬)
        // - 우선순위가 높은 순번대로 뽑힌다. (1, 2, 3...)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // int형 배열을 우선순위 큐에 넣어주기
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }

        int count = 0;
        while (pq.peek() < k) { //맨 앞의 데이터가 k 이상이 아닐 동안 반복

            // (모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우)
            // - 음식이 1개면 섞을 수 없으니 -1 반환
            if (pq.size() == 1) {
                return -1;
            }

            int first = pq.poll(); //가장 맵지 않은 음식 뽑기 (맨 앞 데이터, 1)
            int second = pq.poll(); //두번째로 맵지 않은 음식 뽑기 (그 다음 데이터, 2)

            int result = first + (second * 2); //섞은 음식의 스코빌 지수 계산
            pq.offer(result); //결과값 우선순위 큐에 삽입

            count++; //횟수 카운팅
        }

        return count;
    }
}
