import java.util.Arrays;
import java.util.Comparator;

// 가장 큰 수 - level2
public class P_42746 {

    public static void main(String[] args) {
        int[] array = {3, 30, 34, 5, 9};
        System.out.print(solution(array));
    }

    public static String solution(int[] numbers) {
        String result = "";
        String[] array = new String[numbers.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2); // 내림차순
            }
        });

        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }

        if (result.startsWith("0")) {
            result = "0";
        }

        return result;
    }
}
