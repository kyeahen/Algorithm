package Baekjoon;

import java.util.Scanner;

//문자열 폭발 - 알고리즘 스터디(공통)
// - 문자열
// https://leveloper.tistory.com/112
public class BJ_9935 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next(); //문자열
        String explosion = sc.next(); //폭발 문자열

        char[] answer = new char[str.length()];

        int idx = 0; //배열 인덱스
        for (int i = 0; i < str.length(); i++) {
            answer[idx] = str.charAt(i); //배열에 문자열 차례로 넣기

            if (check(answer, idx, explosion)) { //현재 폭발 문자열을 담고 있으면
                idx -= explosion.length(); //폭발 문자열 길이만큼 인덱스 감소 (삭제)
            }
            idx++; //
        }

        //배열 인덱스는 최종 문자열의 마지막 인덱스를 담고 있음
        if (idx == 0) { //길이 0
            System.out.println("FRULA");
        } else {
            System.out.println(String.valueOf(answer, 0, idx));
        }
    }

    //폭발 문자열인지 체크하는 메소드
    public static boolean check(char[] answer, int idx, String explosion) {
        int len = idx + 1; //현재 문자열의 길이

        if (len < explosion.length()) { //현재 문자열 길이 < 폭발 문자열 길이
            return false;
        }

        //현재 문자열에서 차례로 폭발 문자열 체크하기
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
