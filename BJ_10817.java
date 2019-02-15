import java.util.Arrays;
import java.util.Scanner;

public class BJ_10817 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] inputArr = new int[3];

        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = s.nextInt();
        }

        Arrays.sort(inputArr);

        System.out.print(inputArr[1]);

    }
}
