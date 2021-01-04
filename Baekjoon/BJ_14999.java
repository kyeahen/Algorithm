package Baekjoon;
import java.util.Scanner;

//주사위 굴리기 - 알고리즘 스터디(공통)
// - 시뮬레이션
public class BJ_14999 {

    static int[][] map;
    static int[] dice;

    // "동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4"
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();
        int M = s.nextInt();
        int x = s.nextInt();
        int y = s.nextInt();
        int K = s.nextInt();

        map = new int[N][M]; //지도
        dice = new int[7]; //주사위

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < K; i++) {
            int dir = s.nextInt(); //이동해야하는 방향

            //방향에 따른 좌표 설정
            int nx = dx[dir - 1] + x;
            int ny = dy[dir - 1] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                change(dir); //주사위 굴리기

                if (map[nx][ny] == 0) { //이동한 칸이 0인 경우
                    map[nx][ny] = dice[6]; //주사위 '바닥면'에 쓰여있는 수가 칸에 복사된다.

                } else { //이동한 칸이 0이 아닌 경우
                    dice[6] = map[nx][ny]; //칸에 쓰여있는 수가 주사위의 '바닥면'에 복사된다.
                    map[nx][ny] = 0; //칸에 쓰여있는 수는 0이 된다.
                }

                //좌표 이동
                x = nx;
                y = ny;

                //주사위 이동했을 때마다 상단에 쓰여있는 값(윗면) 출력
                System.out.println(dice[1]);
            }
        }
    }

    //주사위 굴리는 메소드
    public static void change(int dir) {
        int[] temp = new int[7];
        for (int i = 0; i < 7; i++) {
            temp[i] = dice[i];
        }

        //윗면 = 1, 바닥면 = 6
        //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
        if (dir == 1) { //동
            dice[4] = temp[6];
            dice[1] = temp[4];
            dice[3] = temp[1];
            dice[6] = temp[3];
        } else if (dir == 2) { //서
            dice[4] = temp[1];
            dice[1] = temp[3];
            dice[3] = temp[6];
            dice[6] = temp[4];
        } else if (dir == 3) { //북
            dice[2] = temp[1];
            dice[1] = temp[5];
            dice[5] = temp[6];
            dice[6] = temp[2];
        } else { //남
            dice[2] = temp[6];
            dice[1] = temp[2];
            dice[5] = temp[1];
            dice[6] = temp[5];
        }
    }
}
