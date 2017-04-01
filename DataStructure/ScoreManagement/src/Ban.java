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
		System.out.println("�л� ���� " + this._numOfStudents + "�� �̰� ����� "
				+ average() + "�Դϴ�");
	}

	public void showGradeCounts() {
		System.out.println("A������ �л��� " + this._A_count + "�� �Դϴ�");
		System.out.println("B������ �л��� " + this._B_count + "�� �Դϴ�");
		System.out.println("C������ �л��� " + this._C_count + "�� �Դϴ�");
		System.out.println("D������ �л��� " + this._D_count + "�� �Դϴ�");
		System.out.println("F������ �л��� " + this._F_count + "�� �Դϴ�");
	}

	public void showNumAboveAverage() {
		System.out.println("����̻� �л� ���� "+numAboveAverage(0, (this._numOfStudents-1), average())+"�� �Դϴ�");
	}

	public void showMaxScore() {
		System.out.println("�л� �� �ְ������� "+maxScore(0, (this._numOfStudents-1))+"�� �Դϴ�");
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
	} // Recursive�� ������ �Լ�

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
	} // Recursive�� ������ �Լ�
}