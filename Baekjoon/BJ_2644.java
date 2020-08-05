package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//촌수 계산 - bfs, dp
public class BJ_2644 {

    static int n; //전체 사람의 수
    static int a, b; //촌수를 계산해야하는 서로 다른 사람의 번호
    static int m; //부모 자식들 간의 관계의 개수

    static int[][] map; //촌수 정보를 저장하는 배열
    static boolean[] visited; //방문 체크 배열 (사람)
    static int[] count_dp; //촌수를 저장하는 배열

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();

        a = s.nextInt();
        b = s.nextInt();

        m = s.nextInt();

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        count_dp = new int[n + 1];

        //부모 자식 관계(1촌 관계)를 배열에 입력
        for (int i = 1; i <= m; i++) {
            int x = s.nextInt();
            int y = s.nextInt();

            map[x][y] = map[y][x] = 1;
        }

        int result = bfs();
        if (result == 0) { //두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(a);

        while (!q.isEmpty()) {
            int a1 = q.poll();
            visited[a1] = true; //방문 체크

            for (int i = 1; i <= n; i++) {

                //a1과 i가 1촌이고, 아직 방문하지 않았을 때
                if (map[a1][i] == 1 && visited[i] == false) {
                    visited[i] = true; //방문 체크
                    count_dp[i] = count_dp[a1] + 1; //촌수 증가 (+1)
                    q.add(i); //i를 큐에 삽입
                }
            }
        }

        return count_dp[b];
    }
}
