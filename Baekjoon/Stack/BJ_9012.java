package Baekjoon.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by kyeahen.
 * Title : 9012 괄호
 * Category : 스택, 문자열
 * Date: 2021/04/30
 */

public class BJ_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (!stack.isEmpty()) {
                    if (stack.peek() == '(' && c == ')') { //올바른 괄호 문자열이면 삭제
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }

            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
