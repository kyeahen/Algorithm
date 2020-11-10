package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//벽 부수고 이동하기 - bfs
public class BJ_2206 {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0, 0, 1);

    }

    public static void bfs(int x, int y, int k, int count) {
        Queue<Move> q = new LinkedList<>();
        q.add(new Move(x, y, k, count));
        visited[x][y][k] = true;

        while (!q.isEmpty()) {
            Move v = q.poll();

            if (v.x == N - 1 && v.y == M - 1) {
                System.out.println(v.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + v.x;
                int ny = dy[i] + v.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                    if (!visited[nx][ny][v.k]) {

                        if (map[nx][ny] == 0) { //이동 가능
                            q.add(new Move(nx, ny, v.k, v.count + 1));
                            visited[nx][ny][v.k] = true;
                        } else {

                            if (v.k < 1) {
                                q.add(new Move(nx, ny, v.k + 1, v.count + 1));
                                visited[nx][ny][v.k + 1] = true;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
