/**
 * Created by shadea on 7/9/2015.
 */
import java.util.Random;

public class sorts {

    public int[] selection_sort(int[] list) {
        for(int i = 0;i < list.length;i++) {
            int min = i;
            for(int j = i; j < list.length;j++) {
                if (list[min] > list[j]){
                    min = j;
                }
            }
            int temp = list[i];
            list[i] = list[min];
            list[min] = temp;
        }

        return list;
    }

    public int[] insertion_sort(int[] list) {
        for(int i = 1; i < list.length;i++){
            int j = i;
            while( j > 0 && list[j] <= list[j-1]) {
                int temp = list[j-1];
                list[j-1] = list[j];
                list[j] = temp;
                j--;
            }
        }

        return list;
    }

    public int[] shell_sort(int[] list) {
        int x = 1;

        while (x < (list.length/3)) x = 3*x+1;
            while (x >= 1) {
                for (int i = x; i < list.length;i++){
                    for (int j = i;j >= x && less(list[j],list[j-x]);j -= x){
                        int temp = list[j];
                        list[j] = list[j-x];
                        list[j-x] = temp;
                    }
                }
                x=x/3;
            }

        return list;
    }

    private boolean less(int a, int b){
        if(a < b){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        sorts s = new sorts();
        int[] x = new int[10];

        Random rg = new Random();

        System.out.println("Initial Array below");
        for (int i = 0; i < 10;i++) {
            x[i] = rg.nextInt(100);
            System.out.printf("%d\t", x[i]);
        }

        x = s.shell_sort(x);

        System.out.println("\nSorted Array below");
        for(int i = 0; i < x.length;i++) {
            System.out.printf("%d\t",x[i]);
        }
    }
}
