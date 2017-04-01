import java.util.Random;
import java.util.Scanner;

public class Application {

	HeapPriorityQueue<Integer> _priorityQueue;
//	 SortedArrayPriorityQueue<Integer> _priorityQueue;
//	 SortedLinkedPriorityQueue<Integer> _priorityQueue;

	public Application() {	//Application�� ������
		this._priorityQueue = new HeapPriorityQueue();
//		 this._priorityQueue = new SortedArrayPriorityQueue();
//		 this._priorityQueue = new SortedLinkedPriorityQueue();
	}

	public void showAll() {
		HeapPriorityQueue.Iterator it = (HeapPriorityQueue.Iterator) this._priorityQueue
				.iterator();
//		 SortedArrayPriorityQueue.Iterator it =
//		 (SortedArrayPriorityQueue.Iterator) this._priorityQueue.iterator();
//		 SortedLinkedPriorityQueue.Iterator it =
//		 (SortedLinkedPriorityQueue.Iterator) this._priorityQueue.iterator(); // �� Ŭ������ innerClass �ݺ��� �̿�
		System.out.print("[List] ");
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		// �ݺ��ڸ� �̿��� ����ڴ� �ε������� ���� ���ص� ��� ������ ��°���
	}

	public void add(int n) {
		if (this._priorityQueue.isFull())
			System.out.println("!����: Priority Queue�� ������ �Է��� �� �����ϴ�.");
		else {
			if (this._priorityQueue.add(n))	
				System.out.println("-���������� �ԷµǾ����ϴ�.");
			else
				System.out.println("!����: �Է� ����");
		}
	}

	public void removeMax() {
		if (this._priorityQueue.isEmpty())
			System.out.println("[Empty] ������ ���Ұ� �����ϴ�.");
		else {
			Integer n = (Integer) this._priorityQueue.removeMax();	
			System.out.println("[Delete] ������ ���Ҵ� " + n + " �Դϴ�.");
		}
	}

	private void showLength() {
		// TODO Auto-generated method stub
		System.out.println("- PriorityQueue ���� " + this._priorityQueue.size()
				+ "���� ���Ұ� ����ֽ��ϴ�.");
	}

	private void max() {
		// TODO Auto-generated method stub
		System.out.println("- PriorityQueue �� �ִ��� "
				+ this._priorityQueue.max() + "�Դϴ�.");
	}

	private void removeAll() {
		// TODO Auto-generated method stub
		System.out.print("-������ ���ҵ� : ");
		while (!this._priorityQueue.isEmpty()) {
			if (this._priorityQueue.isEmpty()) {
				System.out.println("- PriorityQueue �� ������ϴ�.");
				break;
			}
			System.out.print(this._priorityQueue.removeMax() + " ");
		}
			System.out.println();
			if(this._priorityQueue.isEmpty())
				System.out.println("- PriorityQueue �� ������ϴ�.");
	}

	void run() {
		Random random = new Random();
		Scanner scan = new Scanner(System.in);
		this._priorityQueue = new HeapPriorityQueue<Integer>();
//		 this._priorityQueue = new SortedArrayPriorityQueue<Integer>();
//		 this._priorityQueue = new SortedLinkedPriorityQueue<Integer>();	// Queue ��ü ����
		char command;	 // Character���� ���� ���� 
		String input;	// �Է��� �������� ���� ����

		System.out.println("<Max Priority Queue>");
		System.out
				.print("[���� �� �ؾ� �� ���� �ڵ带 �����Ͻÿ�]\ni : �Է�\nm : �ִ� ����\nd : �ִ� ����\nv : Priority Queue ���� ����\nx : ��� ���� ���ʴ�� �����Ͽ� ���\nr : ������ �����Ͽ� 10�� �Է�\nn : ������ ���� ����\nq : ���α׷� ����\n");
		System.out.print("? �ؾ� �� ���� �ڵ带 ġ�ÿ� ");
		while (scan.hasNext()) {
			command = scan.nextLine().charAt(0); 	// �Էµ� String �� �� ���� ���� char���� �޾� command�� ����
			if (command == 'i') {
				System.out.print("-������ �������� �Է��Ͻÿ�: ");
				input = scan.nextLine();	// String ���� �Է¹޴´�
				this.add(Integer.parseInt(input));	// �Է¹��� String ���� int������ �ٲ� �� addȣ��
			} else if (command == 'r'){
				int i;
				for (i = 0; i < 10; i++){
					if(this._priorityQueue.isFull()){
						System.out.println("!����: Priority Queue�� ������ "+i+"�� �� �ԷµǾ����ϴ�.");
						break;
					}
					else
						this._priorityQueue.add(random.nextInt(100));	// 0���� ũ�ų����� 100���� ���� ������ ����
				}
			if(i == 10)	
				System.out.println("- ������ ���Ұ� 10�� �ԷµǾ����ϴ�.");	// 10���� ���� ��� ���� ��� ���
			}
			else if (command == 'm')
				this.max();
			else if (command == 'd')
				this.removeMax();
			else if (command == 'n')
				this.showLength();
			else if (command == 'x') {
				this.removeAll();
			} else if (command == 'v')
				this.showAll();
			else if (command == 'q')
				break;
			else
				System.out.println("[Ignore] �ǹ� ���� ���ڰ� �ԷµǾ����ϴ�.");
			System.out.print("[���� �� �ؾ� �� ���� �ڵ带 �����Ͻÿ�]");
		}
		System.out.println("\n<���α׷��� �����մϴ�>");
	}

}
