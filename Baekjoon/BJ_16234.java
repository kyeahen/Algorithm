package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//인구이동 - 알고리즘 스터디(공통)
// - BFS
public class BJ_16234 {

    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); //땅 크기 (N x N)
        L = s.nextInt(); //인구 최소값
        R = s.nextInt(); //인구 최대값

        A = new int[N][N]; //인구수 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = s.nextInt();
            }
        }

        int count = 0; //인구이동 횟수
        boolean flag = false; //연합국 유무 여부

        while (true) {
            visited = new boolean[N][N];
            flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (visited[i][j]) { //이미 방문한 나라는 건너뛰기
                        continue;
                    }

                    if (bfs(i, j)) { //연합국이 존재하면
                        flag = true;
                    }
                }
            }

            if (flag) { //연합국이 존재한다면 인구 이동 발생
                count++;

            } else { //연합국 더이상 존재하지 않음 (출력)
                System.out.println(count);
                break;
            }
        }

    }

    public static boolean bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> union = new ArrayList<>(); //연합국 저장

        visited[x][y] = true;
        q.add(new Point(x, y));
        union.add(new Point(x, y));

        int sum = A[x][y]; //연합의 인구수

        while (!q.isEmpty()) {
            Point p = q.poll();

            //상하좌우 체크
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < N && 0 <= ny && ny < N) { //범위 안

                    if (visited[nx][ny]) { //이미 방문했으면 건너뛰기
                        continue;
                    }

                    int diff = Math.abs(A[p.x][p.y] - A[nx][ny]); //두 나라의 인구 차이

                    if (diff >= L && diff <= R) { //두 나라의 인구 차이가 L명 이상, R명 이하라면 (국경선 open)
                        sum += A[nx][ny];

                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                        union.add(new Point(nx, ny));
                    }
                }
            }
        }

        if (union.size() == 1) { //연합국 존재 X
            return false;

        } else { //연합국 존재 O
            int temp = sum / union.size(); //연합국 각 칸의 인구수

            for (Point p : union) { //모든 연합국에 새 인구수 대입
                A[p.x][p.y] = temp;
            }

            return true;
        }
    }
}
