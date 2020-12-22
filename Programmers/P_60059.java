package Programmers;

//2020 KAKAO Blind Recruitment - 자물쇠와 열쇠
public class P_60059 {

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(solution(key, lock));
    }

    public static boolean solution(int[][] key, int[][] lock) {
        return true;
    }

    public static int[][] rotation(int[][] key) {
        int[][] temp = new int[key.length][key.length];

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                temp[i][j] = key[key.length - 1 - j][i];
            }
        }

        return temp;
    }

    public static int[][] move(int[][] key) {
        int[][] temp = new int[key.length][key.length];

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {


            }
        }

        return temp;
    }
}
