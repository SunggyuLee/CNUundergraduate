import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class mergeSort {

    public static void main(String[] args) throws IOException {

        long start;
        long end;

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
//        BufferedReader reader = new BufferedReader(new FileReader("Worst/5000000.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("201202149_output.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while(reader.ready()){
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while(tokens.hasMoreTokens()){
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        start = System.currentTimeMillis();
        int[] result = mergeSort(array, 0, array.size() - 1);
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

    private static int[] mergeSort(ArrayList<Integer> array, int p, int r) {
        int[] sortedArray = new int[r-p+1];

        if(p < r){
            int q = (p + r) / 2;

            int[] pArray = mergeSort(array, p, q);
            int[] rArray = mergeSort(array, q+1, r);

            sortedArray = merge(pArray, rArray);
        }else{
            sortedArray[0] = array.get(p);
        }
        return sortedArray;
    }

    private static int[] merge(int[] pArray, int[] rArray) {

        int[] temp = new int[pArray.length + rArray.length];
        int i=0, j=0, k=0;
        while(i < pArray.length && j < rArray.length) {
            if(pArray[i]<=rArray[j])
                temp[k++] = pArray[i++];
            else
                temp[k++] = rArray[j++];
        }

        while(i<pArray.length)
            temp[k++] = pArray[i++];

        while(j<rArray.length)
            temp[k++] = rArray[j++];
        return temp;
    }
}
