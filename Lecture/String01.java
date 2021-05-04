package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 1. 문자 찾기
 * Category : String
 * Date: 2021/04/27
 */

public class String01 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char c = br.readLine().charAt(0);

        System.out.println(solution(str, c));
    }

    public static int solution(String str, char t) {
        int count = 0;

        //모두 대문자로 통일하기
        str = str.toUpperCase();
        t = Character.toUpperCase(t);

        // for문
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == t) {
                //count++;
            }
        }

        // for-each문
        for (char ch : str.toCharArray()) {
            if (ch == t) {
                count++;
            }
        }

        return count;
    }
}
