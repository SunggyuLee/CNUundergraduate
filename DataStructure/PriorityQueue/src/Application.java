import java.util.Random;
import java.util.Scanner;

public class Application {

	HeapPriorityQueue<Integer> _priorityQueue;
//	 SortedArrayPriorityQueue<Integer> _priorityQueue;
//	 SortedLinkedPriorityQueue<Integer> _priorityQueue;

	public Application() {	//Application의 생성자
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
//		 (SortedLinkedPriorityQueue.Iterator) this._priorityQueue.iterator(); // 각 클래스의 innerClass 반복자 이용
		System.out.print("[List] ");
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		// 반복자를 이용해 사용자는 인덱스값을 알지 못해도 모든 정보를 출력가능
	}

	public void add(int n) {
		if (this._priorityQueue.isFull())
			System.out.println("!오류: Priority Queue가 꽉차서 입력할 수 없습니다.");
		else {
			if (this._priorityQueue.add(n))	
				System.out.println("-정상적으로 입력되었습니다.");
			else
				System.out.println("!오류: 입력 실패");
		}
	}

	public void removeMax() {
		if (this._priorityQueue.isEmpty())
			System.out.println("[Empty] 삭제할 원소가 없습니다.");
		else {
			Integer n = (Integer) this._priorityQueue.removeMax();	
			System.out.println("[Delete] 삭제된 원소는 " + n + " 입니다.");
		}
	}

	private void showLength() {
		// TODO Auto-generated method stub
		System.out.println("- PriorityQueue 에는 " + this._priorityQueue.size()
				+ "개의 원소가 들어있습니다.");
	}

	private void max() {
		// TODO Auto-generated method stub
		System.out.println("- PriorityQueue 의 최댓값은 "
				+ this._priorityQueue.max() + "입니다.");
	}

	private void removeAll() {
		// TODO Auto-generated method stub
		System.out.print("-삭제된 원소들 : ");
		while (!this._priorityQueue.isEmpty()) {
			if (this._priorityQueue.isEmpty()) {
				System.out.println("- PriorityQueue 는 비었습니다.");
				break;
			}
			System.out.print(this._priorityQueue.removeMax() + " ");
		}
			System.out.println();
			if(this._priorityQueue.isEmpty())
				System.out.println("- PriorityQueue 는 비었습니다.");
	}

	void run() {
		Random random = new Random();
		Scanner scan = new Scanner(System.in);
		this._priorityQueue = new HeapPriorityQueue<Integer>();
//		 this._priorityQueue = new SortedArrayPriorityQueue<Integer>();
//		 this._priorityQueue = new SortedLinkedPriorityQueue<Integer>();	// Queue 객체 생성
		char command;	 // Character값을 받을 변수 
		String input;	// 입력할 정수값을 받을 변수

		System.out.println("<Max Priority Queue>");
		System.out
				.print("[다음 중 해야 할 일의 코드를 선택하시오]\ni : 입력\nm : 최댓값 보기\nd : 최댓값 보기\nv : Priority Queue 내용 보기\nx : 모든 원소 차례대로 삭제하여 출력\nr : 난수를 생성하여 10개 입력\nn : 원소의 개수 보기\nq : 프로그램 종료\n");
		System.out.print("? 해야 할 일의 코드를 치시오 ");
		while (scan.hasNext()) {
			command = scan.nextLine().charAt(0); 	// 입력된 String 값 중 가장 앞의 char값만 받아 command에 저장
			if (command == 'i') {
				System.out.print("-삽입할 정수값을 입력하시오: ");
				input = scan.nextLine();	// String 값을 입력받는다
				this.add(Integer.parseInt(input));	// 입력받은 String 값을 int형으로 바꾼 후 add호출
			} else if (command == 'r'){
				int i;
				for (i = 0; i < 10; i++){
					if(this._priorityQueue.isFull()){
						System.out.println("!오류: Priority Queue가 꽉차서 "+i+"개 만 입력되었습니다.");
						break;
					}
					else
						this._priorityQueue.add(random.nextInt(100));	// 0보다 크거나같고 100보다 작은 난수를 생성
				}
			if(i == 10)	
				System.out.println("- 임의의 원소가 10개 입력되었습니다.");	// 10개의 값이 모두 들어갔을 경우 출력
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
				System.out.println("[Ignore] 의미 없는 문자가 입력되었습니다.");
			System.out.print("[다음 중 해야 할 일의 코드를 선택하시오]");
		}
		System.out.println("\n<프로그램을 종료합니다>");
	}

}
