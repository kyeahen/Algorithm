package Programmers;
import java.util.ArrayList;
import java.util.Arrays;

// 모의고사 - level1
public class P_42840 {

    public static void main(String[] args) {
        int[] answers1 = {1, 2, 3, 4, 5};
        int[] answers2 = {1, 3, 2, 4, 2};

        System.out.print(Arrays.toString(solution(answers1)));
    }

    public static int[] solution(int[] answers) {
        int[] ans1 = {1, 2, 3, 4, 5};
        int[] ans2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] ans3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] count = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == ans1[i % 5]) {
                count[0]++;
            }

            if (answers[i] == ans2[i % 8]) {
                count[1]++;
            }

            if (answers[i] == ans3[i % 10]) {
                count[2]++;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();

        int max = -1;
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
               max = count[i];
            }
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == max) {
                arr.add(i + 1);
            }
        }

        return arr.stream().mapToInt(i -> i).toArray();
    }
}
