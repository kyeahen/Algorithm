package Baekjoon.Graph.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 17141 연구소2
 * Category : 완전탐색, bfs
 * Date: 2021/04/09
 * ref - https://pangtrue.tistory.com/267
 */
public class BJ_17141 {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static ArrayList<Point> virus = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    static int count = 0; //빈칸 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //연구소 크기
        M = Integer.parseInt(st.nextToken()); //놓을 수 있는 바이러스의 개수

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 2) { //바이러스를 놓을 수 있는 칸
                    virus.add(new Point(i, j));
                }

                if (map[i][j] == 0) { //빈칸
                    count++;
                }
            }
        }

        /*
        빈칸은 바이러스가 퍼질 수 있는 공간을 의미
        (바이러스를 놓을 수 있는 칸 - 놓을 수 있는 바이러스의 개수)는 바이러스를 놓지 못해서 남은 자리이기에
        빈칸과 같다. 그렇기에 해당 개수만큼 count를 증가시켜준다.
         */

        count += virus.size() - M;
        visited = new boolean[virus.size()]; //바이러스 체크 배열

        if (count == 0) { //빈칸이 없으면 바이러스는 퍼질 수 없음.
            ans = 0;
        }
        else {
            comb(0, 0);
        }

        // "바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력한다."
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    //조합 (depth = 바이러스 선택 개수, start = 바이러스 index)
    public static void comb(int depth, int start) {

        if (depth == M) { //바이러스 M개 선택 완료
            int[][] copyMap = copy();
            spreadVirus(copyMap, count);
            return;
        }

        //바이러스 선택
        for (int i = start; i < virus.size(); i++) {
            visited[i] = true;
            comb(depth + 1, i + 1);
            visited[i] = false; //백트래킹
        }
    }

    //바이러스 퍼뜨리기
    public static void spreadVirus(int[][] map, int cnt) {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < virus.size(); i++) {

            if (visited[i]) { //선택한 바이러스를 큐에 삽입
                q.offer(virus.get(i));
            }
        }

        int time = 0; //바이러스가 퍼지는 시간
        while (!q.isEmpty()) {
            if (ans <= time) { break; } // 해당 조합은 이전 조합보다 느리기 때문에 최소 시간이 아님

            int len = q.size();
            for (int v = 0; v < len; v++) { // 시작 지점(바이러스)이 여러 개이기 때문에 반복문으로 한번 더 감싼다.
                Point p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (0 <= nx && nx < N && 0 <= ny && ny < N) {

                        //빈칸이 아니면 건너뛰기 (바이러스 - 2, 벽 - 1)
                        if (map[nx][ny] != 0) { continue; }

                        //바이러스 퍼뜨리기
                        map[nx][ny] = 2;
                        q.offer(new Point(nx, ny));
                        cnt--; //빈칸 개수 차감
                    }
                }
            }
            time++; //시간 증가

            if (cnt == 0) { //더 이상 빈칸이 존재하지 않으면 탐색 종료
                ans = time;
                return;
            }

        }
    }

    public static int[][] copy() {
        int[][] temp = new int[N][N]; //map 복사

        //맵 모두 초기화해준 후에 선택된 바이러스만 다시 표시해주기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 2) { //바이러스를 놓을 수 있는 칸을 0으로 초기화
                    temp[i][j] = 0;
                } else { //빈칸, 벽
                    temp[i][j] = map[i][j];
                }
            }
        }

        for (int i = 0; i < virus.size(); i++) {
            if (visited[i]) { //선택된 바이러스를 2로 표기
                Point v = virus.get(i);
                temp[v.x][v.y] = 2;
            }
        }

        return temp;
    }
}
