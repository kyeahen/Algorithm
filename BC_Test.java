import java.util.*;

public class BC_Test {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 3, 3, 3, 4, 4};
        int[] arr2 = {3, 2, 4, 4, 2, 5, 2, 5, 5};
        int[] arr3 = {3, 7, 5, 9, 1};

        System.out.print(Arrays.toString(sol(arr3)));
    }

    public static int[] sol(int[] arr) {

        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        for (Iterator i = set.iterator(); i.hasNext();) {

            int count = countOf((int) i.next(), arr);
            if (count > 1) {
                result.add(count);
            }

            //System.out.print(i.next());
        }

        if (result.size() == 0) {
            result.add(-1);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int countOf(int a, int[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (a == arr[i]) {
                count++;
            }
        }

        return count;
    }
}
