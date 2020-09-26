package Baekjoon.Resolving;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//안전영역 - 20.09.26
public class BJ_2468 {

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int result = 0; //안전영역 최대값

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        int high = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                high = Math.max(high, map[i][j]);
            }
        }

        for (int k = 0; k < high; k++) {
            visited = new boolean[N][N];

            int area = 0; //안전지역 개수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (map[i][j] > k && !visited[i][j]) {
                        //bfs(i, j, k);
                        dfs(i, j, k);
                        area++;
                    }
                }
            }
            result = Math.max(result, area);
        }

        System.out.println(result);
    }

    public static void dfs(int x, int y, int k) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (map[nx][ny] > k && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, k);
                }
            }
        }
    }

    public static void bfs(int x, int y, int k) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {

                    if (map[nx][ny] > k && !visited[nx][ny]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
