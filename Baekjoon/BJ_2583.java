package Baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//영역 구하기 - dfs
public class BJ_2583 {

    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int count = 0;
    static int area = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(br.readLine());

        M = Integer.parseInt(s.nextToken());
        N = Integer.parseInt(s.nextToken());
        K = Integer.parseInt(s.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            s = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(s.nextToken());
            int y1 = Integer.parseInt(s.nextToken());

            int x2 = Integer.parseInt(s.nextToken());
            int y2 = Integer.parseInt(s.nextToken());

            fillSquare(x1, y1, x2, y2);
        }

        ArrayList<Integer> areaArr = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 0 && !visited[i][j]) { //직사각형이 아니고, 방문하지 않은 좌표
                    count++; //영역 개수 카운팅
                    dfs(i,j);
                    areaArr.add(area); //탐색을 마친 영역 넓이 추가
                    area = 0; //다음 영역 카운팅일 위해 초기화
                }
            }
        }

        Collections.sort(areaArr); //오름차순 정렬
        System.out.println(count);

        for (int i : areaArr) {
            System.out.print(i + " ");
        }
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true; //방문 체크
        area++; //넓이 카운팅

        //해당 좌표를 기준으로 상하좌우 체크
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (map[nx][ny] == 0 && !visited[nx][ny]) { //직사각형이 아니고, 방문하지 않은 좌표
                    dfs(nx, ny);
                }
            }
        }
    }

    //직사각형 영역을 채우는 메소드
    public static void fillSquare(int x1, int y1, int x2, int y2) {

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                map[j][i] = 1;
            }
        }
    }

}
