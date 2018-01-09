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
        // ArrayList���� �Ϲݹ迭�� ��ȯ

        Scanner scan = new Scanner(System.in);
        do {
            flag = false;
            System.out.println("ã�����ϴ� ���ڸ� �Է����ּ���");
            input = scan.nextInt();
            if(input < 1 || input > transformedArray.length){
                System.out.println("������ �´� ���ڸ� �Է����ּ���");
                flag = true;
            }
        }while(flag);
        start = System.currentTimeMillis();
        index = loopInvariant(transformedArray, input);
        end = System.currentTimeMillis();

        System.out.print("ã�����ϴ� ������ �ε��� : " + index + "\n");
        System.out.print("ã�µ� �ɸ� �ð� : " + (end - start));

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
