package Programmers;
import java.util.Arrays;

//기능 개발 - level2
public class P_42586 {

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        System.out.print(Arrays.toString(solution(progresses, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] resultArr = new int[100];

        int day = 0;
        for (int i = 0; i < progresses.length; i++) {
            while (100 > progresses[i] + (day * speeds[i])) {
                day++;
            }

            resultArr[day]++;
        }

        return Arrays.stream(resultArr).filter(i -> i != 0).toArray();
    }
}
