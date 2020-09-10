package Programmers;
import java.util.ArrayList;

//2018 KAKAO Blind Recruitment - [1차] 다트게임
public class P_17682 {

    public static void main(String[] args) {
        String dart1 = "1S2D*3T";
        String dart2 = "1D2S#10S";
        String dart3 = "1D2S0T";
        String dart4 = "1S*2T*3S";
        String dart5 = "1D#2S*3S";
        String dart6 = "1T2D3D#";
        String dart7 = "1D2S3T*";

        System.out.println(solution(dart7));
    }

    public static int solution(String dartResult) {
        ArrayList<String> arr = new ArrayList<>();

        int start = 0 ;
        int lastIdx = dartResult.length() - 1;
        for (int i = 1; i < dartResult.length(); i++) {

            char c = dartResult.charAt(i);
            if (c != 'S' && c != 'D' && c != 'T' && c != '*' && c != '#') { //해당 문자가 숫자일 때
                int b = dartResult.charAt(i - 1) - '0'; //이전 문자

                if (b >= 0 && b <= 9) { //이전 문자가 숫자이면 통과 (10일 경우를 체크)
                    i++;
                } else {
                    arr.add(dartResult.substring(start, i));
                    start = i;
                }
            }

            if (i == lastIdx) { //마지막 문자면 마지막 문자열 추가
                arr.add(dartResult.substring(start, i + 1));
            }
        }

        int[] sum = new int[arr.size()]; //각 문자열의 합을 저장하는 배열
        for (int i = 0; i < arr.size(); i++) {
            String current = arr.get(i);
            int len = current.length();

            String num = current.replaceAll("[^0-9]", ""); //점수
            String word = current.replaceAll("[0-9]", ""); //보너스, 옵션

            int score = Integer.parseInt(num);
            char bonus = word.charAt(0);

            // - 보너스 계산
            if (bonus == 'S') { //싱글
                sum[i] += score;
            } else if (bonus == 'D') { //더블
                sum[i] += Math.pow(score, 2);
            } else { //트리플
                sum[i] += Math.pow(score, 3);
            }

            // - 옵션 계산
            char last = current.charAt(len - 1);
            if (last == '*' || last == '#') { //문자열의 마지막 문자가 옵션이면
                char option = word.charAt(1);

                if (option == '*') { //스타상
                    if (i == 0) { //스타상이 첫번째 기회에 나오면 첫번째만 2배
                        sum[i] *= 2;
                    } else {
                        sum[i - 1] *= 2;
                        sum[i] *= 2;
                    }
                } else { //아차상
                    sum[i] *= -1;
                }
            }
        }

        int total = 0; //총점수
        for (int i = 0; i < sum.length; i++) {
            total += sum[i];
        }

        return total;
    }
}
