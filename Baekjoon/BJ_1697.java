package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//숨바꼭질 - bfs
public class BJ_1697 {

    static int N, K;
    static int[] map;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        K = s.nextInt();

        map = new int[100001];

        bfs();
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N); //수빈 위치 큐에 삽입
        map[N] = 1; //방문 체크

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == K) { //수빈의 현재 위치와 동생의 위치가 같으면 탐색 종료
                System.out.println(map[current] - 1);
                return;
            }

            for (int i = 0; i < 3; i++) {
                int next = 0;

                if (i == 0) { //걷기 (x - 1)
                    next = current - 1;
                } else if (i == 1) { //걷기 (x + 1)
                    next = current + 1;
                } else { //순간이동 (x * 2)
                    next = current * 2;
                }

                if (0 <= next && next <= 100000) { //범위를 벗어나지 않을 때
                    if (map[next] == 0) { //다음 점 위치가 방문하지 않은 곳이면
                        q.add(next); //해당 점 위치 큐에 삽입
                        map[next] = map[current] + 1; //1초 증가
                    }
                }
            }
        }



    }
}
