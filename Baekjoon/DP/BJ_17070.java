package Baekjoon.DP;

/**
 * Created by kyeahen.
 * Title : 17070 파이프 옮기기1
 * Category : dp, 그래프 탐색
 * Date: 2021/03/30
 * ref - https://steady-coding.tistory.com/30
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070 {

    static int N;
    static int[][] map;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // "가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다."
        dfs(1, 2, 0); //dir = 0(가로), 1(세로), 2(대각선)
        System.out.println(result);
    }

    // x는 세로, y는 가로
    // direction이 0일 때는 파이프가 가로 방향, 1일 때는 세로 방향, 2일 때는 대각선 방향.
    public static void dfs(int x, int y, int dir) {

        if (x == N && y == N) { // 종료 조건
            result++;
            return;
        }

        /*
        - "파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다."
        - "회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다."
        - "파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 세로로 놓여진 경우에는 2가지,
          대각선 방향으로 놓여진 경우에는 3가지가 있다."
         */
        if (dir == 0) { //파이프가 가로 방향 - 오른쪽, 대각선

            // 파이프가 범위 내에 있고, 좌표가 벽이 아닌 경우
            if (y + 1 <= N && map[x][y + 1] != 1) { //오른쪽
                dfs(x, y + 1, 0); //가로
            }

        } else if (dir == 1) { //파이프가 세로 방향 - 아래, 대각선

            // 파이프가 범위 내에 있고, 좌표가 벽이 아닌 경우
            if (x + 1 <= N && map[x + 1][y] != 1) { //아래
                dfs(x + 1, y, 1); //세로
            }

        } else if (dir == 2) { //파이프가 대각선 방향 - 오른쪽, 아래, 대각선

            // 파이프가 범위 내에 있고, 좌표가 벽이 아닌 경우
            if (y + 1 <= N && map[x][y + 1] != 1) { //오른쪽
                dfs(x, y + 1, 0); //가로
            }

            // 파이프가 범위 내에 있고, 좌표가 벽이 아닌 경우
            if (x + 1 <= N && map[x + 1][y] != 1) { //아래
                dfs(x + 1, y, 1); //세로
            }
        }

        // 대각선 (파이프가 범위 내에 있고, 좌표가 벽이 아닌 경우)
        if (y + 1 <= N && x + 1 <= N && map[x][y + 1] != 1 && map[x + 1][y] != 1 && map[x + 1][y + 1] != 1) {
            dfs(x + 1, y + 1, 2);
        }
    }

}

