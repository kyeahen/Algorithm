package Baekjoon;
import java.util.Scanner;

//2048(Easy) - 알고리즘 스터디(공통)
// - dfs + 시뮬레이션
// https://developer-pi.tistory.com/26
// https://indesire.tistory.com/113

public class BJ_12100 {

    static int N;
    static int[][] map;
    static int[][] temp;
    static boolean[][] visited;
    static int[] way;

    //상하좌우
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int max = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new int[N][N];

        way = new int[5]; //회차에 따른 탐색 방향을 저장하는 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        dfs(0, 5);

        System.out.println(max);
    }

    public static void dfs(int count, int end) { //(횟수 카운팅, 최대 횟수)

        if (count == end) { //5회 완료
            move();
            //횟수에 따라 어느 방향으로 움직이는지가 way 배열에 저장되어있기 때문에
            // 5회차가 되면 해당 정보를 가지고 블럭 탐색
            return;
        }

        for (int i = 0; i < 4; i++) { //상하좌우 탐색
            way[count] = i; //횟수에 따른 방향 전달
            dfs(count + 1, end);
        }
    }

    //블럭 탐색
    public static void move() {

        //배열 복사
        temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int k = 0; k < way.length; k++) { //최대 5회만큼 반복
            visited = new boolean[N][N];

            //변수로 전달된 방향으로 탐색 후, 합치기
            if (way[k] == 0) { //위로 ( +1)

                for (int i = N - 1; i >= 0; i--) { //아래 -> 위
                    for (int j = 0; j < N; j++) {
                        merge(i, j, way[k]);
                    }
                }
            } else if (way[k] == 1) { //아래로 ( -1)

                for (int i = 0; i < N; i++) { //위 -> 아래
                    for(int j = 0; j < N; j++) {
                        merge(i, j, way[k]);
                    }
                }
            } else if (way[k] == 2) { //왼쪽으로 ( +1)

                for (int j = N - 1; j >= 0; j--) { //오른쪽 -> 좌측
                    for (int i = 0; i < N; i++) {
                        merge(i, j, way[k]);
                    }
                }
            } else if (way[k] == 3) { //오른쪽으로 ( -1)

                for (int j = 0; j < N; j++) { //왼쪽 -> 오른쪽
                    for (int i = 0; i < N; i++) {
                        merge(i, j, way[k]);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (temp[i][j] > max) { //최대값 갱신 (가장 큰 블록)
                    max = temp[i][j];
                }
            }
        }
    }

    //블럭 합치기
    // ? 왜 y, x가 거꾸로 들어가는 것인가
    public static void merge(int y, int x, int k) {

        if (temp[y][x] == 0) { //0이면 공백 - 리턴
            return;
        }

        while (true) {
            //k에는 방향이 저장되어있음
            int nx = dx[k] + x;
            int ny = dy[k] + y;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) { return; } //범위 초과 - 리턴
                if (visited[ny][nx]) { return; } //이미 방문한 블럭 - 리턴

                if (temp[ny][nx] == temp[y][x]) { //같을 때 - 블럭 합치기
                    visited[ny][nx] = true; //방문 체크
                    temp[ny][nx] *= 2;
                    temp[y][x] = 0; //기존 블럭 공백 처리
                    return;

                } else if (temp[ny][nx] != 0) { //다음 블럭이 공백이 아니지만 블럭이 같진 않을 경우
                    return;
                }

                // - 주변에 합칠 블럭이 없는 경우 (이동)
                temp[ny][nx] = temp[y][x]; //기존 블럭을 다음 블럭으로 옮겨담기
                temp[y][x] = 0; //기존 블럭 공백 처리
                //좌표 교체
                x = nx;
                y = ny;

        }
    }

}
