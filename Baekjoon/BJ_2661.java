package Baekjoon;
import java.util.Scanner;

//좋은 수열 - 알고리즘 스터디(공통)
// - 백트래킹
// https://bellog.tistory.com/43
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
    // ex) 마지막 1개 - 그 앞의 1개 동일?
    public static boolean check(String str) {

        for (int i = 1; i <= str.length() / 2; i++) {
            String a = str.substring(str.length() - i);
            String b = str.substring(str.length() - 2 * i, str.length() - i);

            if (a.equals(b)) {
                return false;
            }
        }

        return true;
    }
}
