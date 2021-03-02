package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 9376 탈옥
 * Category : bfs, 다익스트라
 * Date: 2021/02/24
 * ref -
 */
public class BJ_9376 {

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int count; //문을 연 수

        Point (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) { //오름차순
            return Integer.compare(this.count, o.count);
        }
    }

    static int TC;

    static int h, w;
    static char[][] map;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()) + 2; //높이
            w = Integer.parseInt(st.nextToken()) + 2; //너비

            map = new char[h][w];

            Point sangeun = new Point(0, 0, 0);
            Point[] prisoners = new Point[2]; //죄수

            //. : 빈공간 , * : 벽, # : 문, & : 죄수
            int idx = 0;
            for (int i = 1; i < h - 1; i++) {

                String str = "." + br.readLine() + ".";
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '$') { //죄수
                        prisoners[idx] = new Point(i, j, 0);
                        idx++;
                    }
                }
            }

            int[][] dist1 = bfs(sangeun);
            int[][] dist2 = bfs(prisoners[0]); //죄수1 탈출
            int[][] dist3 = bfs(prisoners[1]); //죄수2 탈출

            int ans = h * w;
            int temp = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*') {
                        continue;
                    }

                    temp = dist1[i][j] + dist2[i][j] + dist3[i][j];

                    if (map[i][j] == '#') {
                        temp -= 2;
                    }

                    if (ans > temp) {
                        ans = temp;
                    }
                }
            }

            System.out.println(ans);

        }

    }

    public static int[][] bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.offer(p);

        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[p.x][p.y] = 0;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if (0 <= nx && nx < h && 0 <= ny && ny < w) {

                    //벽이면 건너뛰기
                    if (map[nx][ny] == '*') { continue; };

                    if (map[nx][ny] != '#') { //문
                        if (dist[nx][ny] == Integer.MAX_VALUE || dist[nx][ny] > dist[p.x][p.y]) {
                            dist[nx][ny] = dist[p.x][p.y];
                            q.offer(new Point(nx, ny, point.count + 1));
                        }
                    } else {
                        if (dist[nx][ny] == Integer.MAX_VALUE || dist[nx][ny] > dist[p.x][p.y] + 1) {
                            dist[nx][ny] = dist[p.x][p.y] + 1;
                            q.offer(new Point(nx, ny, point.count));
                        }
                    }
                }
            }
        }

        return dist;
    }
}
