import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Huffman {

    public static void main(String[] args) throws IOException{

        //        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("data06_huffman.txt"));

        int[] priority = new int[30];
        int resetNum = 97;
        String s = null;
        ArrayList<Node> array = new ArrayList<>();

        while (reader.ready()) {
            s = reader.readLine();
            char[] temp = s.toCharArray();

            for(int i = 0; i < temp.length; i++) {
                priority[temp[i] - resetNum]++;
            }

            array.add(null);
            for(int i = 0; i < priority.length; i++){
                if(priority[i] != 0){
                    Node tempNode = new Node();
                    tempNode.setAlphbet((char)(i + resetNum));
                    tempNode.setPriority(priority[i]);
                    array.add(tempNode);
                }
            }

        }

        buildMinHeap(array);
        Node huffmanNode = huffman(array);


        System.out.println("Input data : ");
        System.out.println(s+"\n");


        System.out.println("Huffman Code : ");

        huffmanPrint(huffmanNode, "","");

    }

    private static void huffmanPrint(Node huffmanNode, String code, String s) {
        code += s;

        if(huffmanNode.left == null || huffmanNode.right == null){
            System.out.println(huffmanNode.getAlphbet() + ", " + code);
        }else {
            huffmanPrint(huffmanNode.left, code, "0");
            huffmanPrint(huffmanNode.right, code, "1");
        }
    }

    private static Node huffman(ArrayList<Node> array) {

        while(array.size() > 2){
            Node tempNode = new Node();
            tempNode.setLeft(extractMin(array));
            tempNode.setRight(extractMin(array));
            tempNode.setPriority(tempNode.getLeft().getPriority() + tempNode.getRight().getPriority());
            insert(array, tempNode);
        }

        return extractMin(array);
    }

    private static void insert(ArrayList<Node> array, Node node) {
        array.add(node);
        buildMinHeap(array);
    }

    private static Node extractMin(ArrayList<Node> array) {
        Node temp = array.remove(1);
        buildMinHeap(array);

        return temp;
    }

    private static void buildMinHeap(ArrayList<Node> array) {
        int heap_size = array.size();

        for(int i = heap_size/2; i >= 1; i--){
            minHeapify(array, i);
        }
    }

    private static void minHeapify(ArrayList<Node> array, int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest;

        if(left < array.size() && array.get(left).getPriority() < array.get(i).getPriority())
            smallest = left;
        else
            smallest = i;

        if(right < array.size() && array.get(right).getPriority() < array.get(smallest).getPriority())
            smallest = right;

        if (smallest != i){
            Node temp = array.get(i);
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


    private static  class Node {
        int priority = 0;
        Node left;
        Node right;
        char alphbet = '\0';


        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }


        public char getAlphbet() {
            return alphbet;
        }

        public void setAlphbet(char alphbet) {
            this.alphbet = alphbet;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
