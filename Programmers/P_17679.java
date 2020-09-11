package Programmers;

//2018 KAKAO Blind Recruitment - [1차] 프렌즈 4블록
public class P_17679 {

    static char[][] map; //board 배열
    static boolean[][] visited; //방문 체크 배열
    static boolean fourFlag = true; //2x2 블록 존재 여부 변수

    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};

    public static void main(String[] args) {
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(4, 5, board));
    }

    public static int solution(int m, int n, String[] board) {
        map = new char[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int count = 0;
        while (fourFlag) { // 2x2 배열이 존재할 때만 반복
            fourFlag = false; //fourFlag 초기화
            check(m, n);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (visited[i][j]) { //지워진 블록일 때 (2x2 블록)
                        map[i][j] = ' '; //공백으로 표시
                        count++; //블록 카운팅
                    }
                }
            }

            drop(m, n); //블록 재배치
        }

        return count;
    }

    //2x2 블록을 체크하는 메소드
    public static void check(int m, int n) {

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {

                if (map[i][j] == ' ') { //없는 블록은 건너뛰기
                    continue;
                }

                boolean flag = true; //블록 일치 여부 변수
                for (int k = 0; k < 3; k++) { //해당 블록의 오른쪽, 아래쪽, 대각선을 탐색
                    int x = dx[k] + i;
                    int y = dy[k] + j;

                    if (x >= 0 && x < m && y >= 0 && y < n) { //블록 범위 체크
                        if (map[i][j] != map[x][y]) { //블록 하나라도 일치하지 않으면 탈출
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) { // 2x2 블록을 찾았을 때
                    fourFlag = true;
                    visited[i][j] = true; //해당 블록 방문 체크

                    for (int k = 0; k < 3; k++) { //해당 블록 기준 오른쪽, 아래, 대각선 방문 체크
                        int x = dx[k] + i;
                        int y = dy[k] + j;
                        visited[x][y] = true;
                    }
                }
            }
        }
    }

    //블록을 재배치하는 메소드
    public static void drop(int m, int n) {

        String temp = ""; //해당 열의 블록을 저장하는 문자열
        for (int j = 0; j < n; j++) { //열단위로 탐색 - 공백이 있을 시, 아래로 내려가기 때문
            temp = "";
            for (int i = 0; i < m; i++) {
                temp += map[i][j];
            }
            temp = temp.replaceAll(" ", ""); //공백 제거

            //마지막 블록부터 순서대로 채우기
            for (int i = 0; i < temp.length(); i++) {
                map[m - 1 - i][j] = temp.charAt(temp.length() - 1 - i);
                visited[m - 1 - i][j] = false; //방문 체크 초기화
            }

            if (m > temp.length()) { //블록 개수보다 행이 클 때 (남은 블록이 존재)
                int len = m - temp.length();

                for (int i = 0; i < len; i++) {
                    map[i][j] = ' '; //남은 블록은 공백으로 채우기
                    visited[i][j] = false; //방문 체크 초기화
                }
            }
        }

    }
}
