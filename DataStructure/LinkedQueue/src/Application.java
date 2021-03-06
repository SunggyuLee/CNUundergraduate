import java.util.Scanner;

public class Application {

	private CircularLinkedQueue<Character> _linkedQueue;
	private int _inputChars; // 입력된 문자의 개수
	private int _ignoredChars; // 무시된 문자의 개수
	private int _insertedChars; // 삽입된 문자의 개수

	public Application() {
		this._ignoredChars = 0;
		this._inputChars = 0;
		this._insertedChars = 0;
	}

	public void showAllFromFront() {

		System.out.print("[Queue] <Front> ");
		for (int i = 0; i < this._linkedQueue.size(); i++)
			System.out.print(this._linkedQueue.findElementByOrder(i) + " ");
		System.out.println("<Rear>");
	}


	public void showFrontElement() {
		System.out
				.println("[Front] 맨 앞 원소는 " + this._linkedQueue.frontElement() + " 입니다.");
	}

	public void showQueueLength() {
		System.out.println("[Length] 큐에는 현재 " + this._linkedQueue.size()
				+ "개의 원소가 있습니다.");
	}

	public void initCharCounts() {
		this._ignoredChars = 0;
		this._inputChars = 0;
		this._insertedChars = 0;
	}

	public void countInserted() {
		this._insertedChars++;
	}

	public void countIgnored() {
		this._ignoredChars++;
	}

	public void countInputChar() {
		this._inputChars++;
	}

	@SuppressWarnings("unchecked")
	public void insert(char c) {
		if (this._linkedQueue.isFull())
			System.out.println("[Full] 큐이 꽉 차서 원소 " + c + "은(는) 삽입이 불가능합니다.");
		else {
			this._linkedQueue.enQueue(c);
			System.out.println("[EnQueue] 삽입된 원소는 " + c + "입니다");
			this.countInserted();
		}
	}

	public void deleteOne() {
		if (this._linkedQueue.isEmpty())
			System.out.println("[Empty] 큐에 삭제할 원소가 없습니다.");
		else
			System.out.println("[DeQueue] 삭제된 원소는 " + this._linkedQueue.deQueue()
					+ " 입니다.");
	}

	public void deleteN(int numOfCharsToBeDeleted) {
		System.out.println("[DeleteN] "+numOfCharsToBeDeleted + "개의 원소를 삭제하려고 합니다.");
		for (int i = 0; i < numOfCharsToBeDeleted; i++) {
			this.deleteOne();
			if (this._linkedQueue.isEmpty()) {
				System.out.println("[Empty] 큐에 삭제할 원소가 없습니다.");
				break;
			}
		}
	}

	public void conclusion() {
		System.out.println();
		for (int i = 0; i < this._linkedQueue.size(); i++)
			this.deleteOne();
		System.out.println();
		System.out.println("입력된 문자는 모두 " + this._inputChars + "개 입니다.");
		System.out.println("정상 처리된 문자는 "
				+ (this._inputChars - this._ignoredChars) + "개 입니다.");
		System.out.println("무시한 문자는 " + this._ignoredChars + "개 입니다.");
		System.out.println("삽입된 문자는 " + this._insertedChars + "개 입니다");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		this._linkedQueue = new CircularLinkedQueue<Character>();
		char input;

		System.out.print("> 문자를 입력하세요: ");
		while (scan.hasNext()) {
			input = scan.nextLine().charAt(0);
			this.countInputChar();
			if ((input >= 'A' && input <= 'Z')
					|| (input >= 'a' && input <= 'z'))
				this.insert(input);
			else if (input >= '0' && input <= '9')
				this.deleteN(Integer.parseInt(String.valueOf(input)));
			else if (input == '-')
				this.deleteOne();
			else if (input == '#')
				this.showQueueLength();
			else if (input == '/')
				this.showAllFromFront();
			else if (input == '^')
				this.showFrontElement();
			else {
				System.out.println("[Ignore] 의미 없는 문자가 입력되었습니다.");
				this.countIgnored();
			}
			System.out.print("> 문자를 입력하세요: ");
		}
		this.conclusion();
		System.out.println("> 프로그램을 종료합니다.");
	}
}