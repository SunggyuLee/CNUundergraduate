public class DFS {

    // u's ASCII = 117
    final static int uASCII = 117;
    final static int[][] sampleMatrix = {
            // u, v, w, x, y, z => 0, 1, 2, 3, 4, 5
            { 0, 1, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 1, 1 },
            { 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 1 },
    };
    static int TIME;
    static Vertex[] Vertexes = new Vertex[sampleMatrix.length];

    public static void main(String[] args) {

        TIME = 0;
        init();
        for(int i = 0; i < sampleMatrix.length; i++){
            if(Vertexes[i].getColor() == Color.WHITE)
                DFS(Vertexes[i]);
        }


        for(int i = 0; i < sampleMatrix.length; i++){
            if(Vertexes[i].parent == null)
                System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 는 시작점");
            else
                System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 의 부모 정점 : " + Vertexes[i].getParent().getAlphabet());
            System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 의  초기발견 시간 : " + Vertexes[i].getD());
            System.out.println("정점 [" + Vertexes[i].getAlphabet() + "] 의  검색종료 시간 : " + Vertexes[i].getF());
            System.out.println();
        }

    }

    private static void DFS(Vertex u){
        TIME++;
        u.setD(TIME);
        u.setColor(Color.GRAY);
        for(int i = 0; i < sampleMatrix.length; i++){
            if(sampleMatrix[u.getAlphabet()-uASCII][i] == 1 && Vertexes[i].getColor() == Color.WHITE){
                Vertexes[i].parent = u;
                DFS(Vertexes[i]);
            }
        }
        u.setColor(Color.BLACK);
        TIME++;
        u.setF(TIME);
    }

    private static void init() {

        for(int i = 0; i < sampleMatrix.length; i++){
            Vertexes[i] = new Vertex((char)(i + uASCII));
        }

    }

    public enum Color {
        WHITE, GRAY, BLACK
    }

    public static class Vertex {
        private Color color;
        private char alphabet;
        private Vertex parent;
        private int d;
        private int f;

        public Vertex(char c) {
            this.color = Color.WHITE;
            this.alphabet = c;
            this.parent = null;

        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public char getAlphabet() {
            return alphabet;
        }

        public void setAlphabet(char alphabet) {
            this.alphabet = alphabet;
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }
    }
}
