package Programmers;
import java.util.Arrays;

//구명보트 - level2
public class P_42885 {

    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        System.out.print(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people); //오름차순 정렬

        int count = 0;
        int i = 0; //최소값(앞) index
        int j = people.length - 1; //최대값(뒤) index

        //i보다 j가 클 동안 반복
        while (i < j) {

            int sum = people[i] + people[j];
            if (sum <= limit) { //최대-최소 조합이 limit 이하일 때
                count++;
                i++; //최소값을 구명보트에 태웠기 때문에 다음 최소값으로 넘어가기 위해 index 증가
            } else {
                count++;
                /*
                최대-최소 조합임에도 불구하고 limit 이상이기 때문에
                혼자 타야하기에 count 값 증가
               */
            }

            j--; //if-else문 조건 모두 최대값 index를 감소
        }

        /* i와 j가 같을 경우는
        차례로 최대-최소값을 비교하다 무게 제한을 충족하지 못해
        결국 2명씩 태우지 못하고 1명씩 태우게 된 경우이다.
         */
        if (i == j) {
            count++;
        }

        return count;
    }
}
