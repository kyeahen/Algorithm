package Baekjoon;
import java.util.Scanner;

//Cubeditor - 알고리즘 스터디 (공통)
// - KMP 알고리즘
// https://devowen.com/310
public class BJ_1701 {

    /*
    @어떤 문자열 내에서 부분 문자열이 두 번 이상 나오는 문자열을 찾는 기능

    KMP 알고리즘
    - 어떤 문자열 안에 특정 문자열이 몇 개나 있는지를 찾는 알고리즘
    - 접두사와 접미사 정보를 기억해서 가능성이 있는 문자열만 껑충껑충 뛰면서 탐색하는 방법
    - pi[i]에는 주어진 문자열의 인덱스 0부터 인덱스 i까지의 부분 문자열 중에서
        접두사 == 접미사가 되는 가장 긴 접두사의 길이를 넣는다
    - O(M+N) /  O(전체 문자열 길이 + 찾으려는 문자열 길이)
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.next();

        // 두 번 이상 나오는 부분 문자열 중에서 가장 길이가 긴 것을 구하기
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, str.length());
            result = Math.max(result, kmp(temp));
        }

        System.out.println(result);
    }

    // KMP 알고리즘
    // - 해당 문자열 내에 존재하는 부분 문자열 중 "접두사"와 "접미사"가 같은 문자열의 최대 길이 구하기.
    // - 원래는 pi 배열을 리턴하여 문자열 탐색에 KMP 알고리즘으로 사용된다.

    //pi[i]에는 주어진 문자열의 인덱스 0부터 인덱스 i까지의 부분 문자열 중에 (접두사 == 접미사가 되는 가장 긴 "접두사"의 길이)를 넣는다
    public static int kmp(String str) {
        int i = 0; //접두사 길이
        int max = 0;
        int[] pi = new int[str.length()]; //접두사 길이 저장

        for (int j = 1; j < str.length(); j++) { //index

            //왼쪽 인덱스가 0보다 크고, 왼쪽과 오른쪽 문자가 같지 않을 동안 반복
            while (i > 0 && str.charAt(i) != str.charAt(j)) {
                i = pi[i - 1]; //이전 접두사 길이 그대로 저장
            }

            //왼쪽과 오른쪽 문자가 같으면
            if (str.charAt(i) == str.charAt(j)) {
                pi[j] = ++i; //증가한 접두사 길이 저장
                max = Math.max(max, pi[j]); //최대값 갱신
            }
        }

        return max;
    }
}
