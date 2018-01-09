import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by BAEK on 2017-09-17.
 */
public class binarySearch {

    public static void main(String[] args) throws IOException {
        long start;
        long end;

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
//        BufferedReader reader = new BufferedReader(new FileReader("Random/10000.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("201202149_output.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while(reader.ready()){
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while(tokens.hasMoreTokens()){
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        start = System.currentTimeMillis();
        int[] result = binaryInsertionSort(array);
        end = System.currentTimeMillis();

        for(int i = 0; i < result.length; i++) {
            if(i == result.length-1)
                writer.write(String.valueOf(result[i]));
            else
                writer.write(String.valueOf(result[i]) + " ");
            writer.flush();
        }
        writer.close();
        System.out.println(end-start);
    }

    private static int[] binaryInsertionSort(ArrayList<Integer> array) {
        int[] sortedArray = new int[array.size()];
        int low;
        int high;
        int mid;

        sortedArray[0] = array.get(0);

        for(int i = 1; i < array.size(); i++){
            int key = array.get(i);
            low = 0;
            high = i;
            mid = (low + high) / 2;
            do{
                if(key > sortedArray[mid]){
                    low = mid + 1;
                }else {
                    high = mid;
                }
                mid = (low + high) / 2;
            }while(low < high);
      /*if(array.get(1) < array.get(0)){
            sortedArray[0] = array.get(1);
            sortedArray[1] = array.get(0);
        }else{
            sortedArray[0] = array.get(0);
            sortedArray[1] = array.get(1);
        }

        for(int i = 2 ; i < array.size(); i++){
            int key = array.get(i);
            low = 0;
            high = i;

            mid = myBinarySearch(sortedArray, key, low, high);*/
            int j;
            if(i > mid) {
                for (j = i-1; j >= mid; j--) {
                    sortedArray[j + 1] = sortedArray[j];
                }
            }
            sortedArray[mid] = key;
        }

        return sortedArray;
    }

    /*private static int myBinarySearch(int[] sortedArray, int key, int low, int high) {

        int mid = (low + high) / 2;

        if(low >= high)
            return mid;

        if(key > sortedArray[mid])
            return myBinarySearch(sortedArray, key, mid+1, high);
        return myBinarySearch(sortedArray, key, low, mid);


    }*/

}
