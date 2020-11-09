package Baekjoon.Greedy;
import java.util.ArrayList;
import java.util.Scanner;

class Day {
    int index;
    int L;
    int P;
    int V;

    public Day (int index, int L, int P, int V) {
        this.index = index;
        this.L = L;
        this.P = P;
        this.V = V;
    }

    @Override
    public String toString() {
        return "Day{" +
                "index=" + index +
                ", L=" + L +
                ", P=" + P +
                ", V=" + V +
                '}';
    }
}

//캠핑
public class BJ_4796 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Day> list = new ArrayList<>();

        int idx = 1;
        while (true) {
            int l = s.nextInt();
            int p = s.nextInt();
            int v = s.nextInt();

            if (l == 0 && p == 0 && v == 0) {
                break;
            }

            list.add(new Day(idx, l, p, v));
            idx++;
        }

        for (Day d : list) {
            int a = d.V / d.P; // - 휴가 / P일 (20 / 8 = 2)
            int b = d.V % d.P; // - 휴가 % P일 (20 % 8 = 4)

            int ans = a * d.L; //2 * 5 = 10
            if (b <= d.L) { //l이 더크면
                if (b + ans <= d.V) {
                    ans += b;
                }
            } else { //나머지가 더 클때

                if (d.L + ans <= d.V) {
                    ans += d.L;
                }
            }

            System.out.println("Case " + d.index + ": " + ans);
        }
    }
}
