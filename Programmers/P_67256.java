package Programmers;

/**
 * Created by kyeahen.
 * Title : [카카오 인턴] 키패드 누르기
 * Category : lv.1
 * Date: 2021/05/05
 */
public class P_67256 {

    static int[] fingers = {10, 12}; //왼쪽(0), 오른쪽(1)

    public static void main(String[] args) {

        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        System.out.println(solution(numbers, hand));

    }

    public static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            String dir = "";

            if (num == 1 || num == 4 || num == 7) {
                dir = "L";
            } else if (num == 3 || num == 6 || num == 9) {
                dir = "R";
            } else {
                if (num == 0) { num = 11; }
                if (getDirection(num, hand) == 0) { //왼쪽
                    dir = "L";
                } else { //오른쪽
                    dir = "R";
                }
            }

            sb.append(dir);

            //현재 엄지 손가락 위치 갱신
            if (dir.equals("L")) {
                fingers[0] = num;
            } else {
                fingers[1] = num;
            }
        }

        return sb.toString();
    }

    public static int getDirection(int num, String hand) {
        int left = Math.abs(fingers[0] - num) / 3 + Math.abs(fingers[0] - num) % 3;
        int right = Math.abs(fingers[1] - num) / 3 + Math.abs(fingers[1] - num) % 3;

        if (left == right) {
            if (hand.equals("left")) {
                return 0;
            }
            return 1;
        }

        if (left < right) {
            return 0;
        }

        return 1;
    }

}
