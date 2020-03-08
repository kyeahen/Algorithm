import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ_1427 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String input = s.next();

        char[] inputCharArray = input.toCharArray();
        Integer[] inputNumArray = new Integer[inputCharArray.length];

        for (int i = 0; i < inputCharArray.length; i++) {
            inputNumArray[i] = Character.digit(inputCharArray[i], 10); // char -> int
        }

        Arrays.sort(inputNumArray, Collections.reverseOrder());

        for (int i = 0; i < inputCharArray.length; i++) {
            System.out.print(inputNumArray[i]);
        }
    }
}
