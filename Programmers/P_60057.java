package Programmers;

//[2020 카카오 공채] 문자열 압축 - level2
public class P_60057 {

    public static void main(String[] args) {
        String s = "xababcdcdababcdcd";

        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = 1000;//s는 최대 1000이하임

        String str1 = "", str2 = "", result = "";
        int cnt = 1;

        if (s.length() == 1) {
            return 1;
        }

        for (int i = 1; i <= s.length() / 2; i++) {//쪼갤 단위 갯수

            str1 = s.substring(0 , i);
            cnt = 1;
            result = "";

            for (int j = i; j < s.length(); j += i) {

                str2 = s.substring(j, j + i);
                if (str1.equals(str2)) {
                    cnt++;//문자가 반복된다면

                } else {
                    if (cnt == 1) {
                        result += str1;//str1이 반복된 적이 없다면
                    } else {
                        result += cnt + str1;//한번 이상 반복됐다면
                    }
                    cnt = 1;//초기화
                    str1 = str2;
                }

                if (j + i + i > s.length()) {//다음 str2를 찾기엔 substring 범위가 벗어난다면
                    if (cnt != 1) {
                        result += cnt;
                    }
                    result += s.substring(j, s.length());
                    answer = Math.min(answer, result.length());
                    break;
                }
            }
        }
        return answer;
    }

    public static int soulution1 (String s) {
        int answer = 1000;

        String result = "";

        int count = 0;
        for (int i = 1; i <= s.length() / 2; i++) {
            String str1, str2 = "";
            str1 = s.substring(0, i);
            count = 1;

            for (int j = i; j < s.length(); j += i) {
                str2 = s.substring(j, j + 1);

                if (str1.equals(str2)) {
                    count++;
                } else {

                    if (count == 1) {
                        result += str1;
                    } else {
                        result += count + str1;
                    }
                }
                count = 1;
                str1 = str2;


            }
        }

        return answer;

    }
}
