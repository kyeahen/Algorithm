package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미로 탐색 - bfs
public class BJ_2178 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map; //미로 정보를 저장한 배열
    static boolean[][] visited; //방문 체크를 위한 배열

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        //값을 붙어서 입력 받기 때문에 String으로 받은 후, 쪼갠다.
        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0); //시작 좌표
        System.out.println(map[N - 1][M - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y)); //이동 가능한 (1) 큐에 삽입
        visited[x][y] = true; //방문한 칸 -> true

        while (!q.isEmpty()) {
            Point p = q.poll();

            //해당 칸을 기준으로 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    //이동 가능한 칸(1)이고, 아직 방문하지 않은 칸일 때
                    if (map[nx][ny] == 1 && visited[nx][ny] == false) {
                        q.add(new Point(nx, ny)); //다음 칸 큐에 삽입
                        map[nx][ny] = map[p.x][p.y] + 1; //다음 칸은 현재 칸보다 1칸 더 가야함 (+1 증가)
                        visited[nx][ny] = true; //방문한 칸 -> true
                    }
                }
            }
        }
    }
}
