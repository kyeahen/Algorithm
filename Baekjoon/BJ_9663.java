package Baekjoon;
import java.util.Scanner;

//N_Queen - dfs(백트래킹)
public class BJ_9663 {

    static int N;
    static int[] col;

    static int count = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        col = new int[15];

        //각 행에는 하나의 열에만 퀸을 배치할 수 있다.
        for (int i = 1; i <= N; i++) {
            col[1] = i; //1행 i열에 퀸 배치
            dfs(1); //1행 i열에 퀸을 배치했을 경우, 경우의 수 탐색
        }

        System.out.print(count);
    }

    public static void dfs(int start) {

        if (start == N) { //종료 조건 (마지막 행에 도달했을 때)
            count++;
            return;

        } else {
            for (int i = 1; i <= N; i++) {
                col[start + 1] = i; //다음 행의 i열에 퀸을 배치

                if (check(start + 1)) { //해당 위치에 퀸을 놓을 수 있는지 체크
                    dfs(start + 1); //배치 가능하면 다음 퀸 배치 탐색
                }
            }
        }
    }

    //next 행 위치의 퀸이 이전 퀸들의에게 공격을 받는지 판별하는 메소드
    // - 퀸은 상하좌우, 대각선 모두 이동 가능하다.
    public static boolean check(int next) {

        for (int i = 1; i < next; i++) {

            if (col[i] == col[next]) { //같은 열에 존재하면 (행은 다름)
                return false; //배치 불가
            }

            if (Math.abs(i - next) == Math.abs(col[i] - col[next])) { //서로 대각선이면
                return false; //배치 불가
            }
        }

        return true;
    }
}
