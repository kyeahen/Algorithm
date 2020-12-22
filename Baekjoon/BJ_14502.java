package Baekjoon;
import java.util.*;

//연구소 - dfs
public class BJ_14502 {

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    static int N, M, result;
    static int[][] map, temp;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); //세로 크기
        M = s.nextInt(); //가로 크기

        map = new int[N][M];
        temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        // 벽세우기
        for (int i = 0; i < N * M; i++) {
            int ny = (int) i / M;
            int nx = i % M;

            // 0일 경우
            if (map[ny][nx] == 0) {
                map[ny][nx] = 1;

                // wall dfs 시작
                wall(i, 1);

                // 백트래킹
                map[ny][nx] = 0;
            }
        }
        System.out.println(result);
    }

    //0 = 빈칸, 1 = 벽, 2 = 바이러스
    static void wall(int v, int count) {

        // 종료 조건
        if (count == 3) {
            temp = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            // 바이러스 퍼트리기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (temp[i][j] == 2) {
                        spreadVirus(i, j);
                    }
                }
            }

            // 안전영역 갯수세기
            getSafeArea();
        } else {

            for (int i = v + 1; i < N * M; i++) {
                int ny = (int) i / M;
                int nx = i % M;

                if (map[ny][nx] == 0) {
                    map[ny][nx] = 1;
                    wall(i, count + 1);
                    map[ny][nx] = 0;
                }
            }
        }
    }

    //바이러스 퍼뜨리기
    public static void spreadVirus(int y, int x) {

        // 상하좌우
        for (int a = 0; a < 4; a++) {
            int ny = dy[a] + y;
            int nx = dx[a] + x;

            // 상하좌우가 0이면 바이러스 감염
            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {

                if (temp[ny][nx] == 0) {
                    temp[ny][nx] = 3;
                    spreadVirus(ny, nx);
                }
            }
        }
    }

    //안전영역 갯수 구하기
    static void getSafeArea() {
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
