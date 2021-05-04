package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 2. 대소문자 변환
 * Category : String
 * Date: 2021/04/27
 */

public class String02 {

    //대문자와 소문자가 같이 존재하는 문자열을 입력받아 대문자는 소문자로 소문자는 대문자로 변환하여 출력하는 프로그램을 작성하세요.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static String solution(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if ('A' <= c && c <= 'Z') {
                sb.append(String.valueOf(c).toLowerCase());
            }

            if ('a' <= c && c <= 'z') {
                sb.append(String.valueOf(c).toUpperCase());
            }
        }

        return sb.toString();
    }
}
