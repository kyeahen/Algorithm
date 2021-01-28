package Baekjoon.Greedy;
import java.util.Scanner;

//설탕 배달 - 알고리즘 스터디 (공통)
// - 그리디
public class BJ_2839 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt();

        /*
        최소니까 우선적으로 5킬로그램 봉지를 사용하도록 구성

        - 먼저 5로 나누어지는지 체크 (최소)
        - 5로 나누어 떨어지지 않는데 N이 0 미만이면 이미 음수이기 때문에
          "정확하게 n킬로그램을 만들 수 없다고 판단"

        앞의 if문에 걸리지 않았다면 3킬로그램 봉지를 사용
        => N킬로그램이 0 이상이고 5로 나누어 떨어지지 않기 때문에 3킬로그램 봉지 사용 가능
        */
        int count = 0;
        while (true) {

            if (N % 5 == 0) { //5로 나누어 떨어지면
                count += N / 5;
                System.out.println(count); //봉지 개수 출력
                break;

            } else if (N < 0) { //N킬로그램이 0 미만이 되면
                System.out.println(-1); //정확하게 N킬로그램 만들 수 없는 것
                break;
            }

            N -= 3; //3킬로그램 봉지 사용
            count++; //봉지 1개 증가
        }
    }
}
