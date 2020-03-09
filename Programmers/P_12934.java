package Programmers;

//정수 제곱근 판별 - level1
public class P_12934 {
    public static void main(String[] args) {
        long n = 3;
        System.out.print(solution1(n));
    }

    public static long solution1(long n) {
        long root = (long) Math.sqrt(n);

        if (n == Math.pow(root, 2)) {
            return (long) Math.pow(root + 1, 2);
        } else {
            return -1;
        }
    }

    //시간 초과 풀이 (11/18)
    public static long solution2(long n) {
        long result = 0;

        int i = 0;
        while (n != (i * i)) {
            if (n < (i * i)) {
                result = -1;
                break;
            }
            result = (i + 2) * (i + 2);
            i++;
        }

        return result;
    }
}
