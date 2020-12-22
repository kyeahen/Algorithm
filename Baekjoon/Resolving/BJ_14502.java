package Baekjoon.Resolving;
import java.util.*;

//연구소 - 알고리즘 스터디 (공통)
// - dfs + bfs
public class BJ_14502 {

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    static int N, M, result;
    static int[][] map;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); //세로 크기
        M = s.nextInt(); //가로 크기

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 0일 경우
                if (map[i][j] == 0) {
                    map[i][j] = 1;

                    // wall dfs 시작
                    wall(1);

                    // 백트래킹
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(result);
    }

    //벽 세우기 - dfs
    //0 = 빈칸, 1 = 벽, 2 = 바이러스
    static void wall(int count) {

        // 종료 조건 (벽 3개 다 세웠으면 바이러스 퍼뜨려보기)
        if (count == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //빈칸이면
                if (map[i][j] == 0) {
                    map[i][j] = 1; //벽 세우기

                    // wall dfs 시작
                    wall(count + 1);

                    // 백트래킹
                    map[i][j] = 0;
                }
            }
        }
    }

    //바이러스 퍼뜨리기 - bfs
    public static void spreadVirus() {
        int[][] temp = new int[N][M]; //map 복사

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][j];
            }
        }

        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (temp[i][j] == 2) { //바이러스가 퍼진 곳이면
                    q.add(new Point(i, j));
                }
            }
        }

        //바이러스가 퍼진 곳을 기점으로 바이러스 퍼뜨리기
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                    //빈칸이면
                    if (temp[nx][ny] == 0) {
                        temp[nx][ny] = 2; //바이러스 퍼뜨리기
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }

        //안전영역 구하기
        getSafeArea(temp);
    }

    //안전영역 갯수 구하기
    static void getSafeArea(int[][] temp) {
        int safe = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //안전영역이면 카운팅
                if (temp[i][j] == 0) {
                    safe++;
                }
            }
        }

        //안전영역 개수의 최댓값인지 판별
        if (safe > result){
            result = safe;
        }
    }
}
