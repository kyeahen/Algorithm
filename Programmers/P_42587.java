package Programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Document {
    int idx; //문서의 원래 순서 (인덱스)
    int priority; //문서의 우선 순위

    public Document(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }
}

//프린터 - level2
public class P_42587 {

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3 ,2};
        int location = 0;
        System.out.print(solution(priorities, location));
    }

    public static int solution (int[] priorities, int location) {

        LinkedList<Document> list = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            list.add(new Document(i, priorities[i]));
        }

        int count = 1;
        while (count < priorities.length) {

            int max = 0;
            int maxIdx = 0;
            for (int i = 0; i < list.size(); i++) {

                if (max < list.get(i).priority) {
                    max = list.get(i).priority;
                    priorities[list.get(i).idx] = count;
                    maxIdx = i;
                }
                list.addLast(list.get(i));
            }

            list.remove(maxIdx);
            count++;
            System.out.println(list.size());
        }

//        System.out.print(Arrays.toString(priorities));

        return priorities[location];
    }

}
