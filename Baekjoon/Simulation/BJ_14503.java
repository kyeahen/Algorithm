package Baekjoon.Simulation;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//로봇 청소기
public class BJ_14503 {

    static class Robot {
        int r; //북쪽으로부터 떨어진 칸의 개수
        int c; //서쪽으로부터 떨어진 칸의 개수
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int[] dx = { -1, 0, 1, 0 }; //북동남서
    static int[] dy = { 0, 1, 0, -1 };

    static int N, M;
    static int r, c, d;
    static int[][] map;
    static boolean[][] visited;
    static int result = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        r = s.nextInt();
        c = s.nextInt();
        d = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        //bfs();
        dfs(r, c, d);
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(r, c, d));
        visited[r][c] = true;
        result++;

        while (!q.isEmpty()) {
            Robot p = q.poll();
            int x = p.r;
            int y = p.c;
            int dir = p.d;

            int nd = dir;
            boolean flag = false;
            // 왼쪽 방향에 청소하지 않은 구역 탐색
            for (int i = 0; i < 4; i++) {
                nd = turnDirection(nd);
                int nx = dx[nd] + x;
                int ny = dy[nd] + y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        result++;
                        q.add(new Robot(nx, ny, nd));
                        flag = true; //청소O
                        break;
                    }
                }
            }

            //후진
            if (!flag) {
                nd = backDirection(dir);
                int nx = dx[nd] + x;
                int ny = dy[nd] + y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (map[nx][ny] == 0) {
                        q.add(new Robot(nx, ny, dir));
                    }
                }
            }

        }
    }

    public static void dfs(int x, int y, int dir) {

        if (!visited[x][y]) {
            result++;
            visited[x][y] = true;
        }

        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            dir = turnDirection(dir);
            int nx = dx[dir] + x;
            int ny = dy[dir] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (map[nx][ny] == 0 && !visited[nx][ny]) {
                    flag = true;
                    dfs(nx, ny, dir);
                    break;
                }
            }
        }

        if (!flag) {
            int nd = backDirection(dir);
            int nx = dx[nd] + x;
            int ny = dy[nd] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (map[nx][ny] == 0) {
                    dfs(nx, ny, dir);
                }
            }
        }
    }

    //회전
    public static int turnDirection(int d) {
        if (d == 0) {
            return 3;
        } else if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    //후진
    public static int backDirection(int d) {
        if (d == 0) {
            return 2;
        } else if (d == 1) {
            return 3;
        } else if (d == 2) {
            return 0;
        } else {
            return 1;
        }
    }
}
