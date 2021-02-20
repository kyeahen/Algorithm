package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//보물섬 - bfs
public class BJ_2589 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int height, width;
    static char[][] map;
    static int[][] visted;

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static int result = 0; //가장 긴 시간이 걸리는 육지 두곳 사이의 최단거리 (최대값)

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        height = s.nextInt(); //세로 크기
        width = s.nextInt(); // 가로 크기

        map = new char[height][width]; //보물섬 지도 내용을 입력할 배열

        for (int i = 0; i < height; i++) {
            String str = s.next();
            for (int j = 0; j < width; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (map[i][j] == 'L') { //이동은 육지에서만 가능하기에 육지일 때, bfs 탐색
                    visted = new int[height][width]; //방문 여부를 체크하는 배열
                    bfs(i, j);
                }
            }
        }

        System.out.print(result - 1); //처음에 방문했을 때, 1값 삽입 후에 거리를 증가시켰기 때문에 -1을 해준다.
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        visted[x][y] = 1; //방문한 칸 -> 1
        q.add(new Point(x, y)); //큐에 좌표 삽입

        int max = 0;
        while (!q.isEmpty()) {
            Point p = q.poll(); //큐에서 좌표 하나 꺼내기
            int x1 = p.x;
            int y1 = p.y;

            //해당 좌표 기준으로 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x1;
                int ny = dy[i] + y1;

                if (nx >= 0 && nx < height && ny >= 0 && ny < width) {

                    if (map[nx][ny] == 'L' && visted[nx][ny] == 0) { //육지이고, 방문하지 않은 곳일 때

                        q.add(new Point(nx, ny)); //큐에 해당 칸 좌표 삽입
                        visted[nx][ny] = visted[x1][y1] + 1; //거리 증가
                        max = Math.max(max, visted[nx][ny]); //해당 시작 좌표에서의 최대값 저장
                   }
                }
            }
        }

        result = Math.max(max, result); //각 시작 좌표 중에서의 최대값 저장
    }
}
