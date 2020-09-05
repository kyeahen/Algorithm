package Programmers;
import java.util.ArrayList;

//2018 KAKAO Blinde Recruitment - [1차] 뉴스 클러스터링
public class P_17677 {

    static int NUM = 65536;
    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        String str3 = "handshake";
        String str4 = "shake hands";

        String str5 = "FRANCE";
        String str6 = "french";

        String str7 = "E=M*C^2";
        String str8 = "e=m*c^2";
        System.out.println(solution(str5, str6));
    }

    public static int solution(String str1, String str2) {

        ArrayList<String> s1Arr = multipleSets(str1.toUpperCase());
        ArrayList<String> s2Arr = multipleSets(str2.toUpperCase());

        ArrayList<String> intersection = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();

        //교집합
        for (String s1 : s1Arr) {
            for (String s2 : s2Arr) {

                if (s1.equals(s2)) {
                    intersection.add(s1);
                    s2Arr.remove(s2);
                    break;
                }
            }
        }

        //합집합
        union.addAll(s1Arr);
        union.addAll(s2Arr);

        //자카드 유사도 - 두 집합의 교집합 크기 / 두 집합의 합집합 크기
        double j = 0.0;
        if (union.size() == 0) { //공집합일 경우에는 1로 반환
            j = 1;
        } else {
            j = (double) intersection.size() / (double) union.size();
        }

        int result = (int) (j * NUM);

        return result;
    }

    public static ArrayList<String> multipleSets(String str) {
        ArrayList<String> set = new ArrayList<>();

        for (int i = 1; i <= str.length() - 1; i++) {
            char a = str.charAt(i - 1);
            char b = str.charAt(i);

            if (a >= 'A' && a <= 'Z' && b >= 'A' && b <= 'Z') {
                set.add(a + "" + b);
            }
        }

        return set;
    }
}
