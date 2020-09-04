package Programmers;

//2020 KAKAO Blind Recqruitment - 괄호 변환
public class P_60058 {

    public static void main(String[] args) {
        String p = "()))((()";
        System.out.println(solution(p));
    }

    public static String solution(String p) {

        if (p.equals("")) {
            return "";
        }

        if (isCheck(p)) { //재귀 종료 조건
            return p;
        }

        String result = "";

        int count = 0;
        int idx = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (c == '(') { //+1
                count += 1;
            } else { //-1
                count -= 1;
            }

            if (count == 0) {
                idx = i;
                break;
            }
        }

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1, p.length());
        //System.out.println("u: " + u + " v: " + v);

        if (isCheck(u)) {
            result = u;
            result += solution(v);

        } else {
            result += "(";
            result += solution(v);
            result += ")";

            u = u.substring(1, u.length() - 1);

            for (int i = 0; i < u.length(); i++) {
                char c = u.charAt(i);

                if (c == '(') {
                    result += ")";
                } else {
                    result += "(";
                }
            }
        }

        return result;
    }

    public static boolean isCheck(String s) {

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                count += 1;
            } else {
                count -= 1;
            }

            if (count < 0) { // ')' 부터 시작하면 올바른 괄호가 아님
                return false;
            }
        }

        return true;
    }
}
