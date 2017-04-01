public class Student {
	// 비공개 인스턴스 변수
	private int _score; // 학생의 점수
	private String _studentNo; // 학번
	private char _grade; // 학점
	
	public Student() {
		this._score = 0;
		this._studentNo = null;
	}

	public Student(int aScore, String aStudentNo) {
		this._score = aScore;
		this._studentNo = aStudentNo;
	}

	public int score() {
		return this._score;
	}
	
	public String studentNo() {
		return this._studentNo;
	} 
	
	public char grade() {
		return this._grade;
	} 


	public void setScore(int givenScore) {
		this._score = givenScore;
	}
	
	public void setStudentNo(String givenStudentNo) {
		this._studentNo = givenStudentNo;
	} 
	
	public void setGrade(char givenGrade) {
		this._grade = givenGrade;
	}
}