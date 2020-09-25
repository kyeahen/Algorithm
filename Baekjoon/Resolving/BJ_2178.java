package Baekjoon.Resolving;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미로 탐색 - 20.09.25
public class BJ_2178 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(map[N - 1][M - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        map[nx][ny] = map[p.x][p.y] + 1;
                    }
                }
            }
        }
    }
}
