package Baekjoon.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by kyeahen.
 * Title : 1874 스택 수열
 * Category : 스택
 * Date: 2021/04/30
 * ref : https://1-7171771.tistory.com/25
 */
public class BJ_1874 {

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        int[] numArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 1;
        for (int i = 1; i <= n; i++) { //스택에 n만큼 push
            stack.push(i);
            sb.append("+").append("\n");

            //찾는 숫자를 만났으면 pop
            while (!stack.isEmpty() && stack.peek() == numArr[idx]) {
                stack.pop();
                sb.append("-").append("\n");
                idx++;
            }
        }

        if (stack.isEmpty()) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
     }
}
