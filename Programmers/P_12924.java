package Programmers;

//숫자의 표현 - level2
public class P_12924 {

    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n));
    }

    public static int solution(int n) {

        int count = 0;
        for (int i = n; i > 0; i--) {

            int sum = 0;
            for (int j = i; j > 0; j--) {
                sum += j;

                if (sum == n) {
                    count++;
                    break;
                } else if (sum > n) { //이 부분을 추가하지 않으면 효율성 테스트에서 시간초과가 된다.
                    break;
                }
            }
        }

        return count;
    }
}
