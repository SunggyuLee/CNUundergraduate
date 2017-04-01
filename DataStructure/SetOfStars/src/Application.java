import java.util.Scanner;

public class Application {
	private ArraySet _starCollector;

	public void showNumOfStars() {
		System.out.println("���� ���� �ִ� ���� ������ " + this._starCollector.size()
				+ "�Դϴ�");
	}

	public void showExistence(Star givenStar) {
		// �˻� �� ����ϴ� �Լ�
		if (this._starCollector.doesContain(givenStar)) {
			if (givenStar.starName() != null)
				System.out.println(givenStar.starName() + "���� �����մϴ�");
			else
				System.out.println(givenStar.xCoordinate() + ", "
						+ givenStar.yCoordinate() + " ��ġ�� ���� �����մϴ�");
		} else
			System.out.println("���� �������� �ʽ��ϴ�");
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		this._starCollector = new ArraySet();
		int xCoordinate = 0;
		int yCoordinate = 0;
		String starName = null;
		int command = 0;
		while (command != 9) {
			try {
				System.out.print("[1:�Է� 2:�־��� �� ���� 3:������ �� ���� 4:��� 5:�̸����� �˻� 6:��ǥ�� �˻� 9:����]:");
				command = Integer.parseInt(scan.nextLine());
				if (command == 1) {
					System.out.println("- [�Է�] - ");
					System.out.print("- x��ǥ�� �Է��Ͻÿ� : ");
					xCoordinate = Integer.parseInt(scan.nextLine());
					System.out.print("- y��ǥ�� �Է��Ͻÿ� : ");
					yCoordinate = Integer.parseInt(scan.nextLine()); 
//nextInt�� �ϸ� ���⼭ ������ ��������� ���Ͱ��� �������� �Ѿ�� �Է����� �ʰ� �Ѿ������ �̷��� ����ȵ�
//���ڸ� �Է��ϴ� ���� �������� �����Էµ��� ���ڸ� �Է��ϴ� ���¶�� nextInt���Ұ�
					System.out.print("- ���� �̸��� �Է��Ͻÿ� : ");
					starName = scan.nextLine();
					Star aStar = new Star(xCoordinate, yCoordinate, starName);
					this._starCollector.add(aStar);
				} else if (command == 2) {
					System.out.print("-[����] ���� �̸��� �Է��Ͻÿ� : ");
					starName = scan.nextLine();
					Star aStar = new Star(starName);
					this._starCollector.remove(aStar);
				} else if (command == 3) {
					System.out.println("-[����] ������ �� ����");
					this._starCollector.removeAny();
				} else if (command == 4) {
					System.out.println(this._starCollector.size()
							+ "���� ���� �����մϴ�");
				} else if (command == 5) {
					System.out.println("-[�˻�] ���� �̸��� �Է��ϼ���");
					starName = scan.nextLine();
					Star aStar = new Star(starName);
					this.showExistence(aStar);
				} else if (command == 6) {
					System.out.println("-[�˻�]-");
					System.out.print("- x��ǥ�� �Է��Ͻÿ� : ");
					xCoordinate = Integer.parseInt(scan.nextLine());
					System.out.print("- y��ǥ�� �Է��Ͻÿ� : ");
					yCoordinate = Integer.parseInt(scan.nextLine());
					Star aStar = new Star(xCoordinate, yCoordinate);
					this.showExistence(aStar);
				} else if (command == 9) {
					System.out.println("9�� �ԷµǾ� �����մϴ�.");
					break;
				} else {
					System.out.println("[����]�߸��� �Է��Դϴ�.");
				}
			} catch (Exception ex) {
				System.out.println("Error Message : " + ex.getMessage());
				continue;
			}
		}
	}
}
