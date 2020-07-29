package Baekjoon;
import java.util.Arrays;
import java.util.Scanner;

//단지 번호 붙이기 - dfs
public class BJ_2667 {

    static int N; //지도의 크기
    static int[][] array; //지도 내용을 담은 2차원 배열
    static boolean[][] visted; //탐색할 집의 방문 여부 체크하는 배열
    static int count = 1; //방문한 단지 번호

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        array = new int[N][N];
        visted = new boolean[N][N];

        //지도 내용 입력
        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < N; j++) {
                array[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (array[i][j] == 1 && visted[i][j] == false) { //1이면서 방문하지 않은 곳일 때
                    dfs(i, j); //dfs 탐색
                    count++; //탐색이 끝나고, 다음 단지 번호 카운팅 (+1)
                }
            }
        }

        int[] result = new int[count]; //각 단지 내 집의 수

        for(int i = 0; i < N; i++){
           for (int j = 0; j < N; j++) {

               if (array[i][j] != 0) { //집이 있는 곳인 경우
                   result[array[i][j]]++; //집이 있는 경우, count로 저장되었으므로, 단지 번호가 저장된다.
               }
           }
        }

        Arrays.sort(result); //각 단지 내 집의 수 오름차순 정렬
        System.out.println(count - 1); //단지 수 출력 (1부터 단지 번호가 시작이기에 -1을 해줘야 단지 수가 나옴)

        for (int i = 1; i < count; i++) { //각 단지 내 집의 수 출력
            System.out.println(result[i]);
        }
    }

    public static void dfs(int x, int y) {
        array[x][y] = count; //방문한 집 -> count 값 넣기 (1, 2, 3..)
        visted[x][y] = true; //방문한 집 -> true 값

        for (int i = 0; i < 4; i++) { //상하좌우 체크
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // nx, ny = 좌표의 범위, N*N 크기이므로 x, y좌표가 0보단 커야하고 N보단 작아야함.
            if (ny >= 0 && ny < N && nx >= 0 && nx < N) {

                if (array[nx][ny] == 1 && visted[nx][ny] == false) { //1이면서 방문하지 않은 곳
                    dfs(nx, ny); //dfs 탐색
                }
            }
        }
    }

}
