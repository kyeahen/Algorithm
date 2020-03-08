import java.util.Arrays;

//문자열 내림차순으로 배치하기 - level1
public class P_12917 {

    public static void main(String[] args) {
        String s = "Zbcdefg";

        System.out.print(solution1(s));
    }

    public static String solution1(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);

        char[] resultArray = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            resultArray[i] = array[array.length - i - 1];
        }

        String resultStr = new String(resultArray);
        return resultStr;
    }

    public static String solution2(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);

        return new StringBuilder(new String(array)).reverse().toString();
    }
}
