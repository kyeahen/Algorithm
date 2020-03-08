import java.util.*;

public class BJ_1181 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        String[] inputArray = new String[num];

        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = s.next();
        }

        Arrays.sort(inputArray); //알파벳순 정렬
        Arrays.sort(inputArray, new Comparator<String>() { //문자열 길이순 정렬
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        System.out.println(inputArray[0]);
        for (int i = 1; i < inputArray.length; i++) {
            if (!inputArray[i - 1].equals(inputArray[i])) {
                System.out.println(inputArray[i]);
            }
        }
    }
}
