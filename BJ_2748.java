import java.util.Scanner;

public class BJ_2748 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();

        long arr[] = new long[input + 1];
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        System.out.print(arr[input]);
    }
}
