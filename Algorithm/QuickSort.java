import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QuickSort {

    public static void main(String[] args) throws IOException {
        long start;
        long end;
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("Worst/100000.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("201202149_quick.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while (tokens.hasMoreTokens()) {
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        int[] transformedArray = array.stream().mapToInt(i -> i).toArray();
        // ArrayList에서 일반배열로 변환

        start = System.currentTimeMillis();
        qucikSort(transformedArray, 0, transformedArray.length-1);
        end = System.currentTimeMillis();

        for(int i = 0; i < transformedArray.length; i++) {
            if(i == transformedArray.length-1)
                writer.write(String.valueOf(transformedArray[i]));
            else
                writer.write(String.valueOf(transformedArray[i]) + " ");
            writer.flush();
        }
        writer.close();
        System.out.println(end-start);
    }

    private static void qucikSort(int[] array, int p, int r) {
        if(p < r){
            int q = partition(array, p, r);
            qucikSort(array, p, q-1);
            qucikSort(array, q+1, r);
        }


    }

    private static int partition(int[] array, int p, int r) {

        int x = array[r];
        int i = p-1;

        for(int j = p; j <= r-1; j++) {
            if(array[j] <= x) {
                i += 1;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        i += 1;
        int temp = array[r];
        array[r] = array[i];
        array[i] = temp;

        return i;
    }

//    private static void qucikSort(ArrayList<Integer> array, int p, int r) {
//        if(p < r){
//            int q = partition(array, p, r);
//            qucikSort(array, p, q-1);
//            qucikSort(array, q+1, r);
//        }
//
//
//    }
//
//    private static int partition(ArrayList<Integer> array, int p, int r) {
//
//        int x = array.get(p);
//        int i = p;
//
//        for(int j = p+1; j <= r; j++) {
//            if(array.get(j) <= x) {
//                i += 1;
//                int temp = array.get(j);
//                array.set(j, array.get(i));
//                array.set(i, temp);
//            }
//        }
//        int temp = array.get(p);
//        array.set(p, array.get(i));
//        array.set(i, temp);
//
//        return i;
//    }

}
