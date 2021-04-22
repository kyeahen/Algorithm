package Baekjoon.Implementation;

/**
 * Created by kyeahen.
 * Title : 14890 경사로
 * Category : 구현
 * Date: 2021/03/30
 * ref - https://bcp0109.tistory.com/entry/%EB%B0%B1%EC%A4%80-14890%EB%B2%88-%EA%B2%BD%EC%82%AC%EB%A1%9C-Java-Python
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14890 {

    static int[][] map;
    static int N;
    static int L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //NxN
        L = Integer.parseInt(st.nextToken()); //경사로 길이

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0; //지나갈 수 있는 길의 개수
        for (int i = 0; i < N; i++) {
            // "길이란 한 행 또는 한 열 전부를 나타내며, 한쪽 끝에서 다른쪽 끝까지 지나가는 것이다. "

            if (check(i, 0, 0)) { //가로(행) 체크
                count++;
            }

            if (check(0, i, 1)) { //세로(열) 체크
                count++;
            }
        }

        System.out.println(count);
    }

    public static boolean check(int x, int y, int dir) {

        boolean[] visited = new boolean[N];
        int[] height = new int[N]; //한 행 or 한 열의 높이 정보

        for (int i = 0; i < N; i++) {

            if (dir == 0) { //행 검사
                height[i] = map[x][y + i];

            } else { //열 검사
                height[i] = map[x + i][y];
            }
        }

        for (int i = 0; i < N - 1; i++) {

            // 높이가 동일한 경우, 건너뛰기
            // - "낮은 칸과 높은 칸의 높이 차이는 1이어야 한다."
            if (height[i] == height[i + 1]) {
                continue;
            }

            // 인접한 칸과의 높이 차가 1 이상이면 지나갈 수 없음
            // - "낮은 칸과 높은 칸의 높이 차이는 1이어야 한다."
            if (Math.abs(height[i] - height[i + 1]) > 1) {
                return false;
            }

            //현재 칸과 다음 칸의 높이 차가 1인 경우
           if (height[i] - 1 == height[i + 1]) { //현재 칸 높이 > '다음 칸' 높이

               // "경사로를 놓을 '낮은 칸'의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다."

               //다음 칸(i + 1) = 낮은 칸
                for (int j = i + 1; j <= i + L; j++) {

                    /*
                        1. j >= N : "경사로를 놓다가 범위를 벗어나는 경우"
                        2. visited[j] : "경사로를 놓은 곳에 또 경사로를 놓는 경우"
                        3. height[j] != height[i + 1] : "낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우"
                     */
                    if (j >= N || visited[j] || height[j] != height[i + 1]) {
                        return false;
                    }

                    visited[j] = true;
                }
            } else if (height[i] + 1 == height[i + 1]) { //'현재 칸' 높이 < 다음 칸 높이

               //현재 칸(i) = 낮은 칸
                for (int j = i; j > i - L; j--) {

                    /*
                        1. j < 0 : "경사로를 놓다가 범위를 벗어나는 경우"
                        2. visited[j] : "경사로를 놓은 곳에 또 경사로를 놓는 경우"
                        3. height[j] != height[i] : "낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우"
                     */
                    if (j < 0 || visited[j] || height[j] != height[i]) {
                        return false;
                    }

                    visited[j] = true;
                }
            }

        }

        return true;
    }
}