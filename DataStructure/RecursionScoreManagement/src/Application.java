import java.util.Scanner;

public class Application {
	private Ban ban;

	public void showClassInfo() {
		// �л����� ������ ��հ� ��� �̻��� �л���, �ְ���, �������� ����Ͽ� ����Ѵ�.
		ban.processClassInfo();
		System.out.println("��������� " + ban.average() + " �Դϴ�");
		System.out.println("����̻��� �л��� ��� " + ban.aboveAverage() + "�� �Դϴ�");
		System.out.println("�ְ����� " + ban.maxScore() + "�� �Դϴ�");
		System.out.println("�������� " + ban.minScore() + "�� �Դϴ�");

	}

	public void showGradeInfo() {
		// ������ �ο��ϰ�, ������ �л����� ����Ͽ� ����Ѵ�.
		this.ban.assignGrades();
		this.ban.countStudentsByGrade();
		System.out.println("A ������ ��� " + this.ban.numOf_A()+"�� �Դϴ�.");
		System.out.println("B ������ ��� " + this.ban.numOf_B()+"�� �Դϴ�.");
		System.out.println("C ������ ��� " + this.ban.numOf_C()+"�� �Դϴ�.");
		System.out.println("D ������ ��� " + this.ban.numOf_D()+"�� �Դϴ�.");
		System.out.println("F ������ ��� " + this.ban.numOf_F()+"�� �Դϴ�.");
	}

	public void showStudentInfoList() {
		// ���� ������ ������ ��, �л� ���� ����� ����Ѵ�
		System.out.println("�л����� ������ ����Դϴ�.");
		ban.sortStudentsByScore();
		for (int i = 0; i < ban.numOfStudents(); i++)
			System.out.println("�й� : "
					+ ban.findScoreByOrder(i).studentNo() + " ���� : "
					+ ban.findScoreByOrder(i).score() + " ���� : " + ban.findScoreByOrder(i).grade());
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		int score = 0;
		String command = null;
		String studentNo = null;
		System.out.println("<< ���� ó���� �����մϴ� >>");
		ban = new Ban();
		while (true) {
			try {
				System.out.print("? ������ �Է��Ϸ��� ��Y���� , �Է��� �����Ϸ��� ��N���� ġ�ÿ�: ");
				command = scan.nextLine();
				if (command.equals("Y")) {
					System.out.print("- �й��� �Է��Ͻÿ�: ");
					studentNo = scan.nextLine();
					System.out.print("- ������ �Է��Ͻÿ�: ");
					score = Integer.parseInt(scan.nextLine());
					Student aStudent = new Student(score, studentNo);
					ban.add(aStudent);
				} else {
					System.out.println("�Է��� �����մϴ�.");
					break;
				}
			} catch (Exception ex) {
				System.out.println("Error Message : " + ex.getMessage());
				continue;
			}
		}
		this.showClassInfo();
		System.out.println("");
		this.showGradeInfo();
		System.out.println("");
		this.showStudentInfoList();
		System.out.println("");
		System.out.print("<< ���� ó���� �����մϴ� >>");
	}
}