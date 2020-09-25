package Baekjoon.Resolving;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

//보물섬 - 20.09.25
public class BJ_2589 {

    static int N, M;
    static char[][] map;
    static int[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int result = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 'L') {
                    visited = new int[N][M]; //각 육지 좌표를 기준으로 시간을 측정하기 위함 (최단거리)
                    bfs(i, j);
                }
            }
        }

        System.out.println(result - 1); //시작값을 1로 시작했기 때문에 -1
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = 1;

        int max = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                    if (map[nx][ny] == 'L' && visited[nx][ny] == 0) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = visited[p.x][p.y] + 1;
                        max = Math.max(max, visited[nx][ny]); //가장 긴 시간 저장
                    }
                }
            }
        }

        result = Math.max(max, result); //최종적으로 가장 긴 시간을 저장 (최단거리로 이동하는 시간)
    }
}
