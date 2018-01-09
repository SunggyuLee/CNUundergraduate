import java.util.ArrayList;

public class Prim {
    static int[][] path = createPath();

    public static void main(String[] args) {

        ArrayList<Point> S = new ArrayList<>();
        ArrayList<Point> Q = new ArrayList<>();

        // index 0 사용 안 함
        S.add(new Point());
        Q.add(new Point('\0', -1));

        for (int i = 1; i < 10; i++)
            Q.add(new Point((char) (i + 96), Integer.MAX_VALUE));
        Q.get(1).setV(0);

        System.out.println("prim's algorithm\n");
        while (Q.size() != 1)
            prim(S, Q);

        int result = 0;

        for (int i = 1; i < 10; i++) {
            if (S.get(i).getParent() == null) {
                System.out.println("w< ," + S.get(i).getAlphabet() + "> = " + S.get(i).getV());
            } else {
                System.out.println("w<" + S.get(i).getParent().getAlphabet() + "," + S.get(i).getAlphabet() + "> = " + S.get(i).getV());
                result += S.get(i).getV();
            }
        }
        System.out.println();
        System.out.println("w<MST> = " + result);

    }

    private static void prim(ArrayList<Point> S, ArrayList<Point> Q) {
        Point child = extractMin(Q);
        if(child.getParent() != null)
            child.getParent().setChild(child);
        S.add(child);
        buildMinHeap(Q);
        Point temp = S.get(S.size()-1);

        for(int i = 1; i < Q.size(); i++) {

            if(path[temp.getAlphabet()-96][Q.get(i).getAlphabet()-96] != Integer.MAX_VALUE && path[temp.getAlphabet()-96][Q.get(i).getAlphabet()-96] < Q.get(i).getV()){
                Q.get(i).setV(path[temp.getAlphabet()-96][Q.get(i).getAlphabet()-96]);
                Q.get(i).setParent(temp);
            }
        }
        buildMinHeap(Q);
    }

    private static int[][] createPath(){
        int[][] path = new int[10][10];

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                path[i][j] = Integer.MAX_VALUE;

        path[1][1] = 0;
        path[1][2] = 4;
        path[1][8] = 8;
        path[2][1] = 4;
        path[2][2] = 0;
        path[2][3] = 8;
        path[2][8] = 11;
        path[3][2] = 8;
        path[3][3] = 0;
        path[3][4] = 7;
        path[3][6] = 4;
        path[3][9] = 2;
        path[4][3] = 7;
        path[4][4] = 0;
        path[4][5] = 9;
        path[4][6] = 14;
        path[5][4] = 9;
        path[5][5] = 0;
        path[5][6] = 10;
        path[6][3] = 4;
        path[6][4] = 14;
        path[6][5] = 10;
        path[6][6] = 0;
        path[6][7] = 2;
        path[7][6] = 2;
        path[7][7] = 0;
        path[7][8] = 1;
        path[7][9] = 6;
        path[8][1] = 8;
        path[8][2] = 11;
        path[8][7] = 1;
        path[8][8] = 0;
        path[8][9] = 7;
        path[9][3] = 2;
        path[9][7] = 6;
        path[9][8] = 7;
        path[9][9] = 0;

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
        Point parent;
        ArrayList<Point> childs = new ArrayList<>();

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

        public Point getParent() {
            return parent;
        }

        public void setParent(Point parent) {
            this.parent = parent;
        }

        public Point getChild(int i) {
            return childs.get(i);
        }

        public void setChild(Point child) {
            childs.add(child);
        }
    }


}