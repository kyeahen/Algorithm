package Baekjoon;

import java.util.Scanner;

//문자열 폭발 - 알고리즘 스터디(공통)
// - 문자열
// https://leveloper.tistory.com/112
public class BJ_9935 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        String explosion = sc.next();

        char[] answer = new char[str.length()];

        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            answer[idx] = str.charAt(i);

            if (check(answer, idx, explosion)) {
                idx -= explosion.length();
            }
            idx++;
        }

        if (idx == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(String.valueOf(answer, 0, idx));
        }
    }

    public static boolean check(char[] answer, int idx, String explosion) {
        int len = idx + 1;

        if (len < explosion.length()) { //현재 문자열 길이 < 폭발 문자열 길이
            return false;
        }

        for (int i = 0; i < explosion.length(); i++) {

            int currentIdx = len - explosion.length() + i;
            if (explosion.charAt(i) != answer[currentIdx]) { //폭발 문자열과 일치하지 않으면
                return false;
            }
        }

        return true;
    }

    // 메모리 초과 풀이 (45%)
    public void failSolution() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String explosion = sc.next();

        String ans = str.replace(explosion, "");

        boolean flag = false;
        while (true) {
            String temp = ans.replace(explosion, "");

            if (temp.equals("")) { //남아있는 문자가 없을 때
                flag = true;
                break;
            }

            if (ans.equals(temp)) { //더이상 폭발 문자열이 없을 때
                break;
            }

            ans = temp;
        }

        if (flag) {
            System.out.println("FRULA");
        } else {
            System.out.println(ans);
        }
    }
}
