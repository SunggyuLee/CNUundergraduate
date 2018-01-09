import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KaratsubaAlgorithm {
    public static void main(String[] args) throws IOException{
        //        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("data05_karatsuba.txt"));

        ArrayList<String> array = new ArrayList<>();

        while (reader.ready()) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());

            while (tokens.hasMoreTokens()) {
                array.add(tokens.nextToken());
            }
        }


        System.out.print("Input data : \n");
        for(int i = 0; i < array.size(); i++)
            System.out.println(array.get(i));

        BigInteger answer = karatsuba(new BigInteger(array.get(0)), new BigInteger(array.get(1)));

        System.out.print("Answer : ");
        for(int i = 0; i < answer.toString().length(); i++){
            System.out.print(answer.toString().toCharArray()[i]);
        }
    }

    private static BigInteger karatsuba(BigInteger a, BigInteger b) {
        int n = a.toString().length();
        int m = n/2;
        BigInteger z0, z1, z2;

        if(n == 1){
            return a.multiply(b);
        }

        BigInteger[] x = a.divideAndRemainder(new BigInteger(Integer.toString((int)Math.pow(10, m))));
        BigInteger[] y = b.divideAndRemainder(new BigInteger(Integer.toString((int)Math.pow(10, m))));

        z0 = karatsuba(x[1], y[1]);
        z2 = karatsuba(x[0], y[0]);
        z1 = karatsuba((x[1].add(x[0])), (y[1].add(y[0]))).subtract(z0.add(z2));


        return z0.add(z1.multiply(new BigInteger(Integer.toString((int)Math.pow(10, m))))).add(z2.multiply(new BigInteger(Integer.toString((int)Math.pow(10, m*2)))));

    }
}
