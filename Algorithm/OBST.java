import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class OBST {

    static double[][] e;
    static double[][] w;
    static int[][] root;

    public static void main(String[] args) throws IOException {

        LinkedList<Double> p = new LinkedList<>();
        LinkedList<Double> q = new LinkedList<>();

        BufferedReader reader = new BufferedReader(new FileReader("data11.txt"));
        while(reader.ready()){
            StringTokenizer st = new StringTokenizer(reader.readLine(), "\t");

            while(st.hasMoreTokens()) {
                p.add(Double.parseDouble(st.nextToken()));
                q.add(Double.parseDouble(st.nextToken()));
            }
        }

        e = new double[p.size()+1][p.size()+1];
        w = new double[p.size()+1][p.size()+1];
        root = new int[p.size()+1][p.size()+1];

        OBST(p, q, p.size()-1);

        System.out.println("=============================================================");
        for(int i = 0; i < p.size(); i++){
            System.out.format(i +"\t"+"%.2f" +"\t"+ "%.2f\n",p.get(i), q.get(i));
        }
        System.out.println("\nThe number of keys : " + (p.size()-1));
        System.out.println("=============================================================");


        System.out.println("e(i,j) : ");
        for(int i = 0; i <= p.size(); i++){
            System.out.print("\t" + i + "\t");
        }
        System.out.println();
        for(int i = 0; i < e.length; i++){
            System.out.print(i);
            for(int j = 0; j < e[i].length; j++) {
                    System.out.format("\t" + "%.2f", e[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================================");

        System.out.println("w(i,j) : ");
        for(int i = 0; i <= p.size(); i++){
            System.out.print("\t" + i + "\t");
        }
        System.out.println();
        for(int i = 0; i < w.length; i++){
            System.out.print(i);
            for(int j = 0; j < w[i].length; j++) {
                    System.out.format("\t" + "%.2f", w[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================================");

        System.out.println("root(i,j) : ");
        for(int i = 0; i <= p.size(); i++){
            System.out.print("\t" + i + "\t");
        }
        System.out.println();
        for(int i = 0; i < root.length; i++){
            System.out.print(i);
            for(int j = 0; j < root[i].length; j++) {
                    System.out.format("\t" + "%d" + "\t", root[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================================");
        System.out.println("Minimum expected search cost : " + e[1][p.size()-1]);
    }

    private static void OBST(LinkedList<Double> p, LinkedList<Double> q, int n) {

        for(int i = 1; i <= n+1; i++){
            e[i][i-1] = q.get(i-1);
            w[i][i-1] = q.get(i-1);
        }

        for(int l = 1; l <= n; l++){
            for(int i = 1; i <= n - l + 1; i++){
                int j = i + l - 1;
                e[i][j] = Double.POSITIVE_INFINITY;
                w[i][j] = w[i][j-1]+p.get(j)+q.get(j);
                for(int r = i; r <= j; r++){
                        double temp = e[i][r - 1]+e[r + 1][j]+w[i][j];
                        if (temp < e[i][j]) {
                            e[i][j] = temp;
                            root[i][j] = r;
                        }
                }
            }
        }
    }

}
