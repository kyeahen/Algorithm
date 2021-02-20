package Baekjoon.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 15683 감시
 * Category : 시뮬레이션
 * Date: 2021/02/08
 */

public class BJ_15683 {

    static class CCTV {
        int x;
        int y;
        int num; //cctv 번호

        CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int N, M;

    //상하좌우
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    //CCTV 번호별 체크 영역 - 경우의 수
    static int[][][] dir = {
            {{0}},
            {{0}, {1}, {2}, {3}}, // 1번
            {{0, 1}, {2, 3}}, // 2번
            {{1, 2}, {0, 2}, {1, 3}, {0, 3}}, // 3번
            {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}}, // 4번
            {{0, 1, 2, 3}} // 5번

    };

    static ArrayList<CCTV> cctv_list = new ArrayList<>(); //CCTV 저장 리스트
    static int result = Integer.MAX_VALUE; //사각지대의 최소 크기

    //사각지대의 최소 크기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= map[i][j] && map[i][j] <= 5) { // CCTV 좌표를 리스트에 저장 (1 ~ 5번)
                    cctv_list.add(new CCTV(i, j, map[i][j]));
                }

            }
        }

        slove(0, map);
        System.out.println(result);
    }

    // 재귀
    public static void slove(int num, int[][] map) {

        if (num == cctv_list.size()) { //모든 CCTV를 다 체크했으면 (탈출 조건)
            result = Math.min(check(map), result); //최솟값 저장
            return;
        }

        CCTV cctv = cctv_list.get(num); //현재 체크할 CCTV 객체

        for (int i = 0; i < dir[cctv.num].length; i++) { //cctv 번호별 체크할 방향 배열(1 ~ 5번)

            int[][] copy_map = copy(map); //map 복사해서 사용
            for (int j = 0; j < dir[cctv.num][i].length; j++) {

                int nx = cctv.x;
                int ny = cctv.y;
                int nd = dir[cctv.num][i][j]; //감시 방향

                while (isRange(nx + dx[nd], ny + dy[nd])) { //배열 범위 끝까지 체크
                    nx += dx[nd];
                    ny += dy[nd];

                    // "CCTV는 벽을 통과할 수 없다"
                    if (copy_map[nx][ny] == 6) { break; } //벽이면 루프 탈출

                    copy_map[nx][ny] = 7; //체크한 영역은 7로 바꾸기
                }
            }

            slove(num + 1, copy_map); //재귀 - CCTV 인덱스 증가 (다음 cctv)
        }

    }

    //사각지대 체크
    public static int check(int[][] arr) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (arr[i][j] == 0) { //빈칸
                    count++;
                }
            }
        }

        return count;
    }

    //배열 복사
    public static int[][] copy(int[][] map) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    //배열 범위 체크
    public static boolean isRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) {
            return true;
        }

        return false;
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
