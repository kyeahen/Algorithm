package Baekjoon.Graph.Search;
import java.util.Scanner;

//음식물 피하기
// - dfs
public class BJ_1743 {

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int count;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();
        K = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            map[a - 1][b - 1] = 1;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (!visited[i][j] && map[i][j] == 1) {
                    count = 0;
                    dfs(i, j);
                    max = Math.max(count, max);
                }

            }
        }

        System.out.println(max);
    }

    public static void dfs(int x, int y) {

        count++;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
