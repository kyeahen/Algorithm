package Baekjoon.Resolving;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//경로찾기 - 20.09.26
public class BJ_11403 {

    static int N;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited = new boolean[N];
                if (map[i][j] == 1) {
                    bfs(i, j); //i -> j 좌표로 가는 경로 여부 찾기
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        q.add(y); //j 좌표 큐에 삽입
        visited[y] = true; //j 좌표 방문 체크

        while (!q.isEmpty()) {
            int j = q.poll();

            //모든 정점을 탐색하면서 j 좌표와 연결된 간선 찾기
            for (int n = 0; n < N; n++) {

                if (map[j][n] == 1 && !visited[n]) {
                    q.add(n); //해당 정점 좌표 큐에 삽입
                    visited[n] = true;
                    map[x][n] = 1; //해당 정점 좌표가 j 좌표와 연결되어 있다면 1로 변경
                }

            }
        }
    }
}
