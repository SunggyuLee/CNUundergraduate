import java.util.ArrayList;

public class Dijkstra {
    static int[][] path = createPath();

    public static void main(String[] args) {

    ArrayList<Point> S = new ArrayList<>();
    ArrayList<Point> Q = new ArrayList<>();

    // index 0 사용 안 함
    S.add(new Point());
    Q.add(new Point('\0', -1));

    for(int i = 1; i < 6; i++)
        Q.add(new Point((char)(i + 64), Integer.MAX_VALUE));
    Q.get(1).setV(0);

        System.out.println("dijkatra's algorithm\n");
    while(Q.size() != 1)
        dijkstra(S, Q);

    }

    private static void dijkstra(ArrayList<Point> S, ArrayList<Point> Q) {
        System.out.println("======================================================================");
        System.out.println("S[" + (S.size()-1) + "] : d[" + minPoint(Q).getAlphabet() +"] = " + minPoint(Q).getV());
        System.out.println("----------------------------------------------------------------------");
        S.add(extractMin(Q));
        buildMinHeap(Q);
        Point temp = S.get(S.size()-1);

        for(int i = 1; i < Q.size(); i++) {
            System.out.print("Q[" + (i-1) + "] : d[" + Q.get(i).getAlphabet() + "] = " + Q.get(i).getV() + " ");

            if(path[temp.getAlphabet()-64][Q.get(i).getAlphabet()-64] != Integer.MAX_VALUE && temp.getV() + path[temp.getAlphabet()-64][Q.get(i).getAlphabet()-64] < Q.get(i).getV()){
                Q.get(i).setV(temp.getV() + path[temp.getAlphabet()-64][Q.get(i).getAlphabet()-64]);
                System.out.print("=> d[" +Q.get(i).getAlphabet() + "] = " + Q.get(i).getV());
        }
            System.out.println();
        }
        buildMinHeap(Q);
    }

    private static int[][] createPath(){
        int[][] path = new int[6][6];

        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 6; j++)
                path[i][j] = Integer.MAX_VALUE;

        path[1][1] = 0;
        path[1][2] = 10;
        path[1][3] = 3;
        path[2][2] = 0;
        path[2][3] = 1;
        path[2][4] = 2;
        path[3][3] = 0;
        path[3][2] = 4;
        path[3][4] = 8;
        path[3][5] = 2;
        path[4][4] = 0;
        path[4][5] = 7;
        path[5][5] = 0;
        path[5][4] = 9;

        return path;
    }

    private static void insert(ArrayList<Point> array, Point node) {
        array.add(node);
        buildMinHeap(array);
    }

    private static Point extractMin(ArrayList<Point> array) {
        Point temp = array.remove(1);
        buildMinHeap(array);

        return temp;
    }

    private static Point minPoint(ArrayList<Point> array){
        return array.get(1);
    }

    private static void buildMinHeap(ArrayList<Point> array) {
        int heap_size = array.size();

        for(int i = heap_size/2; i >= 1; i--){
            minHeapify(array, i);
        }
    }

    private static void minHeapify(ArrayList<Point> array, int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest;

        if(left < array.size() && array.get(left).getV() < array.get(i).getV())
            smallest = left;
        else
            smallest = i;

        if(right < array.size() && array.get(right).getV() < array.get(smallest).getV())
            smallest = right;

        if (smallest != i){
            Point temp = array.get(i);
            array.set(i, array.get(smallest));
            array.set(smallest, temp);
            minHeapify(array, smallest);
        }
    }

    private static int rightChild(int i) {
        return 2*i+1;
    }

    private static int leftChild(int i) {
        return 2*i;
    }

    private static class Point{
        char alphabet = '\0';
        int v = Integer.MAX_VALUE;

        public Point() {

        }

        public Point(char alphabet, int v) {
            this.alphabet = alphabet;
            this.v = v;
        }

        public char getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(char alphabet) {
            this.alphabet = alphabet;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }


}
