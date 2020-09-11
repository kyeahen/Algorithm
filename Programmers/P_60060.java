package Programmers;
import java.util.Arrays;

//2020 KAKAO Blind Recruitment - 가사검색
public class P_60060 {

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] quries = {"fro??", "????o", "fr???", "fro???", "?????"};
        System.out.println(Arrays.toString(solution(words, quries)));
    }

    //효율성 테스트 1,2,3 통과 X -> 다시 풀기
    public static int[] solution(String[] words, String[] quries) {

        int[] result = new int[quries.length];

        for (int i = 0; i < quries.length; i++) {
            for (int j = 0; j < words.length; j++) {

                if (match(words[j], quries[i])) { //두 문자가 매칭되면
                   result[i]++;
                }
            }
        }

        return result;
    }

    public static boolean match (String w, String q) {

        if (w.length() != q.length()) { //글자수가 다르면 매치되지 않음
            return false;
        }

        boolean flag = true;
        for (int i = 0; i < q.length(); i++) {
            char a = q.charAt(i);
            char b = w.charAt(i);

            if (a != b && a != '?') { //문자가 서로 다르고 쿼리 문자가 ?가 아니면
                flag = false; //매치되지 않음
                break;
            }
        }

        return flag;
    }
}
