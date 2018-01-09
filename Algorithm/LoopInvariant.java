import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoopInvariant {

    public static void main(String[] args) throws IOException {
        long start;
        long end;
        int input;
        int index;
        boolean flag;

//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("InvariantData/10000000.txt"));

        ArrayList<Integer> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while (tokens.hasMoreTokens()) {
                array.add(Integer.parseInt(tokens.nextToken()));
            }
        }
        int[] transformedArray = array.stream().mapToInt(i -> i).toArray();
        // ArrayList에서 일반배열로 변환

        Scanner scan = new Scanner(System.in);
        do {
            flag = false;
            System.out.println("찾고자하는 숫자를 입력해주세요");
            input = scan.nextInt();
            if(input < 1 || input > transformedArray.length){
                System.out.println("범위에 맞는 숫자를 입력해주세요");
                flag = true;
            }
        }while(flag);
        start = System.currentTimeMillis();
        index = loopInvariant(transformedArray, input);
        end = System.currentTimeMillis();

        System.out.print("찾고자하는 숫자의 인덱스 : " + index + "\n");
        System.out.print("찾는데 걸린 시간 : " + (end - start));

    }

    private static int loopInvariant(int[] array, int input) {
        int p = 0;
        int r = array.length - 1;
        int mid = r / 2;

        while(input != array[mid]) {
            if(input > array[mid]) {
                p = mid + 1;
            }else{
                r = mid;
            }
            mid = ( p + r ) / 2;
        }
        return mid;
    }


}
