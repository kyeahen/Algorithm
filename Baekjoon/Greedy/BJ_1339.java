package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by kyeahen.
 * Title : 1339 단어 수학
 * Category : 그리디, 완전탐색 (백트래킹)
 * Date: 2021/02/24
 * ref - https://pangtrue.tistory.com/284
       - https://maivve.tistory.com/73
 */

public class BJ_1339 {

    static int N;
    static String[] words;

    static ArrayList<Character> alpha_list = new ArrayList<>();
    static int[] value;
    static boolean[] visited;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();

            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);

                if (!alpha_list.contains(c)) { //리스트에 저장되지 않은 알파벳이면 저장
                    alpha_list.add(c);
                }
            }
        }

        value = new int[26]; //알파벳에 매칭된 숫자
        visited = new boolean[10]; //0-9 숫자 방문 체크

        dfs(0);
        System.out.println(result);
    }

    public static void dfs(int depth) {

        if (depth == alpha_list.size()) {

            int sum = 0;
            for (int i = 0; i < N; i++) {

                int num = 0; //! StringBuilder 사용하면 시간초과
                for (int j = 0; j < words[i].length(); j++) { //문자열마다 숫자 체크
                    char c = words[i].charAt(j);

                    num *= 10; //자릿수 (0 -> 10 > 100...)
                    num += value[alpha_list.indexOf(c)]; // c에 매칭된 숫자

                    /* - ArrayList.indexOf()
                    : 리스트에 동일한 객체가 2개 이상 존재할 때, 가장 앞에 위치한 객체의 index를 리턴
                     */
                }

                sum += num;
            }

            result = Math.max(sum, result);
            return;
        }

        for (int i = 0; i < 10; i++) { //사용된 알파벳에 0-9 숫자 매칭
            if (visited[i]) { //이미 방문한 숫자면 건너뛰기
                continue;
            }

            visited[i] = true;
            value[depth] = i; //해당 알파벳에 숫자 매칭

            dfs(depth + 1);

            //백트래킹
            visited[i] = false;
            value[depth] = 0;
        }
    }
}
