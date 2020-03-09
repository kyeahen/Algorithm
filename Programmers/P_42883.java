package Programmers;
import java.util.Stack;

//큰 수 만들기 - level2
public class P_42883 {

    public static void main(String[] args) {
        String number = "4177252841";
        int k = 4;

        System.out.print(solution(number, k));
    }

    public static String solution(String number, int k) {

        int size = number.length() - k; //최종 숫자 길이

        char[] input = number.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0 ; i < input.length ; i++) {

            /* [반복 조건]
            1. 스택이 비어있지 않을 때
            2. k가 0이 아닐 때
            3. 스택 가장 위에 있는 데이터가 input[i] 보다 작을 때
             */
            while(!stack.empty() && k > 0 && stack.peek() < input[i]) {
                k--; //제거 개수 감소
                stack.pop(); //가장 위에 있는 데이터 제거
            }

            stack.push(input[i]); //데이터 삽입
        }

        // 최종 숫자 개수 만큼 스택에 있는 데이터 출력
        String result = "";
        for (int i = 0; i < size; i++) {
            result += stack.get(i);
        }

        return result;
    }
}
