package Programmers;

//체육복 - level1
// - 탐욕법
public class P_46862 {

    public static int solution(int n, int[] lost, int[] reserve) {

        int[] check = new int[n + 1]; //학생들이 가진 체육복 수 저장

        /*
        여벌 체육복을 가져온 학생이 체육복을 도난당했을 경우를 고려하여
        1로 초기화한 후, 도난 당한 경우는 -1, 여벌이 있는 경우는 +1을 해준다.
        */

        //체육복을 1벌 가진 학생들 = 1
        for (int i = 1; i <= n; i++) {
            check[i] = 1;
        }

        //체육복을 도난 당한 학생들 = 0
        for (int i = 0; i < lost.length; i++) {
            check[lost[i]] -= 1;
        }

        //여벌 체육복을 가진 학생들 = 2
        for (int i = 0; i < reserve.length; i++) {
            check[reserve[i]] += 1;
        }

        for (int i = 1; i <= n; i++) {
            int prev = i - 1; //앞번호
            int next = i + 1; //뒷번호

            if (check[i] == 0) { //체육복 도난 당한 학생

                /*
                탐욕법은 미래를 생각하지 않고 각 단계에서 최선을 다하는 기법이다.
                그렇기에 앞번호부터 체크하여 앞번호에 여벌 체육복이 있을 시에는 앞번호부터 빌린다.
                최대한 뒤의 도난 당한 학생들이 체육복을 빌릴 수 있도록.
                */
                if (prev > 0 && check[prev] == 2) { //앞번호 학생이 여벌 체육복을 가진 경우

                    check[prev] -= 1;
                    check[i] += 1;

                } else if (next <= n && check[next] == 2) { //뒷번호 학생이 여벌 체육복을 가진 경우

                    check[next] -= 1;
                    check[i] += 1;
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {

            if (check[i] > 0) { //체육복이 있는 사람들 체크
                count += 1;
            }
        }

        return count;
    }
}
