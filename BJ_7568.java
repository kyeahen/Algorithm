import java.util.Scanner;

public class BJ_7568 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        Person[] p = new Person[num];

        for (int i = 0; i < num; i++) {
            int weight = s.nextInt();
            int height = s.nextInt();

            p[i] = new Person(weight, height);
        }

        for (int i = 0; i < num; i++) {

            for (int j = 0; j < num; j++) {
                if (p[i].weight < p[j].weight && p[i].height < p[j].height) {
                    p[i].rank++;
                }
            }
        }

        for (int i = 0; i < num; i++) {
            System.out.print(p[i].rank + " ");
        }

    }
}

class Person {
    int weight;
    int height;
    int rank;

    public Person(int weight, int height) {
        this.weight = weight;
        this.height = height;
        this.rank = 1;
    }
}
