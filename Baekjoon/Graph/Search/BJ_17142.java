package Baekjoon.Graph.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
- 시간 초과 -> 82%
(활성 바이러스 M개 선택 부분)
> dfs(재귀)로 map을 백트래킹하면서 돌도록 구현
! 리스트 + 조합을 사용 (82%)

- 82% -> 86%
("활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다.")
> 이 부분을 간과함. bfs 돌 때, 빈칸(0)인 경우만 큐에 넣어줘서 탐색함
! bfs 돌 때, 비활성 바이러스(2)인 경우도 큐에 넣어줌

- 91% -> 100%
(바이러스가 빈칸에 복제될 경우에는 1초가 걸리지만, 비활성화 바이러스를 활성화 시킬 경우에는 변경될뿐 "시간"이 추가되지 않음)
> 이 부분을 생각하지 못함.
! 0,2 모두 큐에 기존 시간에 1초 증가시켜서 둘다 넣어주지만, max값은 0일 때만 갱신
+ 바이러스가 퍼진 빈칸(0)은 2로 변경

 */

//연구소3 - 알고리즘 스터디 (공통)
// - bfs + 백트래킹
// https://hidelookit.tistory.com/161
public class BJ_17142 {

    static class Virus {
        int x;
        int y;
        int time; //시간

        public Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time; //시간
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int result = Integer.MAX_VALUE;

    static ArrayList<Virus> virus_list = new ArrayList<>();
    static boolean[] virus_check;

    //0 = 빈칸, 1 = 벽, 2 = 비활성 바이러스, -1 = 활성 바이러스
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 2) {
                    virus_list.add(new Virus(i, j, 0));
                }

                // - 82%
//                if (map[i][j] == 2) { //바이러스
//                    map[i][j] = -1; //활성
//                    virus(1);
//                    map[i][j] = 2; //비활성
//                }
            }
        }

        virus_check = new boolean[virus_list.size()];
        virus_comb(0, 0);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    // - 82%
    //활성 바이러스 M개 선택
    public static void virus_x(int count) {

        if (count == M) {
            visited = new boolean[N][N];
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 2) { //바이러스
                    map[i][j] = -1; //활성
                    virus_x(count + 1);
                    map[i][j] = 2; //비할성
                }
            }
        }
    }

    //활성 바이러스 M개 선택 (조합)
    public static void virus_comb(int index, int count) {

        if (count == M) {
            visited = new boolean[N][N];
            bfs();
            return;
        }

        for (int i = index; i < virus_list.size(); i++) {
            virus_check[i] = true;
            virus_comb(i + 1, count + 1);
            virus_check[i] = false;
        }
    }


    //바이러스 퍼뜨리기
    public static void bfs() {
        int[][] temp = copy();
        Queue<Virus> q = new LinkedList<>();

        for (int i = 0; i < virus_list.size(); i++) {


            // - 82%
//                if (temp[i][j] == -1) { //활성 바이러스
//                    q.offer(new Virus(i, j, 0));
//                }

            if (virus_check[i]) {
                Virus v = virus_list.get(i);
                q.offer(new Virus(v.x, v.y, v.time));
                visited[v.x][v.y] = true;
                temp[v.x][v.y] = -1; //활성 바이러스
            }
        }

        int max = 0; //바이러스가 퍼지는 시간
        while (!q.isEmpty()) {
            Virus v = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + v.x;
                int ny = dy[i] + v.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {

                    if (visited[nx][ny]) { continue; } //이미 방문한 곳이면 건너뛰기
                    if (temp[nx][ny] == 1) { continue; } //벽이면 건너뛰기

                    //빈칸(0), 비활성화 바이러스(2)
                    visited[nx][ny] = true;
                    q.offer(new Virus(nx, ny, v.time + 1));

                    if (temp[nx][ny] == 0) { //빈칸
                        temp[nx][ny] = 2; //바이러스 퍼짐 (비활성)
                        max = Math.max(v.time + 1, max); //1초 증가
                    }
                }
            }
        }

        //모든 빈칸에 바이러스를 퍼뜨렸으면 (true)
        if (check(temp)) {
            result = Math.min(max, result); //최소 시간 갱신
        }
    }

    //모든 빈칸에 바이러스 퍼뜨렸는지 확인
    public static boolean check(int[][] temp) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (temp[i][j] == 0) { //빈칸
                    return false;
                }
            }
        }

        return true;
    }

    public static int[][] copy() {
        int[][] temp = new int[N][N]; //map 복사

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }
}
