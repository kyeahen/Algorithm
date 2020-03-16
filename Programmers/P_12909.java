package Programmers;

//올바른 괄호 - level2
public class P_12909 {

    public static void main(String[] args) {
        String s = "(()(";

        System.out.print(solution(s));
    }

    public static boolean solution(String s) {
        char[] arr = s.toCharArray();

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }

        if (count > 0) {
            return false;
        }

        return true;
    }
}
