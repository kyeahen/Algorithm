package Baekjoon;
import java.util.*;

//연산자 끼워넣기
public class BJ_14888 {

    static ArrayList<Integer> list; // 최소값과 최대값을 찾기 위해 모든 값들을 저장해줌

    static int N;
    static int[] A;
    static int[] op;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); // 수의 개수
        A = new int[N]; // N개의 수 저장 배열
        op = new int[4]; // 연산자 개수 저장 배열

        list = new ArrayList<>(); //모든 SUM 값 저장 (최솟값, 최댓값을 찾기 위함)

        for (int i = 0; i < A.length; i++) {
            A[i] = s.nextInt();
        }

        for (int i = 0; i < op.length; i++) {
            op[i] = s.nextInt();
        }

        DFS(1, A[0]); // 출발 시작

        Collections.sort(list); // 최솟값, 최댓값 출력 위해 오름차순 정렬
        System.out.println(list.get(list.size() - 1)); // 최댓값 출력
        System.out.println(list.get(0)); // 최솟값 출력
    }

    // x는 다음 숫자, sum은 그 곳까지 갔을 때 합
    public static void DFS(int x, int sum) {

        for (int i = 0; i < 4; i++) { //연산자들을 한번씩 다 확인해봐야 한다.

            if (op[i] != 0) { // 해당 연산자 개수가 0이 아닐 때만
                op[i]--; // 연산자를 사용했기 때문에 1씩 감소

                switch (i) {

                    case 0: // 덧셈
                        DFS(x + 1, sum + A[x]); // 다음 숫자 계산
                        break;

                    case 1:// 뺄셈
                        DFS(x + 1, sum - A[x]);
                        break;

                    case 2: // 곱셈
                        DFS(x + 1, sum * A[x]);
                        break;

                    case 3: // 나눗셈
                        DFS(x + 1, sum / A[x]);
                        break;
                }

                op[i]++; // 사용이 끝났으면 다시 추가해준다.
            }
        }

        if (x == N) { // x가 N과 같아질 때 출력 (모든 숫자를 다 사용했을 경우)
            list.add(sum);
        }

    }
}
