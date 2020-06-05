package Programmers;
import java.util.Arrays;

// 땅따먹기 - level2
public class P_12913 {

    public static void main(String[] args) {
        int[][] land = {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}};
        System.out.print(solution(land));
    }

    public static int solution(int[][] land) {

        for(int i = 1; i < land.length; i++) {
            land[i][0] += Math.max(land[i - 1][1], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][1] += Math.max(land[i - 1][0], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][2] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][3]));
            land[i][3] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][2]));
        }

        for(int i = 0; i < land.length; i++) {
            Arrays.sort(land[i]);
        }

        return land[land.length - 1][3];
    }
}
