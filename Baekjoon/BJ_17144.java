package Baekjoon;
import java.util.Scanner;

// 미세먼지 안녕! - dfs
public class BJ_17144 {

    static int R, C, T;
    static int[][] map;

    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Point[] cleaner; //공기청정기 좌표

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();
        T = s.nextInt();

        map = new int[R][C];
        cleaner = new Point[2];

        int cleanerCnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = s.nextInt();

                if (map[i][j] == -1) { //해당 좌표가 공기청정기일 때
                    cleaner[cleanerCnt++] = new Point(i, j);
                }
            }
        }

        spreadDust(0);
        System.out.print(getSum());
    }

    // 미세먼지 확산
    public static void spreadDust(int t) {
        int[][] temp = new int[R][C];
        int count = 0;

        if (t == T) { //종료 조건
            return;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dust = map[i][j] / 5; //확산되는 먼지의 양

                if (map[i][j] != 0 && map[i][j] != -1) { //해당 좌표가 미세먼지

                    //해당 좌표를 기준으로 상하좌우 탐색 (미세먼지는 4방향으로 확산)
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {

                            if (map[nx][ny] != -1) { //해당 좌표가 공기청정기가 아니면
                                temp[nx][ny] += dust; //임시 배열 좌표에 먼지 양 추가
                                count++; //확산된 방향의 개수 카운팅
                            }
                        }
                    }

                    map[i][j] -= dust * count; //해당 좌표에 확산된 후, 남은 먼지 양 계산
                    count = 0;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += temp[i][j]; //원 배열에 확산된 총 먼지 양을 더해준다.
            }
        }

        activateAirCleaner(); //공기청정기 가동
        spreadDust(t + 1);
    }

    public static void activateAirCleaner() {

        // - 위쪽 공기청정기 (반시계 방향)
        //아래
        for (int i = cleaner[0].x - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        //왼쪽
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        //위
        for (int i = 0; i < cleaner[0].x; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        //오른쪽
        for (int i = C - 1; i > 1; i--) {
            map[cleaner[0].x][i] = map[cleaner[0].x][i - 1];
        }
        map[cleaner[0].x][1] = 0;

        // - 아래쪽 공기청정기 (시계 방향)
        //위
        for(int i = cleaner[1].x + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        //왼쪽
        for(int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        //아래
        for(int i = R - 1; i > cleaner[1].x; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        //오른쪽
        for(int i = C - 1; i > 1; i--) {
            map[cleaner[1].x][i] = map[cleaner[1].x][i - 1];
        }
        map[cleaner[1].x][1] = 0;
    }

    public static int getSum() {

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }
}
