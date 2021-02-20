package Baekjoon.String;

import java.util.Scanner;

//문서 검색 - 알고리즘 스터디(공통)
// - 완전탐색
public class BJ_1543 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String doc = s.nextLine(); //문서
        String word = s.nextLine(); //검색하고 싶은 단어

        //문서에서 검색 단어를 1로 변환
        // - "문서와 단어는 알파벳 소문자와 공백으로 이루어져 있다."
        String check = doc.replace(word, "1");

        int count = 0;
        for (int i = 0; i < check.length(); i++) {
            char c = check.charAt(i);

            if (c == '1') { //검색 단어를 1로 변환했기에 1의 개수는 검색 단어의 개수
                count++;
            }
        }

        System.out.println(count);
    }
}
