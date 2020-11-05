package Baekjoon;
import java.util.Scanner;

//테트로미노 - 알고리즘 스터디 (공통)
// DFS + Backtracking
public class BJ_14500 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    //상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int max = 0; //최댓값

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false; //백트래킹
            }
        }

        System.out.print(max);
    }

    public static void dfs(int x, int y, int depth, int sum) {

        if (depth == 4) { //테트로미노 완성 (정사각형 4개)
            max = Math.max(max, sum); //최대값 갱신
            return;
        }

        //상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, depth + 1, sum + map[nx][ny]);

                    /*
                    ㅗ 모양은 depth 2에서 뻗어나와야 가능한 모양이다.
                    그렇기 때문에 depth 2에서 한번 더 다른 점을 탐색한다.
                    */
                    if (depth == 2) { // ㅗ (ㅏ, ㅜ, ㅓ) 모양

                        //상하좌우 탐색
                        for (int j = 0; j < 4; j++) {
                            int mx = dx[j] + x;
                            int my = dy[j] + y;

                            if (0 <= mx && mx < N && 0 <= my && my < M) {

                                if (!visited[mx][my]) {
                                    visited[mx][my] = true;
                                    dfs(mx, my, depth + 2, sum + map[nx][ny] + map[mx][my]);
                                    visited[mx][my] = false;
                                }
                            }
                        }
                    }

                    visited[nx][ny] = false;
                }
            }
        }
    }

}
