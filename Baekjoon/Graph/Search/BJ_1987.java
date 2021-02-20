package Baekjoon.Graph.Search;

import java.util.ArrayList;
import java.util.Scanner;

//알파벳 - dfs
public class BJ_1987 {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static ArrayList<Character> alpha = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = s.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        dfs(0, 0, 1);
        System.out.println(result);
    }

    public static void dfs(int x, int y, int count) {
        visited[x][y] = true; //방문 체크
        alpha.add(map[x][y]); //알파벳 삽입

        //해당 알파벳을 기준으로 상하좌우 체크
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {

                //알파벳이 중복되지 않고, 방문하지 않은 칸일 때
                if (check(map[nx][ny]) && !visited[nx][ny]) {
                    dfs(nx, ny, count + 1); //해당 좌표를 기준으로 탐색 및 카운팅
                }
            }
        }

        visited[x][y] = false; //방문 체크 초기화 (백트래킹)
        alpha.remove(alpha.size() - 1); //알파벳 초기화 (백트레킹)
        result = Math.max(count, result); //최대값
    }

    //지나온 칸의 알파벳들과 중복되는지 체크하는 메소드
    public static boolean check(char value) {

        boolean flag = true;
        for (char c : alpha) {

            if (c == value) { //해당 알파벳과 같은 알파벳이 존재하면
                flag = false;
                break;
            }
        }

        return flag;
    }
}
