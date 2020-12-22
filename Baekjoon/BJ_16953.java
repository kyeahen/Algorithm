package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Data {
    long num;
    int count;

    public Data (long num, int count) {
        this.num = num;
        this.count = count;
    }
}

// A -> B - 알고리즘 스터디 (공통)
// - bfs
public class BJ_16953 {

    static int A, B;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        A = s.nextInt();
        B = s.nextInt();

        bfs(A, B);
    }

    public static void bfs(int a, int b) {
        Queue<Data> q = new LinkedList<>();
        q.add(new Data(a, 0));

        while (!q.isEmpty()) {
            Data d = q.poll();

            if (d.num == b) { //A를 B로 만드는데 성공
                System.out.println(d.count + 1);
                return;
            }

            for (int i = 0; i < 2; i++) {
                long n = d.num;

                if (i == 0) { //2를 곱한다.
                    n *= 2;

                } else { //1을 수의 가장 오른쪽에 추가한다.
                    String str = String.valueOf(n);
                    str += "1";
                    n = Long.valueOf(str);
                }

                if (n <= b) { //계산한 값이 B보다 같거나 크지 않으면
                    q.add(new Data(n, d.count + 1));
                }
            }
        }

        //만들 수 없는 경우
        System.out.println(-1);
    }
}
