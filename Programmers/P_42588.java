package Programmers;
import java.util.Arrays;

//탑 - level2
public class P_42588 {

    public static void main(String[] args) {
        int[] heights = {6, 9, 5, 7, 4};
        System.out.print(Arrays.toString(solution(heights)));
    }

    public static int[] solution(int[] heights) {

        int[] arr = new int[heights.length];

        int start = heights.length - 1;
        for (int i = start; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (heights[i] < heights[j]) {
                    arr[i] = j + 1;
                    break;
                }
            }
        }

        return arr;
    }
}
