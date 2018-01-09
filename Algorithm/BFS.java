import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    // r's ASCII = 114
    final static int rASCII = 114;
    final static int[][] sampleMatrix = {
            // r, s, t, u, v, w, x, y => 0, 1, 2, 3, 4, 5, 6, 7
            { 0, 1, 0, 0, 1, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 1, 0, 1, 1, 0 },
            { 0, 0, 1, 0, 0, 0, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 0, 0, 0, 1, 0 },
            { 0, 0, 1, 0, 0, 1, 0, 1 },
            { 0, 0, 0, 1, 0, 0, 1, 0 }
    };
    static Vertex[] Vertexes = new Vertex[sampleMatrix.length];

    public static void main(String[] args) {

        init();
        BFS(Vertexes[(int)'s'-rASCII]);

        for(int i = 0; i < sampleMatrix.length; i++){
            if(Vertexes[i].parent == null)
                System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 는 시작점");
            else
                System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 의 부모 정점 : " + Vertexes[i].getParent().getAlphabet());
            System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 의 비용 : " + Vertexes[i].getCost());
            System.out.println();
        }
    }

    private static void BFS(Vertex s) {
        s.setColor(Color.GRAY);
        s.setCost(0);

        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(Vertexes['s'-rASCII]);
        while(!queue.isEmpty()){
            Vertex u = queue.poll();
            for(int i = 0; i < sampleMatrix.length; i++){
                // i는 그 알파벳-rASCII를 의미
                if(sampleMatrix[u.getAlphabet()-rASCII][i] == 1 && Vertexes[i].getColor() == Color.WHITE){
                    Vertexes[i].setColor(Color.GRAY);
                    Vertexes[i].setCost(u.getCost() + 1);
                    Vertexes[i].setParent(u);
                    queue.offer(Vertexes[i]);
                }
            }
            u.setColor(Color.BLACK);
        }
    }

    private static void init() {

        for(int i = 0; i < sampleMatrix.length; i++){
            Vertexes[i] = new Vertex((char)(i + rASCII));
        }

    }


    public enum Color {
        WHITE, GRAY, BLACK
    }

    public static class Vertex {
        private Color color;
        private char alphabet;
        private int cost;
        private Vertex parent;

        public Vertex(char c) {
            this.color = Color.WHITE;
            this.alphabet = c;
            this.cost  = Integer.MAX_VALUE;
            this.parent = null;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public char getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(char alphabet) {
            this.alphabet = alphabet;
        }
    }

}
