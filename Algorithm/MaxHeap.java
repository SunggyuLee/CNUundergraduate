import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MaxHeap {

    public static void main(String[] args) throws IOException {
        boolean flag = true;

        BufferedReader reader = new BufferedReader(new FileReader("data_heap.txt"));

        ArrayList<Node> array = new ArrayList<>();
        array.add(null);

        while (reader.ready()) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), ",");

            while(st.hasMoreTokens()){
                Node temp = new Node();
                temp.setPriority(Integer.parseInt(st.nextToken()));
                temp.setName(st.nextToken().trim());

                array.add(temp);
            }
        }
        System.out.println("MaxHeap�� ����� ���� ������ : ");
        for(int i = 1; i < array.size(); i++) {
            System.out.println(array.get(i).getPriority() + ", " + array.get(i).getName());
        }
        System.out.println("--------------------------------------------------");
        buildMaxHeap(array);
        System.out.println("MaxHeap : ");
        for(int i = 1; i < array.size(); i++) {
            System.out.println(array.get(i).getPriority() + ", " + array.get(i).getName());
        }
        System.out.println("--------------------------------------------------");

        Scanner scan = new Scanner(System.in);
        while(flag){
            System.out.println("1. ������ �߰� 2. �ִ밪 3. �ִ� �켱���� �۾� ó�� \n" +
                    "4. ���� Ű�� ����        5. �۾�����        6. ����");
            System.out.println("--------------------------------------------------");
            int num = scan.nextInt();

            switch (num){
                case 1:
                    System.out.println("�����͸��� �Է��ϼ��� :");
                    String name = scan.next();
                    System.out.println("�켱������ �Է��ϼ��� :");
                    int priority = scan.nextInt();
                    Node temp = new Node(priority, name);
                    insert(array, temp);
                    System.out.println("�߰� �Ǿ����ϴ�.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 2:
                    System.out.println("�ִ밪 :");
                    System.out.println(max(array).getPriority() + ", " + max(array).getName());
                    System.out.println("--------------------------------------------------");
                    break;
                case 3:
                    extractMax(array);
                    System.out.println("�ִ� �켱���� �۾��� ó���߽��ϴ�.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 4:
                    System.out.println("Ű���� ������ ��� ��ȣ�� �Է��ϼ��� :");
                    int index1 = scan.nextInt();
                    System.out.println("������ Ű���� �Է��ϼ��� :");
                    int key = scan.nextInt();
                    if(!increaseKey(array, index1, key))
                        System.out.println("Ű���� ������ ������ Ŀ���մϴ�. �ٽ� �õ����ּ���.");
                    else
                        System.out.println("������ Ű���� ���������� ó���Ǿ����ϴ�");
                    System.out.println("--------------------------------------------------");
                    break;
                case 5:
                    System.out.println("������ ��� ��ȣ�� �Է��ϼ��� :");
                    int index2 = scan.nextInt();
                    Node removedNode = delete(array, index2);
                    System.out.println(removedNode.getName() + "��(��) ���ŵǾ����ϴ�.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 6:
                    flag = false;
                    System.out.println("���α׷��� ����˴ϴ�.");
                    break;
                    default:
                        System.out.println("�ٽ� �õ����ּ���. (1-6 ������ ���ڸ� �Է��ؾ� �մϴ�)");
            }
            for(int i = 1; i < array.size(); i++) {
                System.out.println(array.get(i).getPriority() + ", " + array.get(i).getName());
            }
            System.out.println("--------------------------------------------------");
        }
   }

    private static void buildMaxHeap(ArrayList<Node> array) {
        int heap_size = array.size();

        for(int i = heap_size/2; i >= 1; i--){
            maxHeapify(array, i);
        }
    }

    private static void maxHeapify(ArrayList<Node> array, int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest;

        if(left < array.size() && array.get(left).getPriority() > array.get(i).getPriority())
            largest = left;
        else
            largest = i;

        if(right < array.size() && array.get(right).getPriority() > array.get(largest).getPriority())
            largest = right;

        if (largest != i){
            Node temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);
            maxHeapify(array, largest);
        }
    }

    private static void insert(ArrayList<Node> array, Node node){
        array.add(node);
        buildMaxHeap(array);
    }

    private static Node max(ArrayList<Node> array){
        return array.get(1);
    }


    private static boolean increaseKey(ArrayList<Node> array, int index, int key){
        if(array.get(index).getPriority() >= key)
            return false;
        else{
            array.get(index).setPriority(key);
            buildMaxHeap(array);
            System.out.println("--------------------------------------------------");
            return true;
        }
    }

    private static Node delete(ArrayList<Node> array, int index){
        Node temp = array.remove(index);
        buildMaxHeap(array);
        return temp;
    }

    private static Node extractMax(ArrayList<Node> array){
        Node temp = delete(array, 1);
        return temp;
    }


    private static int parent(int i){
        return i/2;
    }

    private static int leftChild(int i){
        return 2*i;
    }

    private static int rightChild(int i){
        return 2*i+1;
    }

    private static class Node {

        int priority = 0;
        String name = null;

        public Node(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        public Node() {

        }


        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
