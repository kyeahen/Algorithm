import java.util.Scanner;

public class BJ_1978 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        int[] inputArr = new int[num];

        int count = num;
        for (int i = 0; i < inputArr.length; i++) {
            inputArr[i] = s.nextInt();
        }

        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr[i] == 1) {
                count--;
            }

            for (int j = 2; j < inputArr[i]; j++) {
                if (inputArr[i] % j == 0) {
                    count--;
                    break;
                }
            }
        }

        System.out.print(count);
    }
}
