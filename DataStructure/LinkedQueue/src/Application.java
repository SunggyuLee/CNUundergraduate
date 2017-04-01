import java.util.Scanner;

public class Application {

	private CircularLinkedQueue<Character> _linkedQueue;
	private int _inputChars; // �Էµ� ������ ����
	private int _ignoredChars; // ���õ� ������ ����
	private int _insertedChars; // ���Ե� ������ ����

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
				.println("[Front] �� �� ���Ҵ� " + this._linkedQueue.frontElement() + " �Դϴ�.");
	}

	public void showQueueLength() {
		System.out.println("[Length] ť���� ���� " + this._linkedQueue.size()
				+ "���� ���Ұ� �ֽ��ϴ�.");
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
			System.out.println("[Full] ť�� �� ���� ���� " + c + "��(��) ������ �Ұ����մϴ�.");
		else {
			this._linkedQueue.enQueue(c);
			System.out.println("[EnQueue] ���Ե� ���Ҵ� " + c + "�Դϴ�");
			this.countInserted();
		}
	}

	public void deleteOne() {
		if (this._linkedQueue.isEmpty())
			System.out.println("[Empty] ť�� ������ ���Ұ� �����ϴ�.");
		else
			System.out.println("[DeQueue] ������ ���Ҵ� " + this._linkedQueue.deQueue()
					+ " �Դϴ�.");
	}

	public void deleteN(int numOfCharsToBeDeleted) {
		System.out.println("[DeleteN] "+numOfCharsToBeDeleted + "���� ���Ҹ� �����Ϸ��� �մϴ�.");
		for (int i = 0; i < numOfCharsToBeDeleted; i++) {
			this.deleteOne();
			if (this._linkedQueue.isEmpty()) {
				System.out.println("[Empty] ť�� ������ ���Ұ� �����ϴ�.");
				break;
			}
		}
	}

	public void conclusion() {
		System.out.println();
		for (int i = 0; i < this._linkedQueue.size(); i++)
			this.deleteOne();
		System.out.println();
		System.out.println("�Էµ� ���ڴ� ��� " + this._inputChars + "�� �Դϴ�.");
		System.out.println("���� ó���� ���ڴ� "
				+ (this._inputChars - this._ignoredChars) + "�� �Դϴ�.");
		System.out.println("������ ���ڴ� " + this._ignoredChars + "�� �Դϴ�.");
		System.out.println("���Ե� ���ڴ� " + this._insertedChars + "�� �Դϴ�");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		this._linkedQueue = new CircularLinkedQueue<Character>();
		char input;

		System.out.print("> ���ڸ� �Է��ϼ���: ");
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
				System.out.println("[Ignore] �ǹ� ���� ���ڰ� �ԷµǾ����ϴ�.");
				this.countIgnored();
			}
			System.out.print("> ���ڸ� �Է��ϼ���: ");
		}
		this.conclusion();
		System.out.println("> ���α׷��� �����մϴ�.");
	}
}