package Baekjoon.BruteForce;
import java.util.Arrays;
import java.util.Scanner;

//일곱난쟁이 - 완전탐색
public class BJ_2309 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] height = new int[9];

        int sum = 0;
        for (int i = 0;i < height.length; i++) {
            height[i] = s.nextInt();
            sum += height[i];
        }
        Arrays.sort(height); //오름차순 정렬

        int[] removeArr = new int[2]; //제외할 난쟁이 키를 저장할 배열
        boolean flag = false;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {

                //(9개 난쟁이 키의 합 - 100 = 제외할 난쟁이들의 키의 합)
                // - 제외할 난쟁이 2명을 찾자!
                if (height[i] + height[j] == sum - 100) {
                    removeArr[0] = height[i];
                    removeArr[1] = height[j];
                    flag = true;
                    break;
                }
            }

            if (flag) {
                break;
            }
        }

        for (int i = 0; i < height.length; i++) {
            if (removeArr[0] != height[i] && removeArr[1] != height[i]) {
                System.out.println(height[i]);
            }
        }
    }
}
