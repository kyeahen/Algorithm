import java.util.Arrays;

//H-Index - level2
public class P_42747 {

    public static void main(String[] args) {
        int[] arr = {2, 7, 5};
        System.out.print(solution(arr));
    }

    public static int solution(int[] citations) {

        Arrays.sort(citations);

        int result = 0;
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;

            if (citations[i] >= h) {
                result = h;
                break;
            }
        }

        return result;
    }
}
