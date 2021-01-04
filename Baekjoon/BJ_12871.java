package Baekjoon;

import java.util.Scanner;

//무한문자열 - 알고리즘 스터디(공통)
// - 문자열
public class BJ_12871 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fs = sc.next();
        String ft = sc.next();

        if (fs.length() == ft.length()) { //문자열 길이가 같을 때

            if (fs.equals(ft)) { //같으면
                System.out.println(1);
            } else { //다르면
                System.out.println(0);
            }

            return;
        }

        //문자열 길이가 다를 때
        int lcm = lcm(fs.length(), ft.length()); //두 문자열 길이의 최소공배수

        //최소공배수 길이만큼 문자열 붙여보기
        String s = "";
        for (int i = 1; i <= lcm / fs.length(); i++) {
            s += fs;
        }

        String t = "";
        for (int i = 1; i <= lcm / ft.length(); i++) {
            t += ft;
        }

        if (s.equals(t)) { //두 문자열이 같으면
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    //최대공약수
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    //최소공배수
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
