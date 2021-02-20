package Baekjoon.Graph.Search;
import java.util.Scanner;

//좋은 수열 - 알고리즘 스터디(공통)
// - 백트래킹
// https://bellog.tistory.com/43
// https://mygumi.tistory.com/213
public class BJ_2661 {

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        backTracking("");
    }

    public static void backTracking(String str) {
        if (str.length() == N) {
            System.out.println(str);
            System.exit(0);

        } else {
            for (int i = 1; i <= 3; i++) { // "숫자 1, 2, 3으로만 이루어지는 수열이 있다."
                String next = str + i;

                if (check(next)) {
                    backTracking(next);
                }
            }
        }
    }

    //좋은 수열인지 체크하는 메소드
    // - 인접한 두 부분이 동일한 경우는 동일한 수열의 길이가 최소 1부터 최대 n/2인 경우 발생
    //   마지막 N/2개와 그 앞에 N/2개의 수가 동일한지 비교를 해서 한번이라도 동일한 경우가 나오면 나쁜 수열임

    public static boolean check(String str) {

        int len = str.length();
        int start = len - 1;
        int end = len;

        //ex) 123123
        // i=1 | a = 2, b = 3
        // i=2 | a = 31, b = 23
        // i=3 | a = 123, b = 123
        for (int i = 1; i <= len / 2; i++) {
            String a = str.substring(start, end);
            String b = str.substring(start - i, end - i);

            if (a.equals(b)) { //두개가 같으면
                return false; //나쁜 수열
            }
            start -= 1;
        }

        return true; //좋은 수열
    }
}
