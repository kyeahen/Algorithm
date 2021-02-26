package Baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 1913 달팽이
 * Category : 구현
 * Date: 2021/02/26
 * ref : [16926 배열 돌리기1] 참고
 */
public class BJ_1913 {

    static int N, find_num;
    static int[][] map;

    //우 아래 좌 위 - 반시계 방향
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int num;
    static int[] point = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        find_num = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int depth = N / 2;
        num = 1;
        simulation(depth);
    }

    public static void simulation(int depth) {

        int index = depth;
        for (int i = 1; i <= depth; i++) { //사각형 개수 만큼

            //시작 좌표 (2,2) -> (1,1)...
            int x = index;
            int y = index;
            map[x][y] = num;

            //시작 좌표 위로 한칸 올라가기
            x--;
            num++;
            map[x][y] = num;

            if (map[x][y] == find_num) {
                point[0] = x + 1;
                point[1] = y + 1;
            }

            int k = 0;
            while (k < 4) { //반시계 방향 탐색
                int nx = dx[k] + x;
                int ny = dy[k] + y;

                //배열의 유효 범위이고, 현재 depth에서 체크해야하는 범위일 때
                if (isCheck(depth, i, nx, ny) && isRange(nx, ny)) {

                    map[nx][ny] = map[x][y] + 1; // +1 한 후, find_num 체크해야 함
                    x = nx;
                    y = ny;

                    num = map[nx][ny];

                    if (map[nx][ny] == find_num) { //해당 좌표에 찾는 숫자가 있을 때
                        point[0] = nx + 1;
                        point[1] = ny + 1;
                        //좌표가 0부터 시작하기 때문에 +1을 해줘야 답이 나옴
                    }

                } else { //회전 방향이 바뀌어야할 때 (범위 초과)
                    k += 1; // 다음 반시계 방향으로 넘어가기
                }
            }

            index--;
        }

        print();
        System.out.println(point[0] + " " + point[1]);
    }

    //현재 depth의 유효 범위 확인
    public static boolean isCheck(int depth, int len, int nx, int ny) {
        if (depth - len <= nx && nx <= depth + len && depth - len <= ny && ny <= depth + len) {
            return true;
        }

        return false;
    }

    //배열 유효 범위 확인
    public static boolean isRange(int nx, int ny) {
        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
            return true;
        }

        return false;
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //System.out.printf("%3d", map[i][j]);
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

}
