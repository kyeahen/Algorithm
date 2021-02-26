package Baekjoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 1065 한수
 * Category : 완전탐색
 * Date: 2021/02/26
 */

public class BJ_1065 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 1; i <= n; i++) {

            if (check(i)) { //등차수열 체크
                count++;
            }
        }

        System.out.println(count);
    }

    public static boolean check(int num) {

        //1의 자리
        int temp = num % 10;
        num /= 10;

        int diff = Integer.MIN_VALUE;

        while (num > 0) {
            int current = num % 10;
            int current_diff = temp - current;

            if (diff == Integer.MIN_VALUE) {
                diff = current_diff;
            } else if (diff != current_diff) {
                return false;
            }

            temp = current;
            num /= 10;
        }

        return true;
    }
 }
