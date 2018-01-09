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
        System.out.println("MaxHeap을 만들기 전의 데이터 : ");
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
            System.out.println("1. 데이터 추가 2. 최대값 3. 최대 우선순위 작업 처리 \n" +
                    "4. 원소 키값 증가        5. 작업제거        6. 종료");
            System.out.println("--------------------------------------------------");
            int num = scan.nextInt();

            switch (num){
                case 1:
                    System.out.println("데이터명을 입력하세요 :");
                    String name = scan.next();
                    System.out.println("우선순위를 입력하세요 :");
                    int priority = scan.nextInt();
                    Node temp = new Node(priority, name);
                    insert(array, temp);
                    System.out.println("추가 되었습니다.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 2:
                    System.out.println("최대값 :");
                    System.out.println(max(array).getPriority() + ", " + max(array).getName());
                    System.out.println("--------------------------------------------------");
                    break;
                case 3:
                    extractMax(array);
                    System.out.println("최대 우선순위 작업을 처리했습니다.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 4:
                    System.out.println("키값을 증가할 노드 번호를 입력하세요 :");
                    int index1 = scan.nextInt();
                    System.out.println("증가될 키값을 입력하세요 :");
                    int key = scan.nextInt();
                    if(!increaseKey(array, index1, key))
                        System.out.println("키값은 기존의 값보다 커야합니다. 다시 시도해주세요.");
                    else
                        System.out.println("윈소의 키값이 정상적으로 처리되었습니다");
                    System.out.println("--------------------------------------------------");
                    break;
                case 5:
                    System.out.println("제거할 노드 번호를 입력하세요 :");
                    int index2 = scan.nextInt();
                    Node removedNode = delete(array, index2);
                    System.out.println(removedNode.getName() + "이(가) 제거되었습니다.");
                    System.out.println("--------------------------------------------------");
                    break;
                case 6:
                    flag = false;
                    System.out.println("프로그램이 종료됩니다.");
                    break;
                    default:
                        System.out.println("다시 시도해주세요. (1-6 사이의 숫자를 입력해야 합니다)");
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
