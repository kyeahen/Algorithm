package Baekjoon.Graph.Search;
import java.util.Scanner;

//유기농 배추
// - dfs
public class BJ_1012 {

    static int T, M, N, K;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        T = s.nextInt(); //테스트 케이스의 개수

        for (int i = 0; i < T; i++) {
            M = s.nextInt(); //가로길이
            N = s.nextInt(); //세로길이
            K = s.nextInt(); //배추가 심어져 있는 위치의 개수

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int a = 0; a < K; a++) {
                int x = s.nextInt(); //열
                int y = s.nextInt(); //행

                map[y][x] = 1;
            }

            int count = 0;
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) {

                    if (map[a][b] == 1 && !visited[a][b]) {
                        dfs(a, b);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                if (!visited[nx][ny] && map[nx][ny] != 0) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
