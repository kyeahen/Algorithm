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

        int[] count = new int[3]; //수포자들이 맞춘 정답의 개수를 저장하는 배열

        for (int i = 0; i < answers.length; i++) {

            if (answers[i] == ans1[i % 5]) { //수포자1
                count[0]++;
            }

            if (answers[i] == ans2[i % 8]) { //수포자2
                count[1]++;
            }

            if (answers[i] == ans3[i % 10]) { //수포자3
                count[2]++;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>(); // 가장 많은 문제를 맞춘 사람을 저장하는 배열

        int max = -1;
        for (int i = 0; i < count.length; i++) { //최댓값 찾기
            if (max < count[i]) {
               max = count[i];
            }
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == max) { // 가장 높은 점수를 받은 사람들 배열에 추가하기
                arr.add(i + 1);
            }
        }

        return arr.stream().mapToInt(i -> i).toArray(); //ArrayList -> int[]로 변환하여 반환
    }
}
