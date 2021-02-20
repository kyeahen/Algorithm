package Baekjoon.Simulation;
import java.util.Scanner;

//배열돌리기1 - 알고리즘 스터디 (공통)
public class BJ_16926 {

    static int N, M, R;
    static int[][] map;

    //반시계 방향 회전
    //right <-> left, top <-> bottom
    //북서남동 <-> 남동북서
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();
        R = s.nextInt();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        int count = Math.min(N, M) / 2; //총 사각형의 개수

        for (int i = 0; i < R; i++) {
            rotate(count);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void rotate(int count) {

        for (int i = 0; i < count; i++) { //사각형 개수 만큼

            int x = i;
            int y = i;
            int start = map[x][y];

            int k = 0;
            while (k < 4) { //반시계 방향 탐색
                int nx = dx[k] + x;
                int ny = dy[k] + y;

                System.out.println(nx + "," + ny);
                if (i <= nx && nx < N - i && i <= ny && ny < M - i) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;

                } else { //회전 방향이 바뀌어야할 때
                    k += 1; // 다음 반시계 방향으로 넘어가기
                }
            }

            map[i + 1][i] = start; //시작 좌표
        }
    }
}
