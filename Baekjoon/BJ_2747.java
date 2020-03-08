import java.util.Scanner;

public class BJ_2747 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int input = s.nextInt();
        System.out.print(fiboTail(input, 0, 1));
    }


    public static int fiboTail(int num, int before, int after) {
        if (num == 1) {
            return after;
        } else {
            return fiboTail(num - 1, after, before + after);
        }
    }
}
