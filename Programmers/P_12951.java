package Programmers;

//JadenCase 문자열 만들기 - level2
public class P_12951 {

    public static void main(String[] args) {
        String s = "ab a ";

        System.out.print(solution(s));
    }

    public static String solution(String s) {
        String[] wordArr = s.split(" ");
        char end = s.charAt(s.length() - 1);

        String result = "";
        for (int i = 0; i < wordArr.length; i++) {

            for (int j = 0; j < wordArr[i].length(); j++) {
                char alpha = wordArr[i].charAt(j);

                if (j == 0) {
                    alpha = Character.toUpperCase(alpha);
                } else {
                    alpha = Character.toLowerCase(alpha);
                }

                result += alpha;
            }
            result += " ";
        }

        //마지막에 공백문자가 들어가면 그대로 출력하고 아니면 앞뒤 공백을 제거한 후, 반환
        if (end == ' ') {
            return result;
        }

        return result.trim();
    }
}
