package Baekjoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 15684 사다리 조작
 * Category : 완전탐색, 백트래킹
 * Date: 2021/02/25
 * ref - https://leveloper.tistory.com/96
 */
public class BJ_15684 {

    static int N, M, H;
    static int[][] map;

    static int result = 0;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //세로선, y
        M = Integer.parseInt(st.nextToken()); //가로선, x
        H = Integer.parseInt(st.nextToken()); //세로선마다 가로선을 놓을 수 있는 위치의 개수, x

        map = new int[H + 1][N + 1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            //가로선의 정보 : "b번 세로선과 b+1 세로선을 a번 점선 위치에서 연결"
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1; //오른쪽으로
            map[a][b + 1] = 2; //왼쪽으로
        }

       for (int i = 0; i <= 3; i++) { //가능한 가로선 개수 대입 (0 - 3)
           result = i; //가로선 개수의 최솟값
           dfs(1, 0);

           if (flag) { break; }
       }

       if (flag) {
           System.out.println(result);
       } else {
           System.out.println(-1);
       }
    }

    public static void dfs(int a, int count) {
        if (flag) { return; }

        if (result == count) { //대입한 가로선 개수 모두 채웠을 때
            if (check()) { flag = true; }
            return;
        }

        for (int i = a; i <= H; i++) { //세로선마다 가로선 위치 놓기
            for (int j = 1; j < N; j++) { //세로선 (마지막 세로선을 체크 x - 범위 초과)

                // "단, 두 가로선이 연속하거나 서로 접하면 안 된다. 또, 가로선은 점선 위에 있어야 한다."
                if (map[i][j] == 0 && map[i][j + 1] == 0) { //가로선이 연속하지 않을 때
                    map[i][j] = 1;
                    map[i][j + 1] = 2;

                    dfs(i, count + 1);

                    //백트래킹
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    // "i번 세로선의 결과가 i번이 나와야 한다." - 조건 충족 체크
    public static boolean check() {

        for (int i = 1; i <= N; i++) { //세로선

            int y = i; //i번 세로선의 결과 저장
            int x = 1;
            for (int j = 0; j < H; j++) { //가로선

                if (map[x][y] == 1) { //오른쪽으로 이동
                    y++;
                } else if (map[x][y] == 2) { //왼쪽으로 이동
                    y--;
                }

                x++;
            }

            if (y != i) { //i번 세로선의 결과(y)가 i번이 아닌 경우
                return false;
            }
        }

        return true;
    }
}
