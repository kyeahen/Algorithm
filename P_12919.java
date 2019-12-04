//서울에서 김서방 찾기 - leve;1
public class P_12919 {
    static String KIM = "Kim";

    public static void main(String[] args) {
        String[] seoul = {"Jane", "Kim"};
        System.out.print(solution(seoul));
    }

    public static String solution(String[] seoul) {
        int x = 0;
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals(KIM)) {
                x = i;
            }
        }

        return "김서방은 " + x + "에 있다";
    }
}
