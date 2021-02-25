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

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); //높이
            int w = Integer.parseInt(st.nextToken()); //너비

            char[][] map = new char[h][w];

            Point[] prisoners = new Point[2]; //죄수

            //. : 빈공간 , * : 벽, # : 문, & : 죄수
            int idx = 0;
            for (int i = 0; i < h; i++) {

                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '$') { //죄수
                        prisoners[idx] = new Point(i, j, 0);
                        idx++;
                    }
                }
            }

            bfs(prisoners[0], map, h, w); //죄수1 탈출
            bfs(prisoners[1], map, h, w); //죄수2 탈출

        }

    }

    public static void bfs(Point p, char[][] map, int h, int w) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];

        q.offer(p);
        visited[p.x][p.y] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if (0 <= nx && nx < h && 0 <= ny && ny < w) {

                    //방문했거나 벽이면 건너뛰기
                    if (visited[nx][ny] || map[nx][ny] == '*') { continue; };

                    if (map[nx][ny] == '#') { //문
                        q.offer(new Point(nx, ny, point.count + 1));
                    } else {
                        q.offer(new Point(nx, ny, point.count));
                    }

                    visited[nx][ny] = true;
                }
            }
        }

    }
}
