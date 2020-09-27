package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//소수 경로 - bfs
public class BJ_1963 {

    private static boolean[] isPrime; //소수 저장 배열 (1000 ~ 10000)

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();

        isPrime = new boolean[10000];
        getPrime();

        int[][] pwd = new int[N][2];
        for (int i = 0; i < N; i++) {
            pwd[i][0] = s.nextInt();
            pwd[i][1] = s.nextInt();
        }

        for (int i = 0; i < N; i++) {
            bfs(pwd[i][0], pwd[i][1]);
        }
    }

    public static void bfs(int a, int b) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[10000]; //방문 체크 배열
        int[] count = new int[10000]; //개수 저장 배열

        q.add(a);
        visited[a] = true; //방문 체크
        count[a] = 0;

        while (!q.isEmpty()) {
            int n = q.poll();

            if (n == b) { //b값에 도달하면 탐색 끝
                break;
            }

            for (int i = 0; i < 4; i++) { //자릿수
                for (int j = 0; j < 10; j++) { //임의의 숫자

                    int newNum = change(n, i, j);

                    //1000 이상, 소수, 방문하지 않은 비번이면
                    if (newNum >= 1000 && isPrime[newNum] && !visited[newNum]) {
                        q.add(newNum);
                        visited[newNum] = true; //방문 체크
                        count[newNum] = count[n] + 1; //개수 증가
                    }

                }
            }
        }

        if (count[b] == -1) {
            System.out.print("Impossible");
        } else {
            System.out.println(count[b]);
        }
    }

    //숫자를 교체하는 메소드
    private static int change(int n, int index, int changeNum) {
        char[] num = Integer.toString(n).toCharArray();
        num[index] = (char) (changeNum + '0');

        return Integer.parseInt(new String(num));
    }

    //1000~10000 사이의 소수를 구하는 메소드
    private static void getPrime() {

        for (int i = 1000; i < 10000; i++) {
            isPrime[i] = true;

            int a = (int) Math.sqrt(i);
            for (int j = 2; j <= a; j++) {
                if (i % j == 0) {
                    isPrime[i] = false;
                    break;
                }
            }
        }
    }
}
