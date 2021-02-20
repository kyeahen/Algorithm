package Baekjoon.Graph.Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//빙산 - 알고리즘 스터디 (공통)
// - dfs + bfs
public class BJ_2573 {
    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    //바다 = 0, 나머지 = 빙산 높이
    // 한 덩어리의 빙산이 주어질 때, 이 빙산이 "두 덩어리 이상"으로 분리되는 최초의 시간(년)을 구하기
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        int year = 0;
        while (true) {
            visited = new boolean[N][M];

            int count = 0; //빙산 덩어리 변수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    //해당 좌표가 빙산이고, 방문하지 않은 좌표일 때
                    if (map[i][j] != 0 && !visited[i][j]) {
                        dfs(i, j); //dfs 탐색 시작
                        count++; //빙산 덩어리 카운팅
                    }
                }
            }

            if (count == 0) { //빙산이 다 녹은 경우 (두 덩어리 이상으로 분리되지 못한 경우)
                year = 0; //0을 반환
                break;
            }

            if (count >= 2) { //빙산이 두 덩어리 이상으로 분리되는 경우
                break; //탈출하여 최초의 시간을 반환
            }

            melt(); //빙산 녹이기
            year++; //1년 증가
        }

        System.out.println(year);
    }

    //빙산을 탐색하는 메소드 (빙산 덩어리 개수 체크)
    public static void dfs(int x, int y) {
        visited[x][y] = true; //방문 체크

        //해당 좌표를 기준으로 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {

                //해당 좌표가 빙산이고, 방문하지 않은 좌표일 때
                if (map[nx][ny] != 0 && !visited[nx][ny]) {
                    dfs(nx, ny); //dfs 탐색
                }
            }
        }
    }

    //빙산을 녹이는 메소드 (바다에 근접한 칸 수 체크)
    public static void melt() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M]; //빙산이 1년마다 녹기 때문에 초기화된 방문 체크 배열을 따로 사용

        //- 배열에 있는 빙산 좌표를 모두 큐에 삽입한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //해당 좌표가 빙산이고, 방문하지 않은 좌표일 때
                if (map[i][j] != 0 && !visited[i][j]) {
                    q.add(new Point(i, j)); //해당 좌표 큐에 삽입
                    visited[i][j] = true; //방문 체크
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            //해당 좌표를 기준으로 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                int seaCnt = 0; //해당 빙산에 붙어있는 바닷물 칸의 수
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {

                    //해당 좌표가 바닷물이고, 방문하지 않은 좌표일 때
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        seaCnt++; //카운팅
                    }
                }

                //녹은 빙산의 높이가 0 미만일 때는 높이를 0으로 해준다.
                if (map[p.x][p.y] - seaCnt < 0) {
                    map[p.x][p.y] = 0;
                } else {
                    map[p.x][p.y] -= seaCnt; //붙어있는 바닷물 수 만큼 빼준다.
                }
            }
        }
    }
}
