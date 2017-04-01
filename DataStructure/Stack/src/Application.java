import java.util.Scanner;

public class Application {

	private ArrayStack _arrayStack;
	private int _inputChars; // �Էµ� ������ ����
	private int _ignoredChars; // ���õ� ������ ����
	private int _insertedChars; // ���Ե� ������ ����

	public Application() {
		this._ignoredChars = 0;
		this._inputChars = 0;
		this._insertedChars = 0;
	}

	public void showAllFromBottom() {

		System.out.print("[Stack] <Bottom> ");
		for (int i = 0; i < this._arrayStack.length(); i++)
			System.out.print(this._arrayStack.findElementByOrder(i) + " ");
		System.out.println("<top>");
	}

	public void showAllFromTop() {

		System.out.print("[Stack] <top> ");
		for (int i = this._arrayStack.length() - 1; i >= 0; i--)
			System.out.print(this._arrayStack.findElementByOrder(i) + " ");
		System.out.println("<Bottom>");
	}

	public void showTopElement() {
		System.out
				.println("[Top] Top ���Ҵ� " + this._arrayStack.peek() + " �Դϴ�.");
	}

	public void showStackLength() {
		System.out.println("[Length] ���ÿ��� ���� " + this._arrayStack.length()
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
		if (this._arrayStack.isFull())
			System.out.println("[Full] ������ �� ���� ���� " + c + "��(��) ������ �Ұ����մϴ�.");
		else {
			this._arrayStack.push(c);
			System.out.println("[Push] ���Ե� ���Ҵ� " + c + "�Դϴ�");
			this.countInserted();
		}
	}

	public void deleteOne() {
		if (this._arrayStack.isEmpty())
			System.out.println("[Empty] ���ÿ� ������ ���Ұ� �����ϴ�.");
		else
			System.out.println("[Pop] ������ ���Ҵ� " + this._arrayStack.pop()
					+ " �Դϴ�.");
	}

	public void deleteN(int numOfCharsToBeDeleted) {

		for (int i = 0; i < numOfCharsToBeDeleted; i++) {
			this.deleteOne();
			if (this._arrayStack.isEmpty()) {
				System.out.println("[Empty] ���ÿ� ������ ���Ұ� �����ϴ�.");
				break;
			}
		}
	}

	public void conclusion() {
		for (int i = 0; i < this._arrayStack.length(); i++)
			this.deleteOne();
		System.out.println("�Էµ� ���ڴ� ��� " + this._inputChars + "�� �Դϴ�.");
		System.out.println("���� ó���� ���ڴ� "
				+ (this._inputChars - this._ignoredChars) + "�� �Դϴ�.");
		System.out.println("������ ���ڴ� " + this._ignoredChars + "�� �Դϴ�.");
		System.out.println("���Ե� ���ڴ� " + this._insertedChars + "�� �Դϴ�");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		this._arrayStack = new ArrayStack<Character>();
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
				this.showStackLength();
			else if (input == '/')
				this.showAllFromBottom();
			else if (input == '\\')
				this.showAllFromTop();
			else if (input == '^')
				this.showTopElement();
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
