package Baekjoon.Graph.Search;

import java.util.*;

//성곽 - 알고리즘 스터디 (공통)
// bfs
public class BJ_2234 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0}; //남동북서 -> 동남서북
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        m = s.nextInt();

        map = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = s.nextInt();
            }
        }

        int room = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    //System.out.println(map[i][j]);
                    max = Math.max(max, bfs(i, j));
                    room++;
                }
            }
        }

        System.out.println(room);
        System.out.println(max);

        //내가 푼 풀이 - 3번 풀이
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                boolean[] isWall = getWallData(map[i][j]);

                for (int k = 0; k < 4; k++) {
                    visited = new boolean[m][n];
                    int[] num = {8, 4, 2, 1};

                    if (isWall[k]) {
                        map[i][j] -= num[k];
                        max = Math.max(max, bfs(i, j));
                        map[i][j] += num[k]; //백트래킹
                    }
                }
            }
        }

        //비트마스크 풀이 - 3번 풀이
//        for(int i = 0; i < m; i++) {
//            for(int j = 0; j < n; j++) {
//                for(int bit=1; bit<=8; bit<<=1) {
//                    if((map[i][j] & bit)!=0) {
//                        visited = new boolean[m][n];
//                        map[i][j] -= bit;
//                        max = Math.max(max, bfs(i,j));
//                        map[i][j] += bit;
//                    }
//                }
//            }
//        }

        System.out.println(max);
    }

    //1번, 2번 풀이
    public static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        int count = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();

            boolean[] isWall = getWallData(map[p.x][p.y]);

            for (int i = 0; i < 4; i++) {

                if (!isWall[i]) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));
                            count += 1;
                        }
                    }
                }

            }
        }

        return count;
    }

    //10진수를 2진수로 바꾼 후, 벽에 대한 정보를 얻는 메소드 (메모리 초과 해결)
    public static boolean[] getWallData(int current) {
        boolean[] isWall = new boolean[4]; //남, 동, 북, 서

        int i = isWall.length - 1;
        while (current / 2 != 0) {
            int a = current % 2;

            if (a == 1) { //벽일 때
                isWall[i] = true;
            }

            current = current / 2;
            i--;
        }

        if (current % 2 == 1) {
            isWall[i] = true;
        }

        return isWall;
    }

    //10진수 -> 2진수로 바꾸는 메소드 - 메모리 초과 원인!
//    public static int[] getBit(int current) {
//        int[] bit = new int[4];
//
//        int i = bit.length - 1;
//        while (current / 2 != 0) {
//            int a = current % 2;
//            bit[i] = a;
//            current = current / 2;
//            i--;
//        }
//        bit[i] = current % 2;
//
//        return bit;
//    }

}
