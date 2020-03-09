package Programmers;

//가운데 글자 가져오기 - level1
public class P_12903 {
    public static void main(String[] args) {
        String s = "qwer";

        System.out.print(solution(s));
    }

    public static String solution(String s) {
        String result = "";

        if (s.length() % 2 == 0) {
            result += s.charAt((s.length() / 2) - 1);
        }

        result += s.charAt((s.length() / 2));
        return result;
    }
}
