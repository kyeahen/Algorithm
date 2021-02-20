package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//토마토 - bfs
public class BJ_7576 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int M, N;
    static int[][] tomatoes;

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        M = s.nextInt(); //가로 칸의 수
        N = s.nextInt(); //세로 칸의 수

        tomatoes = new int[N][M]; //상자에 저장된 토마토들의 정보

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = s.nextInt();
            }
        }

        bfs();
    }

    public static void bfs() {

        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (tomatoes[i][j] == 1) { //익은 토마토 좌표들 큐에 삽입
                    q.add(new Point(i, j));
                }
            }
        }

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x1 = p.x;
            int y1 = p.y;

            for (int i = 0; i < 4; i++) { //익은 토마토 상하좌우 탐색
                int nx = dx[i] + x1;
                int ny = dy[i] + y1;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {

                    if (tomatoes[nx][ny] == 0) { //해당 좌표가 익지 않은 토마토일 때
                        q.add(new Point(nx, ny));
                        tomatoes[nx][ny] = tomatoes[x1][y1] + 1; //일수 증가 (토마토가 익게 되었기 때문)
                    }
                }
            }
        }

        int day = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //토마토가 모두 익지 못한 상황이라면 -1 을 출력
                if (tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                // 전체 토마토 배열을 탐색하면서 최소 일수(최대값)을 찾는다.
                day = Math.max(day, tomatoes[i][j]);
            }
        }

        //시작 일수가 1에서 시작했기 때문에 -1을 해주면 최소 일수가 나온다.
        System.out.println(day - 1);
    }
}
