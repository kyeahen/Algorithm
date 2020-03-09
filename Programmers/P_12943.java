package Programmers;

//콜라츠 추측 - level1
public class P_12943 {
    public static void main(String[] args) {
        long num = 626331;
        System.out.print(solution(num));
    }

    public static int solution(long num) {

        int i = 0;
        while(num != 1) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num * 3 + 1;
            }
            i++;

            if (i == 500) {
                i = -1;
                break;
            }
        }

        return i;
    }
}
