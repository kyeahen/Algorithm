package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//양 - bfs
public class BJ_3184 {

    static int R, C;
    static int[][] map;
    static boolean[][] visited;

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static int wolfResult = 0;
    static int sheepResult = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = s.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] != '#' && !visited[i][j]) { //해당 좌표가 벽이 아니고, 아직 방문하지 않았을 때
                    bfs(i, j); //bfs 탐색
                }
            }
        }

        System.out.println(sheepResult + " " + wolfResult);
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y)); //큐에 해당 좌표 삽입

        int wolfCnt = 0, sheepCnt = 0;
        if (map[x][y] == 'o') { //해당 좌표가 양이면
            sheepCnt++;
        }

        if (map[x][y] == 'v') { //해당 좌표가 늑대면
            wolfCnt++;
        }
        visited[x][y] = true; //방문 체크

        while (!q.isEmpty()) {
            Point p = q.poll();

            //해당 좌표를 기준으로 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {

                    if (map[nx][ny] != '#' && !visited[nx][ny]) { //해당 좌표가 벽이 아니고, 아직 방문하지 않았을 때

                        if (map[nx][ny] == 'o') { //해당 좌표가 양이면
                            sheepCnt++;
                        }

                        if (map[nx][ny] == 'v') { //해당 좌표가 늑대면
                            wolfCnt++;
                        }

                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true; //방문 체크
                    }
                }
            }
        }

        //영역 안의 양의 수가 늑대의 수보다 많다면 양이 늑대를 이긴다.
        if (wolfCnt < sheepCnt) {
            sheepResult += sheepCnt;
        } else { //그렇지 않다면 늑대가 그 지역 안의 모든 양을 먹는다.
            wolfResult += wolfCnt;
        }
    }
}
