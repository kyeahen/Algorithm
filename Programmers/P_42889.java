package Programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//2019 KAKAO Blind Recruitment - 실패율
public class P_42889 {

    public static void main(String[] args) {
        int N = 4;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {4,4,4,4,4};
        System.out.println(Arrays.toString(solution(N, stages2)));
    }

    public static int[] solution(int N, int[] stages) {
        int[] result = new int[N];

        ArrayList<Stage> stageArr = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            double per = getPercent(i, stages);
            stageArr.add(new Stage(i, per));
        }

        //정렬: 실패율이 높은 스테이지부터 내림차순, 실패율이 같으면 스테이지 오름차순
        Comparator<Stage> stageComparator = new Comparator<Stage>() {
            @Override
            public int compare(Stage o1, Stage o2) {

                if (o1.percent == o2.percent) { //실패율이 같으면
                    return o1.index - o2.index; //스테이지 오름차순
                }

                return Double.compare(o2.percent, o1.percent); //실패율 내림차순
            }
        };

        Collections.sort(stageArr, stageComparator);

        for (int i = 0; i < N; i++) {
            result[i] = stageArr.get(i).index;
        }

        return result;
    }

    //스테이지의 실패율을 구하는 메소드
    public static double getPercent(int stage, int[] stages) {

        double a = 0, b = 0;
        for (int i = 0; i < stages.length; i++) {
            if (stage <= stages[i]) {
                b++; //스테이지에 도달한 플레이어 수

                if (stage == stages[i]) {
                    a++; //스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수
                }
            }
        }

        if (b == 0) { //스테이지에 도달한 유저가 없는 경우
            return 0;
        }

        return a / b;
    }
}

class Stage {
    int index; //스테이지 번호
    double percent; //스테이지 실패율

    public Stage(int index, double percent) {
        this.index = index;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "index=" + index +
                ", percent=" + percent +
                '}';
    }
}
