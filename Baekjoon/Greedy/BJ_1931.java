package Baekjoon.Greedy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//회의실배정
public class BJ_1931 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[][] time = new int[n][2];

        for (int i = 0; i < n; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            time[i][0] = a;
            time[i][1] = b;
        }

        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[1] == o2[1]) { //종료시간이 같을 경우
                    return Integer.compare(o1[0], o2[0]); //시작시간을 기준으로 오름차순
                }
                return Integer.compare(o1[1], o2[1]); //종료시간 기준으로 오름차순
            }
        });

        int count = 0;
        int end = -1;
        for (int i = 0; i < n; i++) {

            if (time[i][0] >= end) {
                end = time[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
