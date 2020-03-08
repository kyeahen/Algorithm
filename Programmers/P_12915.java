import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

//문자열 내 마음대로 정렬하기 - level1
public class P_12915 {

    public static void main(String[] args) {
        String[] strings = {"abce", "abcd", "cdx"};
        int n = 2;

        System.out.print(Arrays.toString(solution(strings, n)));
    }

    public static String[] solution(String[] strings, int n) {

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i].charAt(n) + strings[i]);
        }

        Collections.sort(list);

        for (int i = 0; i < strings.length; i++) {
            strings[i] = list.get(i).substring(1);
        }

        return strings;
    }
}
