package Baekjoon.Greedy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//동전 0
public class BJ_11407 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();
        int K = s.nextInt();
        Integer[] money = new Integer[N];

        for (int i = 0; i < N; i++) {
            money[i] = s.nextInt();
        }

        //내림차순 정렬
        Arrays.sort(money, new Comparator<Integer>() {
            @Override
            public int compare(Integer m1, Integer m2) {
                return Integer.compare(m2, m1);
            }
        });

        int count = 0;
        for (int i = 0; i < money.length; i++) {
            if (money[i] <= K) {
                int a = K / money[i];
                count += a;
                K = K % money[i];
            }
        }

        System.out.println(count);
    }
}
