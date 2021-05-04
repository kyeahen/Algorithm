package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kyeahen.
 * Title : 3. 문장 속 단어
 * Category : String
 * Date: 2021/04/27
 */
public class String03 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        System.out.println(solution(str));
    }

    public static String solution(String str) {
        String[] arr = str.split(" ");

        String result = "";
        int max = 0;
        for (int i = 0; i < arr.length; i++) {

            if (max < arr[i].length()) {
                max = arr[i].length();
                result = arr[i];
            }
        }

        return result;
    }
}

