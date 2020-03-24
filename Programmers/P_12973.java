package Programmers;
import java.util.Stack;

//짝지어 제거하기 - level2
public class P_12973 {

    public static void main(String[] args) {
        String s = "baabaa";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            //스택이 차있는 상태에서 가장 위에 있는 데이터가 넣을 데이터와 같다면
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop(); //데이터 삭제
            } else { //스택이 비어있다면
                stack.push(s.charAt(i)); //데이터 삽입
            }
        }

        if (stack.isEmpty()) { //스택이 비어있다면 문자열 모두 짝지어 제거한 상태
            return 1;
        }

        return 0;
    }
}
