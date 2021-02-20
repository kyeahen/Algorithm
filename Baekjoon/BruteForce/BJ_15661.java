package Baekjoon.BruteForce;

import java.util.Scanner;

//링크와 스타트 - 알고리즘 스터디(공통)
// - 완전탐색

/*
1, 2번이 스타트 팀, 3, 4번이 링크 팀

- 스타트 팀: S12 + S21 = 1 + 4 = 5
- 링크 팀: S34 + S43 = 2 + 5 = 7
 */
public class BJ_15661 {

    static int N;
    static int[][] map; //능력치 표
    static boolean[] visited; //방문 체크 배열 (총 인원)

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();

        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    //모든 팀의 조합 구하기
    public static void dfs(int index, int count) { //인덱스, 조합 개수

        if (count == N / 2) { //팀 조합이 완성된 경우 (각 팀 인원 = N/2)
            min = Math.min(min, calculate());
            return;
        }

        for (int i = index; i < N; i++) {

            if (!visited[i]) { //방문하지 않았을 때
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false; //백트래킹
            }
        }
    }

    //두 팀의 능력치를 계산하는 메소드
    public static int calculate() {
        int start = 0; //스타트 팀의 능력치
        int link = 0; //링크 팀의 능력치

        //i == j인 경우는 0이기에 계산하지 않음
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {

                //i번째 사람과 j번째 사람이 true이면 스타트 팀 능력치 ++
                if (visited[i] && visited[j]) {
                    start += map[i][j] + map[j][i];

                    //i번째 사람과 j번째 사람이 false이면 링크 팀 능력치 ++
                } else if (!visited[i] && !visited[j]) {
                    link += map[i][j] + map[j][i];
                }
            }
        }

        return Math.abs(start - link); //두 팀의 능력치 차이
    }
}
