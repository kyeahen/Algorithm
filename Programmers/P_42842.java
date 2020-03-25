package Programmers;
import java.util.Arrays;

//카펫 - level2
public class P_42842 {

    public static void main(String[] args) {
        int brown = 10;
        int red = 2;

        System.out.print(Arrays.toString(solution(brown, red)));
    }

    public static int[] solution(int brown, int red) {

        int width = 0, height = 0; //가로 길이, 세로 길이
        int area = brown + red; //넓이
        boolean flag = false;

        while (flag != true) {
            width++;

            // 가로 길이 >= 세로 길이이기 때문에 세로 길이는 가로 길이 이하까지만 비교한다.
            for (height = 1; height <= width; height++) {
                /*
                red는 brown 안에 같혀있는 모양이기 때문에
                red의 가로, 세로 길이는 2씩 작다.
                */
                if (width * height == area && red == (width - 2) * (height - 2)) {
                    flag = true;
                    break;

                }
            }
        }

        int[] resultArr = {width, height};
        return resultArr;
    }
}
