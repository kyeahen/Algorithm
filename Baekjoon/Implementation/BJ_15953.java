package Baekjoon.Implementation;
import java.util.Scanner;

//상금헌터 - 알고리즘 스터디(공통)
// - 구현
public class BJ_15953 {

    static int[] money2017 = {500, 300, 200, 50, 30, 10}; //상금
    static int[] money2018 = {512, 256, 128, 64, 32};

    static int[] people2017 = {1, 2, 3, 4, 5, 6}; //인원
    static int[] people2018 = {1, 2, 4, 8, 16};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int a = sc.nextInt(); //2017 가정 등수
            int b = sc.nextInt(); //2018 가정 등수

            int sum = 0; //받을 수 있는 상금
            if (a != 0) { //2017

                int count = 0; //등수 카운팅
                for (int i = 0; i < people2017.length; i++) {
                    count += people2017[i];

                    if (count >= a) {
                        sum += money2017[i];
                        break;
                    }
                }
            }

            if (b != 0) { //2018

                int count = 0; //등수 카운팅
                for (int i = 0; i < people2018.length; i++) {
                    count += people2018[i];

                    if (count >= b) {
                        sum += money2018[i];
                        break;
                    }
                }
            }

            System.out.println(sum * 10000);
        }
    }
}
