package Baekjoon;
import java.util.Scanner;

//연결 요소의 개수
// - dfs
public class BJ_11724 {

    static int N, M;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            map[a - 1][b - 1] = map[b - 1][a - 1] = 1;
        }

        int count = 0;
        for (int i = 0; i < N; i++) {

            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    public static void dfs(int x) {
        visited[x] = true;

        for (int i = 0; i < N; i++) {
            if (!visited[i] && map[x][i] == 1) {
                dfs(i);
            }
        }
    }
}
