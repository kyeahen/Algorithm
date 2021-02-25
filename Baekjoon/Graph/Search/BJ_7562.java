package Baekjoon.Graph.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 7562 나이트의 이동
 * Category : BFS
 * Date: 2021/02/24
 * ref - https://ukyonge.tistory.com/87
 */

public class BJ_7562 {

    static class Point {
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int TC;
    static int[][] map;
    static boolean[][] visited;

    //이렇게 좌표할 생각을 하지 못했음
    static int[] dx = {-2, -2, -1, -1, 2, 2, 1, 1};
    static int[] dy = {-1, 1, -2, 2, -1, 1, -2, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            //나이트가 현재 있는 칸
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point from = new Point(x, y, 0);

            st = new StringTokenizer(br.readLine());
            //나이트가 이동하려고 하는 칸
            int move_x = Integer.parseInt(st.nextToken());
            int move_y = Integer.parseInt(st.nextToken());
            Point to = new Point(move_x, move_y, 0);

            bfs(from, to, n);
        }
    }

    public static void bfs(Point from, Point to, int n) {
        Queue<Point> q = new LinkedList<>();
        q.offer(from);
        visited[from.x][from.y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == to.x && p.y == to.y) {
                System.out.println(p.cnt);
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (visited[nx][ny]) { continue; }

                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.cnt + 1));
                }
            }
        }
    }
}
