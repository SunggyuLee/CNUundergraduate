/* 
import java.util.Scanner;

public class DS1_01_201202149_����� {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Ban ban = new Ban();
		int input = 0;
		System.out.print("������ �Է��Ͻÿ� : ");
		while (scan.hasNext()) {
			input = scan.nextInt();
			if (input > 100) {
				System.out.println("100���� �ʰ��ϴ� ������ �Է��߽��ϴ�");
				System.out.print("������ �Է��Ͻÿ� : ");
			} else if (input < 0) {
				System.out.println("���� ������ ������ �Է��߽��ϴ�");
				System.out.print("������ �Է��Ͻÿ� : ");
			} else {
				Student s = new Student(input);
				System.out.println("���� : " + s.score() + " ���� : " + s.grade());
				ban.addScore(s.score());
				ban.countGrade(s.grade());
				System.out.print("������ �Է��Ͻÿ� : ");
			}
		}
		System.out.println("������ �Է��� ���� �մϴ�.");
		ban.showNumOfStudentsAndAverage();
		ban.showGradeCounts();
		ban.showNumAboveAverage();
		ban.showMaxScore();
	}
} */ //���� 1-1

import java.util.Scanner;

public class DS1_01_201202149_BAEK {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 0;
		int input = 0;
		int studentNumber = 0;
		System.out.print("�л� ���ڸ� �Է��Ͻÿ� : ");
		studentNumber = scan.nextInt();
		Ban ban = new Ban(studentNumber);
		while (n < studentNumber) {
			System.out.print("������ �Է��Ͻÿ� : ");
			input = scan.nextInt();
			if (input > 100) {
				System.out.println("100���� �ʰ��ϴ� ������ �Է��߽��ϴ�");
			} else if (input < 0) {
				System.out.println("���� ������ ������ �Է��߽��ϴ�");
			} else {
				Student s = new Student(input);
				System.out.println("���� : " + s.score() + " ���� : " + s.grade());
				ban.addScore(s.score());
				ban.countGrade(s.grade());
				n++;
			}
		}
		System.out.println("������ �Է��� ���� �մϴ�.");
		ban.showNumOfStudentsAndAverage();
		ban.showGradeCounts();
		ban.showNumAboveAverage();
		ban.showMaxScore();
	}
}