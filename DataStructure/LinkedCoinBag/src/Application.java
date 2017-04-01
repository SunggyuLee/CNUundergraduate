import java.util.Scanner;

public class Application {

	static LinkedBag _coinCollector;

	public void showNumOfCoins() {
		System.out.println("�� ������ " + this._coinCollector.size() + "�� �Դϴ�");
	}

	public void showCoinsCounts(Coin givenCoin) {
		System.out.println(givenCoin.value() + "������ "
				+ this._coinCollector.frequencyOf(givenCoin) + "�� �����մϴ�");
	}

	public void showMaxCoinValues() {
		System.out.println("���� ū ������ "
				+ this._coinCollector.maxValueCoin().value() + "�Դϴ�");
	}

	public void showSumCoinValues() {
		System.out.println("��ü �� ������ ���� " + this._coinCollector.sumCoinValues()
				+ "�Դϴ�");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);

		int input = 0;
		System.out.print("���濡 �� �� ���� ������ �Է��Ͻÿ� : ");
		input = scan.nextInt();
		this._coinCollector = new LinkedBag();
		System.out.print("�޴��� �����Ͻÿ� [1:�Է� 2:���� 3:��� 4:�˻� 5:���������� ���� 9:����]:");
		while (input != 9) {
			input = scan.nextInt();
			if (input == 1) {
				System.out.print("- [�Է�] ������ �׼��� �Է��Ͻÿ� : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this._coinCollector.add(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" ���� �ð� = " + time + " nano sec");
			} else if (input == 2) {
				System.out.print("- [����] ������ �׼��� �Է��ϼ��� : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this._coinCollector.remove(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" ���� �ð� = " + time + " nano sec");
			} else if (input == 3) {
				long start = System.nanoTime();
				System.out.println("- [���]");
				System.out.println("�� ������ " + this._coinCollector.size()
						+ "�� �Դϴ�");
				System.out.println("���� ū ������ "
						+ this._coinCollector.maxValueCoin().value() + "�Դϴ�");
				System.out.println("��ü �� ������ ���� "
						+ this._coinCollector.sumCoinValues() + "�Դϴ�");
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" ���� �ð� = " + time + " nano sec");
			} else if (input == 4) {
				System.out.print("- [�˻�] ������ �׼��� �Է��ϼ��� : ");
				input = scan.nextInt();
				Coin anCoin = new Coin(input);
				long start = System.nanoTime();
				this.showCoinsCounts(anCoin);
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" ���� �ð� = " + time + " nano sec");
			} else if (input == 5) {
				long start = System.nanoTime();
				this._coinCollector.removeAny();
				long end = System.nanoTime();
				double time = (double) (end - start);
				System.out.println(" ���� �ð� = " + time + " nano sec");
			}else if (input == 9) {
				this.showNumOfCoins();
				this.showMaxCoinValues();
				this.showSumCoinValues();
				break;
			} else {
				System.out.println("[����]�߸��� �Է��Դϴ�.");
			}
			System.out.print("�޴��� �����Ͻÿ� [1:�Է� 2:���� 3:��� 4:�˻� 5:���������� ���� 9:����]:");

		}
	}
}