package Programmers;
import java.util.Scanner;

//수박수박수박수박수박수? - level1
public class P_12922 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        System.out.print(solution(n));
    }

    public static String solution(int n) {
            char[] array = new char[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    array[i] = '수';
                } else {
                    array[i] = '박';
                }
            }

            return new String(array);
    }
}
