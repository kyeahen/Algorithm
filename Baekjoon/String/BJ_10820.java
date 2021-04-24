package Baekjoon.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 10820 문자열 분석
 * Category : 문자열
 * Date: 2021/04/23
 */
public class BJ_10820 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = br.readLine()) != null) {
            int[] result = new int[4];

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int n = c - '0';

                if ('a' <= c && c <= 'z') {
                    result[0]++;
                }

                if ('A' <= c && c <= 'Z') {
                    result[1]++;
                }

                if (0 <= n && n <= 9) {
                    result[2]++;
                }

                if (c == ' ') {
                    result[3]++;
                }
            }

            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }
}
