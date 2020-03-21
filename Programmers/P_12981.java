package Programmers;
import java.util.ArrayList;
import java.util.Arrays;

//영어 끝말잇기 - level2
public class P_12981 {

    public static void main(String[] args) {
        int n = 5;
        String[] words1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.print(Arrays.toString(solution(n, words2)));
    }

    public static int[] solution(int n, String[] words) {
        int[] resultArr = {0, 0};

        ArrayList<String> tempArr = new ArrayList<>(); // 이전 단어들을 비교하기 위한 배열 생성
        tempArr.add(words[0]); // 첫번째 단어는 비교를 위해 미리 저장

        boolean flag = false; // 탈락자 발생 유무 상태 (유 : true, 무 : false)
        int personIdx = 1; // 탈락하는 사람 번호
        int turn = 1; // 탈락하는 차례

        for (int i = 1; i <= words.length - 1; i++) {
            personIdx++; //번호 증가

            if (i % n == 0) { // 한 차례 되었는지 판별하는 조건
                turn++; // 한 차례 증가
                personIdx = 1; // 새로운 차례가 되었으니 번호 초기화
            }

            char prevEnd = tempArr.get(i - 1).charAt(tempArr.get(i - 1).length() - 1); // 이전 단어의 마지막 알파벳
            char nowFirst = words[i].charAt(0); // 현재 단어의 첫번째 알파벳

            if (tempArr.contains(words[i])) { // 이미 말한 단어를 말했을 경우 (탈락)
                flag = true; // 탈락자 발생
                break;

            } else if (nowFirst != prevEnd) { // 마지막 알파벳으로 시작하지 않는 단어를 말했을 경우 (탈락)
                flag = true; // 탈락자 발생
                break;

            } else { // 탈락자가 없는 경우
                tempArr.add(words[i]); // 다음 비교를 위해 tempArr에 저장
            }
        }

        if (flag) { //탈락자가 있는 경우
            resultArr[0] = personIdx;
            resultArr[1] = turn;
        }

        return resultArr; //탈락자가 없는 경우에는 {0, 0}으로 반환
    }
}