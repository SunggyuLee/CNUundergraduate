public class Ban {

	int i = 0;
	private static final int DEFAULT_MAX_SIZE = 100;
	private int _numOfStudents = 0;
	private Student[] _scores;
	private int _totalScores = 0;
	private int _A_count = 0;
	private int _B_count = 0;
	private int _C_count = 0;
	private int _D_count = 0;
	private int _F_count = 0;

	public Ban() {
		this._scores = new Student[DEFAULT_MAX_SIZE];
	}

	public Ban(int maxNumber) {
		this._scores = new Student[maxNumber];
	}

	public void addScore(int score) {
		this._numOfStudents++;
		this._scores[i] = new Student(score);
		this._totalScores += this._scores[i].score();
		i++;
	}

	public void countGrade(char grade) {
		if (grade == 'A') {
			this._A_count++;
		} else if (grade == 'B') {
			this._B_count++;
		} else if (grade == 'C') {
			this._C_count++;
		} else if (grade == 'D') {
			this._D_count++;
		} else if (grade == 'F') {
			this._F_count++;
		}
	}

	public void showNumOfStudentsAndAverage() {
		System.out.println("학생 수는 " + this._numOfStudents + "명 이고 평균은 "
				+ average() + "입니다");
	}

	public void showGradeCounts() {
		System.out.println("A학점인 학생은 " + this._A_count + "명 입니다");
		System.out.println("B학점인 학생은 " + this._B_count + "명 입니다");
		System.out.println("C학점인 학생은 " + this._C_count + "명 입니다");
		System.out.println("D학점인 학생은 " + this._D_count + "명 입니다");
		System.out.println("F학점인 학생은 " + this._F_count + "명 입니다");
	}

	public void showNumAboveAverage() {
		System.out.println("평균이상 학생 수는 "+numAboveAverage(0, (this._numOfStudents-1), average())+"명 입니다");
	}

	public void showMaxScore() {
		System.out.println("학생 중 최고점수는 "+maxScore(0, (this._numOfStudents-1))+"점 입니다");
	}

	private float average() {
		return (this._totalScores) / (this._numOfStudents);
	}

	private int numAboveAverage(int from, int to, float average) {
		int count = 0;
		if (from <= to) {
			count = numAboveAverage(from + 1, to, average);
			if (this._scores[from].score() >= average)
				count++;
		} else {
			count = 0;
		}
		return count;
	} // Recursive로 구현한 함수

	private int maxScore(int from, int to) {
		int max;
		if (from < to) {
			max = maxScore(from + 1, to);
			if (this._scores[from].score() > max)
				max = this._scores[from].score();
		} else {
			max = this._scores[from].score();
		}
		return max;
	} // Recursive로 구현한 함수
}