package Baekjoon.Resolving;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//토마토 - 20.09.25
public class BJ_7576 {

    static int M, N;
    static int[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        M = s.nextInt();
        N = s.nextInt();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        bfs();
    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    q.add(new Point(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                    if (map[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        map[nx][ny] = map[p.x][p.y] + 1;
                    }

                }
            }
        }

        int day = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                day = Math.max(day, map[i][j]);
            }
        }

        System.out.println(day - 1);
    }
}
