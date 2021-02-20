package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//안전 영역 - bfs, dfs
public class BJ_2468 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] map; //지역의 높이 정보 배열
    static boolean[][] visited; //방문 체크 배열

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static int max = 0; //안전 영역의 최대 개수

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        int high = 0; //최고 층수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                high = Math.max(high, map[i][j]);
            }
        }

        /* 모든 영역의 높이(층)를 기준으로 물이 잠기는 높이를 설정하고
        안전 영역을 탐색하여 안전 영역 최대 개수를 구한다. */
        for (int k = 0; k < high; k++) {

            visited = new boolean[N][N]; //기준 높이(층)마다 방문 체크 배열 초기화
            int count = 0; //기준 높이(층)마다 안전영역 카운트

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (map[i][j] > k && visited[i][j] == false) { //기준 높이(층)보다 높고, 방문하지 않은 영역일 때
                        //dfs(i,j,k);
                        bfs(i, j, k);
                        count++;
                    }
                }
            }
            max = Math.max(count, max); //안전 영역 최대 개수 구하기
        }
        System.out.println(max);
    }

    //dfs - 재귀
    public static void dfs(int x, int y, int floor) {
        visited[x][y] = true; //방문 체크

        //해당 영역을 기준으로 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

                if (map[nx][ny] > floor && visited[nx][ny] == false) { //기준 높이(층)보다 높고, 방문하지 않은 영역일 때
                    visited[nx][ny] = true; //방문 체크
                    dfs(nx, ny, floor);
                }
            }
        }
    }

    //bfs - 큐(Queue)
    public static void bfs(int x, int y, int floor) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y)); //해당 영역 좌표 큐에 삽입
        visited[x][y] = true; //방문 체크

        while (!q.isEmpty()) {
            Point p = q.poll();

            //해당 영역을 기준으로 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

                    if (map[nx][ny] > floor && visited[nx][ny] == false) { //기준 높이(층)보다 높고, 방문하지 않은 영역일 때
                        q.add(new Point(nx, ny)); //해당 영역 좌표 큐에 삽입
                        visited[nx][ny] = true; //방문 체크
                    }
                }
            }
        }
    }
}
