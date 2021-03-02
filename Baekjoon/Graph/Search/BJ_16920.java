package Baekjoon.Graph.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 16920 확장게임
 * Category : bfs
 * Date: 2021-03-02
 */
public class BJ_16920 {

    static class Player {
        int index; //플레이어 index
        int cnt; //플레이어가 소유한 성의 개수
        Queue<Point> points; //플레이어가 소유한 성의 좌표 (라운드별 갱신)

        Player(int index, int cnt, Queue<Point> points) {
            this.index = index;
            this.cnt = cnt;
            this.points = points;
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, P;

    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static Player[] players; //플레이어 배열
    static int[] move_arr; //플레이어별 이동 가능한 칸 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); //플레이어 수

        //플레이어 i는 자신의 성의 있는 곳에서 S칸 만큼 이동할 수 있는 "모든 칸"에 성을 동시에 만든다.
        move_arr = new int[P + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            move_arr[i] = Integer.parseInt(st.nextToken());
        }

        players = new Player[P + 1];
        for (int i = 1; i <= P; i++) {
            players[i] = new Player(i, 0, new LinkedList<>());
        }

        map = new char[N][M];
        visited = new boolean[N][M];

        //. = 빈칸, # = 벽
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] != '.' && map[i][j] != '#') { //플레이어일 때
                    int idx = map[i][j] - '0'; //플레이어 index

                    Queue<Point> q = players[idx].points;
                    q.offer(new Point(i, j)); //성 좌표 추가
                    visited[i][j] = true;

                    players[idx] = new Player(idx, players[idx].cnt + 1, q); //성의 개수 및 좌표 갱신
                }
            }
        }

        while (true) {

            if (isFinish()) { break; } //게임 종료

            for (int i = 1; i < players.length; i++) {
                bfs(players[i]);
            }
        }

        //정답 출력
        for (int i = 1; i < players.length; i++) {
            System.out.print(players[i].cnt + " ");
        }

        //System.out.println();
    }

    //모든 플레이어가 더이상 확장할 수 없는 상태인지 확인 (게임 종료)
    public static boolean isFinish() {

        for (int i = 1; i < players.length; i++) {

            //비어있지 않은 큐가 있으면 (아직 확장 가능한 상태)
            if (!players[i].points.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    //각 플레이어의 성이 움직이는 bfs
    // - 성의 개수랑 현재 성의 좌표들을 리스트에 저장
    public static void bfs(Player player) {
        Queue<Point> q = player.points; //플레이어가 소유한 성의 좌표를 담은 큐 (라운드마다 갱신)
        int index = player.index; //플레이어 index

        int cnt = player.cnt; //플레어어가 소유한 성의 수
        int dist = 1; //플레이어가 이동할 수 있는 칸의 수
        while (!q.isEmpty()) {

            int size= q.size();
            for (int j = 0; j < size; j++) {
                Point p = q.poll();

                for (int i = 0; i < 4; i++){

                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (!isRange(nx, ny) || visited[nx][ny]) { continue; }
                    if (map[nx][ny] != '.') { continue; }; //벽, 숫자

                    q.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;

                    //map[nx][ny] = Integer.toString(player.index).charAt(0);
                }
            }

            dist++; //S칸만큼 이동
            if (dist > move_arr[index]) { break; }
        }

        //player.points = nextQ; //다음 좌표 큐로 변경
        player.cnt = cnt; //성의 개수 갱신

        ///print();
        //System.out.println();
    }

    //배열 유효 범위 체크
    public static boolean isRange(int nx, int ny) {
        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
            return true;
        }

        return false;
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}
