package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//경로 찾기 - bfs
public class BJ_11403 {

    static int N;
    static int[][] map; //인접행렬
    static boolean[] visited; //방문 체크 배열

    static Queue<Integer> q = new LinkedList<>();

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
                visited = new boolean[N]; //방문 체크 초기화

                if (map[i][j] == 1 && visited[j] == false) { //간선이 존재하고, 방문하지 않았을 때
                    bfs(i, j); //해당 정점을 기준으로 bfs 탐색 시작
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

        q.add(y); //해당 정점 큐에 삽입
        visited[y] = true; //방문 체크 -> true

        while (!q.isEmpty()) {
            int a = q.poll();

            for (int i = 0; i < N; i++) {

                if (map[a][i] == 1 && visited[i] == false) { //간선이 존재하고, 방문하지 않았을 때
                    q.add(i); //해당 정점 큐에 삽입
                    visited[i] = true; //방문 체크 -> true
                    map[x][i] = 1; //경로 존재시 1로 변경
                }
            }
        }
    }

}
