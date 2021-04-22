package Baekjoon.Greedy;
import java.util.Scanner;

//시험 감독
public class BJ_13458 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt(); // 시험장의 개수 (1 ≤ N ≤ 1,000,000)
        int[] A = new int[N]; // 각 시험장에 있는 응시자의 수 (1 ≤ Ai ≤ 1,000,000)

        for (int i = 0; i < N; i++) {
            A[i] = s.nextInt();
        }

        // 1 ≤ B, C ≤ 1,000,000
        int B = s.nextInt(); //총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
        int C = s.nextInt(); //부감독관이 한 시험장에서 감시할 수 있는 응시자의 수

        /* - 중요 point!
        최대값으로 100만개의 시험장에 100만명의 학생들이 들어가 있을 때,
        총감독관과 부감독관이 감시하는 학생 수가 최소값으로 1명이라면 총 감독관은 100만 * 100만 만큼 필요하게 된다.
        이러한 경우에는 int형으로는 커버할 수 없으므로 count 변수를 long으로 선언해준다.
         */
        long count = N; //모든 시험장에 총감독관 1명씩 배치

        for (int i = 0; i < N; i++) { //시험장 개수만큼 돌리기
            A[i] -= B; //총감독관 - 시험장에 오직 1명만 있어야 함

            if (A[i] > 0) { //응시자 인원이 남아 있으면
                count += A[i] / C; //부감독관 - 여러명 가능

                if (A[i] % C != 0) { //아직도 남은 인원이 있으면 부감독관 1명 더 추가
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
