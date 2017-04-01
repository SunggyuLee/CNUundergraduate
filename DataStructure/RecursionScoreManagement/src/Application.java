import java.util.Scanner;

public class Application {
	private Ban ban;

	public void showClassInfo() {
		// 학생들의 점수의 평균과 평균 이상인 학생수, 최고점, 최저점을 계산하여 출력한다.
		ban.processClassInfo();
		System.out.println("평균점수는 " + ban.average() + " 입니다");
		System.out.println("평균이상인 학생은 모두 " + ban.aboveAverage() + "명 입니다");
		System.out.println("최고점은 " + ban.maxScore() + "점 입니다");
		System.out.println("최저점은 " + ban.minScore() + "점 입니다");

	}

	public void showGradeInfo() {
		// 학점을 부여하고, 학점별 학생수를 계산하여 출력한다.
		this.ban.assignGrades();
		this.ban.countStudentsByGrade();
		System.out.println("A 학점은 모두 " + this.ban.numOf_A()+"명 입니다.");
		System.out.println("B 학점은 모두 " + this.ban.numOf_B()+"명 입니다.");
		System.out.println("C 학점은 모두 " + this.ban.numOf_C()+"명 입니다.");
		System.out.println("D 학점은 모두 " + this.ban.numOf_D()+"명 입니다.");
		System.out.println("F 학점은 모두 " + this.ban.numOf_F()+"명 입니다.");
	}

	public void showStudentInfoList() {
		// 성적 순으로 정렬한 후, 학생 정보 목록을 출력한다
		System.out.println("학생들의 성적순 목록입니다.");
		ban.sortStudentsByScore();
		for (int i = 0; i < ban.numOfStudents(); i++)
			System.out.println("학번 : "
					+ ban.findScoreByOrder(i).studentNo() + " 점수 : "
					+ ban.findScoreByOrder(i).score() + " 학점 : " + ban.findScoreByOrder(i).grade());
	}

	public void run() {
		Scanner scan = new Scanner(System.in);
		int score = 0;
		String command = null;
		String studentNo = null;
		System.out.println("<< 성적 처리를 시작합니다 >>");
		ban = new Ban();
		while (true) {
			try {
				System.out.print("? 성적을 입력하려면 ‘Y’를 , 입력을 종료하려면 ‘N’을 치시오: ");
				command = scan.nextLine();
				if (command.equals("Y")) {
					System.out.print("- 학번을 입력하시오: ");
					studentNo = scan.nextLine();
					System.out.print("- 점수를 입력하시오: ");
					score = Integer.parseInt(scan.nextLine());
					Student aStudent = new Student(score, studentNo);
					ban.add(aStudent);
				} else {
					System.out.println("입력을 종료합니다.");
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
		System.out.print("<< 성적 처리를 종료합니다 >>");
	}
}