package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1261 알고스팟
 * Category : 그래프 이론, 다익스트라, bfs
 * Date: 2021/05/20
 */

public class BJ_1261 {

    static class Point implements Comparable<Point>  {
        int x;
        int y;
        int count;

        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) { //오름차순 정렬
            return Integer.compare(count, o.count);
        }
    }

    static int N, M;
    static int[][] map;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new Point(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == N - 1 && p.y == M - 1) { // (N, M)
                return p.count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (visited[nx][ny]) { continue; }

                    if (map[nx][ny] == 1) { //벽 부수기
                        q.offer(new Point(nx, ny, p.count + 1));
                    } else { //빈 방
                        q.offer(new Point(nx, ny, p.count));
                    }
                    visited[nx][ny] = true;
                }
            }
        }

        return 0;
    }
}
