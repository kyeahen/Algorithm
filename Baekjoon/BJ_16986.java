package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 16986 인싸들의 가위바위보
 * Category : 구현 (순열, dfs)
 * Date: 2021/02/16
 * ref : https://velog.io/@hyeon930/BOJ-16986-인싸들의-가위바위보-Java
 */
public class BJ_16986 {

    final static int PLAYER_NUM = 3;
    final static int GAME_NUM = 20;

    static int N, K;
    static int[][] map, player;
    static boolean[] visited;
    static int[] win_count, hand_idx;

    static int flag = 0; //경기 결과 (1: 우승, 0: 실패)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //가위바위보의 손동작 수
        K = Integer.parseInt(st.nextToken()); //우승을 위해 필요한 승수

        map = new int[N + 1][N + 1]; //상성에 대한 정보

        /*
            2 : i번이 j번 손동작 이김
            1 : 비긴다
            0 : i번이 j번 손동작 짐
         */
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        player = new int[PLAYER_NUM + 1][GAME_NUM];  //각 플레이어가 20경기에서 낼 손동작 정보
        // - 1 : 지우, 2: 경희, 3: 민호

        visited = new boolean[N + 1]; //손동작 방문 체크 배열

        //경희 손동작
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < GAME_NUM; i++) {
            player[2][i] = Integer.parseInt(st.nextToken());
        }

        //민호 손동작
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < GAME_NUM; i++) {
            player[3][i] = Integer.parseInt(st.nextToken());
        }

        permutation(0);

        System.out.println(flag);
    }

    //지우가 낼 수 있는 n개의 손동작을 순열로 구현
    public static void permutation(int count) {
        if (flag == 1) { return; } //우승

        if (count == N) {
            flag = 0; //초기화
            win_count = new int[PLAYER_NUM + 1]; //각 플레이어의 승수 카운팅
            hand_idx = new int[PLAYER_NUM + 1]; //각 플레이어의 손동작 번호 저장

            if (dfs(1, 2) == 1) { //우승
                flag = 1;
            }

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                player[1][count] = i; //손동작 저장 - 지우

                permutation(count + 1);
                if (flag == 1) { return; } //우승

                //백트래킹
                player[1][count] = 0;
                visited[i] = false;
            }
        }
    }

    //경기 시뮬레이션
    public static int dfs(int p1, int p2) {

        if (win_count[1] >= K) { //지우가 승수 채웠을 때
            return 1;
        }

        if (win_count[2] >= K || win_count[3] >= K) { //경희, 민호가 승수 채웠을 때
            return 0;
        }

        if (hand_idx[1] >= N) { //손동작 번호 초과했을 시
            return 0;
        }

        //해당 경기에 참여하지 않은 플레이어 찾기 (다음 경기 플레이어)
        int next_player = 0;
        for (int i = 1; i <= PLAYER_NUM; i++) {
            if (p1 == i || p2 == i) {
                continue;
            }

            next_player = i;
        }

        int a = player[p1][hand_idx[p1]]; //p1가 해당 경기 순서에 낼 손동작
        int b = player[p2][hand_idx[p2]]; //p2가 해당 경기 순서에 낼 손동작
        int result = map[a][b]; //현재 경기 결과

        //플레이어들의 손동작 번호 증가
        hand_idx[p1]++;
        hand_idx[p2]++;

        if (result == 2) { //p1 승리
            win_count[p1]++;

            // "특정 사람이 미리 합의된 승수를 달성할 때 까지 3을 반복한다." (dfs)
            if (dfs(p1, next_player) == 1) {
                return 1;
            }

        } else if (result == 0) { //p2 승리
            win_count[p2]++;

            if (dfs(p2, next_player) == 1) {
                return 1;
            }

        } else { //무승부

            // "무승부가 발생할 경우 경기 진행 순서상 뒤인 사람이 이긴 것으로 간주한다."
            if (p1 > p2) { //p1 - 뒷번호
                win_count[p1]++;

                if (dfs(p1, next_player) == 1) {
                    return 1;
                }

            } else { //p2 - 뒷번호
                win_count[p2]++;

                if (dfs(p2, next_player) == 1) {
                    return 1;
                }
            }
        }

        return 0;
    }
}
