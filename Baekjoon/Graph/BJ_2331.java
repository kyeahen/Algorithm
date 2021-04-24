package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 2331 반복수열
 * Category : 구현, 그래프 탐색
 * Date: 2021/04/23
 */
public class BJ_2331 {

    static int A, P;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dfs(A);
    }

    public static void dfs(int num) {
        if (list.contains(num)) {
            System.out.println(list.indexOf(num)); //인덱스 출력
            return;
        }

        list.add(num);
        dfs(solution(num));
    }

    //D[n] 구하기
    public static int solution(int num) {

        int sum = 0;
        while (num != 0) { //
            int temp = num % 10;
            sum += Math.pow(temp, P); //P번 곱하기
            num /= 10;
        }

        return sum;
    }
}
