package Programmers;
import java.util.ArrayList;
import java.util.HashSet;

//2019 KAKAO Blind Recruitment - 후보키
public class P_42890 {

    public static void main(String[] args) {
        String[][] s = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(solution(s));
    }

    // - 비트마스크 풀이
    // 비트마스크 : 2진법으로 집합을 표현할 수 있음 (0 - 없는 것 , 1 -  있는 것)
    public static int solution(String[][] relation) {
        ArrayList<Integer> list = new ArrayList<>();

        int rowLen = relation.length;
        int colLen = relation[0].length;

        // - 부분 집합 만들기
        // (1 << collen) : 1을 열의 길이만큼 왼쪽 쉬프트 연산을 하면 만들 수 있는 최대 이진수의 다음 수가 나온다.
        for (int i = 1; i < (1 << colLen); i++) { // (i - 속성의 index)

            if (!isMinimal(i, list)) { //최소성 검사
                continue;
            }

            if (!isUnique(i, rowLen, colLen, relation)) { //유일성 검사
                continue;
            }

            list.add(i);
        }

        return list.size();
    }

    //최소성 검사 메소드
    public static boolean isMinimal(int set, ArrayList<Integer> list) {

        /* - 포함 관계 확인
        (key & set) : AND 연산을 수행하면 교집합이 나온다.
                      set이 key를 포함하면 key가 리턴된다.
                      ex ) 1010 & 1000 = 1000
         */
        for (int key : list) {
            if ((set & key) == key) { //중복이면 유일성 만족 X
                return false;
            }
        }

        return true;
    }

    //유일성 검사 메소드
    public static boolean isUnique(int set, int rowLen, int colLen, String[][] relation) {
        ArrayList<Integer> list = getSet(set, colLen);
        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < rowLen; i++) {
            String s = "";
            for (int j : list) {
                s += relation[i][j] + " ";
            }
            hashSet.add(s);
        }

        if (hashSet.size() == rowLen) {
            return true;
        }

        return false;
    }

    public static ArrayList<Integer> getSet(int set, int colLen) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < colLen; i++) {
            // (1 << i) : i번 원소를 나타냄
            if ( (set & (1 << i)) != 0) { //i번 원소가 set 집합에 존재할 때
                result.add(i); //배열에 추가
            }
        }

        return result;
    }

}
