package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 9376 탈옥
 * Category : bfs, 다익스트라
 * Date: 2021/02/24
 * ref - https://recordar.tistory.com/entry/%EB%B0%B1%EC%A4%80-9376-%ED%83%88%EC%98%A5
       - https://suhyeokeee.tistory.com/13
 */
public class BJ_9376 {

    static class Point {
        int x;
        int y;

        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int TC;

    static int h, w;
    static char[][] map;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    /*
    죄수 2명을 탈옥시키는 방법 3가지
    - 죄수 1이 죄수 2를 데리고 바깥으로 나가는 경우
    - 죄수 2가 죄수 1을 데리고 바깥으로 나가는 경우
    - 상근이가 죄수 1, 2를 탈출시키는 경우

    위 경우가 동시에 실행되면
    3명을 각자 문을 열면서 탐색할 것이고, 어느 지점에서 만나게 될 것이다.
    그 시점에서 탈옥이 완료되었다고 볼 수 있다.

    어느 지점에서 만나게 될지 모르기 때문에 맵의 모든 지점을 조사해야하고
    각 지점마다 3명이 문을 몇개 열고 왔는지 합산한다.
    그리고 그 중 가장 최솟값이 우리가 원하는 답이된다.
    단, 만나는 지점이 문인 경우 -2를 해줘야 한다. (3명이 동시에 문을 열지 않아도 되기 때문임)

    위 3가지 경우의 BFS를 돌려 3개의 dist[][]를 완성시킨 후, 합한다.
    각 지점마다 조사하면서 최솟값을 구한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken()); //높이
            w = Integer.parseInt(st.nextToken()); //너비

            /* - [맵을 2칸 더 크게 받는 이유]
             3가지 조건 중 "상근이가 죄수를 꺼내는 경우"를 생각하면
             상근이는 감옥 밖을 자유롭게 이동할 수 있다는 문제 조건이 있다.
             이는 감옥의 상하좌우 어디서는 접근이 가능하다는 의미이다.
             이를 위해 기존 맵에 외부 공간을 추가해준다.
            */
            map = new char[h + 2][w + 2];

            Point sangeun = new Point(0, 0); //상근
            Point[] prisoners = new Point[2]; //죄수 2명

            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    map[i][j] = '.';
                }
            }

            //. : 빈공간 , * : 벽, # : 문, $ : 죄수
            int idx = 0;
            for (int i = 1; i <= h; i++) {

                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = str.charAt(j - 1);

                    if (map[i][j] == '$') { //죄수
                        prisoners[idx] = new Point(i, j);
                        idx++;
                    }
                }
            }

            int[][] dist1 = bfs(sangeun);
            int[][] dist2 = bfs(prisoners[0]); //죄수1 탈출
            int[][] dist3 = bfs(prisoners[1]); //죄수2 탈출

            /*
            각 지점마다 3명이 문을 몇개 열고 왔는지 합산한다.
            그리고 그 중 가장 최솟값이 우리가 원하는 답이된다.
             */
            int[][] ans_dist = new int[h + 2][w + 2];
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    ans_dist[i][j] = dist1[i][j] + dist2[i][j] + dist3[i][j];
                }
            }

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (map[i][j] == '*') { continue; } //벽

                    if (map[i][j] == '#') { //문
                        ans_dist[i][j] -= 2;
                        // 단, 만나는 지점이 문인 경우 -2를 해줘야 한다.
                        // - 3명이 동시에 문을 열지 않아도 되기 때문
                    }

                    ans = Math.min(ans, ans_dist[i][j]); //최솟값
                }
            }

            System.out.println(ans);
        }

    }

    //다익스트라 적용
    public static int[][] bfs(Point p) {
        int[][] dist = new int[h + 2][w + 2]; //각 사람이 연 문의 개수 저장

        for (int i = 0; i < h + 2; i++) { //배열 초기화
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(p);
        dist[p.x][p.y] = 0; //초기 개수는 0개

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if (0 <= nx && nx < h + 2 && 0 <= ny && ny < w + 2) {

                    //벽이면 건너뛰기
                    if (map[nx][ny] == '*') { continue; };

                    //문이 아니면 기존의 값을 가지고 간다. (빈공간 . or 죄수 $)
                    if (map[nx][ny] != '#') {
                        if (dist[nx][ny] == Integer.MAX_VALUE || dist[nx][ny] > dist[point.x][point.y]) {
                            dist[nx][ny] = dist[point.x][point.y];
                            q.offer(new Point(nx, ny));
                        }
                    } else { //문이면 개수 증가
                        if (dist[nx][ny] == Integer.MAX_VALUE || dist[nx][ny] > dist[point.x][point.y] + 1) {
                            dist[nx][ny] = dist[point.x][point.y] + 1;
                            q.offer(new Point(nx, ny));
                        }
                    }
                }
            }
        }

        return dist;
    }
}
