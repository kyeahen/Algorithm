package Baekjoon;
import java.util.Scanner;
import java.util.Stack;

//괄호의 값 - Stack
public class BJ_2504 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.next(); // (()[[]])([])
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            String v = String.valueOf(str.charAt(i));

            switch (v) {

                case "(":
                    stack.push("(");
                    break;

                case ")":
                    if (!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                        stack.push("2");

                    } else {
                        findValue(stack, ")");
                    }
                    break;

                case "[":
                    stack.push("[");
                    break;

                case "]":
                    if (!stack.isEmpty() && stack.peek().equals("[")) {
                        stack.pop();
                        stack.push("3");
                    } else {
                        findValue(stack, "]");
                    }
                    break;

                default: //괄호가 아닌 다른 입력 값이 들어왔을 때
                    System.out.println("0");
                    return;
            }
        }

        int sum = 0;
        for (String v : stack) {
            if (v.equals("(") || v.equals(")") || v.equals("[") || v.equals("]")) {
                System.out.println(0);
                return;
            }
            sum += Integer.parseInt(v);
        }

        System.out.println(sum);
    }

    //올바른 짝괄호가 있는지 찾는 메소드
    public static void findValue(Stack<String> stack, String current) {

        int ans = 0;
        while (!stack.isEmpty()) {
            String v = stack.peek();

            if (current.equals(")")) { // )
                if (v.equals("(")) {
                    stack.pop();
                    ans *= 2;
                    stack.push(Integer.toString(ans));
                    break;
                } else if (v.equals(")") || v.equals("[") || v.equals("]")) {
                    return;
                } else {
                    stack.pop();
                    ans += Integer.parseInt(v);
                }

            } else { // ]
                if (v.equals("[")) {
                    stack.pop();
                    ans *= 3;
                    stack.push(Integer.toString(ans));
                    break;
                } else if (v.equals("(") || v.equals(")") || v.equals("]")) {
                    return;
                } else {
                    stack.pop();
                    ans += Integer.parseInt(v);
                }
            }
        }

        return;
    }
}
