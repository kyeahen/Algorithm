package Baekjoon;
import java.util.Scanner;

//적록색약 - dfs
public class BJ_10026 {

    static int N;
    static char[][] map; //그림의 구역 정보를 저장한 배열
    static boolean[][] visited; //방문 체크 배열

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        //일반인이 봤을 때의 구역 개수 탐색
        int normalCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j] == false) { //방문하지 않은 구역일 때
                    normalCnt++;
                    dfs(i, j, map[i][j]);
                }
            }
        }

        visited = new boolean[N][N]; //방문 체크 배열 초기화
        changeColor(); //적록색약이 보는 시점으로 색을 바꾼다. (R = G)

        //적록색약이 봤을 때의 구역 개수 탐색
        int colorCnt= 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j] == false) { //방문하지 않은 구역일 때
                    colorCnt++;
                    dfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(normalCnt + " " + colorCnt);
    }

    //dfs - 재귀
    public static void dfs(int x, int y, char color) {

        visited[x][y] = true; //방문 체크

        //해당 구역을 기준으로 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

                //이전 구역과 같은 색이고, 방문하지 않은 구역일 때
                if (map[nx][ny] == color && visited[nx][ny] == false) {
                    visited[nx][ny] = true; //방문 체크
                    dfs(nx, ny, color);
                }
            }
        }
    }

    //적록 색약이 보는 시점으로 그림의 구역을 바꾸는 메소드
    public static void changeColor() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                /* 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못하기 때문에
                빨간색과 초록색을 같은 색으로 바꿔준다.*/
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }
    }
}
