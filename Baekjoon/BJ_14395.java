package Baekjoon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Operation {
    long n; //int형으로 하면 틀림
    String result; //연산 순서

    Operation(long n, String result) {
        this.n = n;
        this.result = result;
    }
}

//4연산 - 알고리즘 스터디(공통)
// - BFS
public class BJ_14395 {

    static long S, T;
    static char[] op4 = {'*', '+', '-', '/'};
    static HashSet<Long> set = new HashSet<>(); //think!

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        S = s.nextInt();
        T = s.nextInt();

        bfs();
    }

    public static void bfs() {
        Queue<Operation> q = new LinkedList<>();
        q.add(new Operation(S, ""));
        set.add(S);

        if (S == T) { //s와 t가 같으면 0 반환
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()) {
            Operation o = q.poll();

            if (o.n == T) { //t와 같으면 반환
                System.out.println(o.result);
                return;
            }

            //사전 순으로 연산 돌리기
            for (int i = 0; i < 4; i++) {
                long num = calculate(i, o.n); //연산 결과값

                if (num <= 0) { //음수이면 건너뛰기 (ArithmeticException 발생)
                    continue;
                }

                if (set.contains(num)) { //중복된 숫자이면 건너뛰기 (메모리 초과)
                    continue;
                }

                set.add(num);
                q.add(new Operation(num, o.result + op4[i]));
            }
        }

        System.out.println(-1); //바꿀 수 없는 경우, -1 반환
    }

    public static long calculate(int op, long n) {

        if (op == 0) { //*
            n *= n;
        } else if (op == 1) { //+
            n += n;
        } else if (op == 2) { //-
            n -= n;
        } else { // /
            n /= n;
        }

        return n;
    }
}
