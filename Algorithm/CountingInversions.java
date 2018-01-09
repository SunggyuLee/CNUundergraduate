import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountingInversions {

    public static void main(String[] args) throws IOException {

//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("data05_inversion_04.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while (tokens.hasMoreTokens()) {
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        int[] transformedArray = array.stream().mapToInt(i -> i).toArray();
        // ArrayList에서 일반배열로 변환

        System.out.print("Input data : ");
        for(int i = 0; i < transformedArray.length; i++)
            System.out.print(transformedArray[i] + " ");
        System.out.println();

        int answer = sortAndCount(transformedArray, 0, transformedArray.length-1);

        System.out.print("Sorted data : ");
        for(int i = 0; i < transformedArray.length; i++)
            System.out.print(transformedArray[i] + " ");
        System.out.println();
        System.out.println("Number of inversions : " + answer);
    }

    private static int sortAndCount(int[] array, int p, int r) {
        int r1, r2;
        int mid = (p + r) / 2;

        if(r - p + 1 == 1)
            return 0;

        r1 = sortAndCount(array, p, mid);
        r2 = sortAndCount(array, mid + 1, r);
        r = mergeAndCount(array, p, mid, r);

        return r1 + r2 + r;
    }

    private static int mergeAndCount(int[] array, int p, int mid, int r) {
        int count = 0;
        int i = p, j = mid + 1;
        int[] tempArray = array.clone();
        // 둘다 바뀌어 버림 clone안하면

        while(i <= mid && j <= r){
            if(tempArray[i] > tempArray[j]){
                count += (mid - i + 1);
                array[p++] = tempArray[j++];
            }else {
                array[p++] = tempArray[i++];
            }
        }

        if(i > mid) {
            while(j <= r)
                array[p++] = tempArray[j++];
        }

        if(j > r) {
            while (i <= mid)
                array[p++] = tempArray[i++];
        }
        return count;
    }
}
