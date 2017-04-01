/* 
import java.util.Scanner;

public class DS1_01_201202149_백승진 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Ban ban = new Ban();
		int input = 0;
		System.out.print("점수를 입력하시오 : ");
		while (scan.hasNext()) {
			input = scan.nextInt();
			if (input > 100) {
				System.out.println("100점을 초과하는 점수를 입력했습니다");
				System.out.print("점수를 입력하시오 : ");
			} else if (input < 0) {
				System.out.println("음의 정수를 점수로 입력했습니다");
				System.out.print("점수를 입력하시오 : ");
			} else {
				Student s = new Student(input);
				System.out.println("점수 : " + s.score() + " 학점 : " + s.grade());
				ban.addScore(s.score());
				ban.countGrade(s.grade());
				System.out.print("점수를 입력하시오 : ");
			}
		}
		System.out.println("데이터 입력을 종료 합니다.");
		ban.showNumOfStudentsAndAverage();
		ban.showGradeCounts();
		ban.showNumAboveAverage();
		ban.showMaxScore();
	}
} */ //문제 1-1

import java.util.Scanner;

public class DS1_01_201202149_BAEK {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 0;
		int input = 0;
		int studentNumber = 0;
		System.out.print("학생 숫자를 입력하시오 : ");
		studentNumber = scan.nextInt();
		Ban ban = new Ban(studentNumber);
		while (n < studentNumber) {
			System.out.print("점수를 입력하시오 : ");
			input = scan.nextInt();
			if (input > 100) {
				System.out.println("100점을 초과하는 점수를 입력했습니다");
			} else if (input < 0) {
				System.out.println("음의 정수를 점수로 입력했습니다");
			} else {
				Student s = new Student(input);
				System.out.println("점수 : " + s.score() + " 학점 : " + s.grade());
				ban.addScore(s.score());
				ban.countGrade(s.grade());
				n++;
			}
		}
		System.out.println("데이터 입력을 종료 합니다.");
		ban.showNumOfStudentsAndAverage();
		ban.showGradeCounts();
		ban.showNumAboveAverage();
		ban.showMaxScore();
	}
}