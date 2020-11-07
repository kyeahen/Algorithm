package Baekjoon.Greedy;
import java.util.*;

//강의실 배정
public class BJ_11000 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();
        int[][] time = new int[N][2];

        for (int i = 0; i < N; i++) {
            time[i][0] = s.nextInt();
            time[i][1] = s.nextInt();
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[0] == o2[0]) { //시작 시간이 같으면
                    return Integer.compare(o1[1], o2[1]);
                }

                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int start = time[i][0];
            int end = time[i][1];

            if (!pq.isEmpty() && pq.peek() <= start) { //해당 수업이 끝나고 맞는 시간대의 수업일 때 (강의실 재사용 가능)
                pq.poll();
            }

            pq.add(end); //다음 수업 추가 (종료 시간)
        }

        System.out.println(pq.size());
    }
}
