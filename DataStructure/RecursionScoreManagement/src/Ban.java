public class Ban {

	private static final int MAXSTUDENTSIZE = 100;
	private Student[] _students;
	private int _numOfStudents; // 전체 학생수
	private int _maxStudents; // 총 입력 가능한 학생 수
	private float _average; // 평균성적
	private int _aboveAverage; // 평균 성적 이상의 성적을 가진 학생수
	private int _maxScore; // 최고점
	private int _minScore; // 최저점
	private int _numOf_A;
	private int _numOf_B;
	private int _numOf_C;
	private int _numOf_D;
	private int _numOf_F;

	public Ban() {
		this._students = new Student[MAXSTUDENTSIZE];
		this._maxStudents = MAXSTUDENTSIZE;
		this._numOfStudents = 0;
	}

	public Ban(int maxStudents) {
		this._students = new Student[maxStudents];
		this._maxStudents = maxStudents;
		this._numOfStudents = 0;
	}

	public boolean isEmpty() {
		return (this._numOfStudents == 0);
	}

	public boolean isFull() {
		return (this._numOfStudents == this._maxStudents);
	}

	public int numOfStudents() {
		return this._numOfStudents;
	}

	public int maxStudents() {
		return this._maxStudents;
	}

	public float average() {
		return this._average;
	}

	public int aboveAverage() {
		return this._aboveAverage;
	}

	public int maxScore() {
		return this._maxScore;
	}

	public int minScore() {
		return this._minScore;
	}

	public int numOf_A() {
		return this._numOf_A;
	}

	public int numOf_B() {
		return this._numOf_B;
	}

	public int numOf_C() {
		return this._numOf_C;
	}

	public int numOf_D() {
		return this._numOf_D;
	}

	public int numOf_F() {
		return this._numOf_F;
	}

	public void assignGrades() {

		for (int i = 0; i < this._numOfStudents; i++)
			this._students[i].setGrade(this.score2grade(this._students[i].score()));

	}

	private char score2grade(int score) {
		if (score <= 100 && score >= 90) {
			return 'A';
		} else if (score < 90 && score >= 80) {
			return 'B';
		} else if (score < 80 && score >= 70) {
			return 'C';
		} else if (score < 70 && score >= 60) {
			return 'D';
		} else if (score < 60 && score >= 0) {
			return 'F';
		} else {
			return 'X';
		}
	}

	public void countStudentsByGrade() {

		for (int i = 0; i < this._numOfStudents; i++)
			this.countGrade(this._students[i].grade());
		
	}

	public void countGrade(char grade) {
		if (grade == 'A') {
			this._numOf_A++;
		} else if (grade == 'B') {
			this._numOf_B++;
		} else if (grade == 'C') {
			this._numOf_C++;
		} else if (grade == 'D') {
			this._numOf_D++;
		} else if (grade == 'F') {
			this._numOf_F++;
		}
	}

	private boolean doesContain(Student aStudent) {
		boolean found = false;

		for (int i = 0; i < this._numOfStudents && !found; i++) {
			if (this._students[i].studentNo().equals(aStudent.studentNo())) {
				found = true;
			}
		}
		return found;
	}

	public boolean add(Student aStudent) {
		if (this.isFull()) {
			System.out.println("[오류] 입력 실패");
			return false;
		} else if (aStudent.score() < 0 || aStudent.score() > 100) {
			System.out.println("[오류] 입력 실패");
			return false;
		} else if (this.doesContain(aStudent)) {
			System.out.println("[오류] 입력 실패");
			return false; // 2번
		} else {
			this._students[this._numOfStudents] = aStudent;
			this.countGrade(aStudent.grade());
			this._numOfStudents++;
			return true;
		}
	}

	public void processClassInfo() {
		// _average에 this.calcAverage에서 계산한 값을 저장
		this._average = this.calcAverage();
		// _aboveAverage에 this.countAboveAverage에서 계산 한 값을 저장
		this._aboveAverage = this.countAboveAverage(0, this._numOfStudents - 1,
				this._average);
		// _maxScore에 this.findMaxScore에서 계산 한 값을 저장
		this._maxScore = this.findMaxScore(0, this._numOfStudents - 1);
		// _minScore에 this.findMinScore에서 계산 한 값을 저장
		this._minScore = this.findMinScore(0, this._numOfStudents - 1);
	}

	public Student findScoreByOrder(int givenNumber) {
		return this._students[givenNumber];
	}

	public void sortStudentsByScore() {
		// quickSort를 사용하여 정렬한다.
		this.quickSort(0, this._numOfStudents - 1);
	}

	private float calcAverage() {
		int totalScore = 0;

		for (int i = 0; i < this._numOfStudents; i++) {
			totalScore += this._students[i].score();
		}
		return (totalScore / this._numOfStudents);
	}

	private int countAboveAverage(int left, int right, float average) {
		int count = 0;
		if (left <= right) {
			count = countAboveAverage(left + 1, right, average);
			if (this._students[left].score() >= average)
				count++;
		} else
			count = 0;
		return count;
	}

	private int findMaxScore(int left, int right) {

		int mid;
		int findLeftMinScore;
		int findRightMinScore;

		if (left == right)
			return this._students[left].score();
		else if (left < right) {
			mid = ((left + right) / 2);
			findLeftMinScore = findMaxScore(left, mid);
			findRightMinScore = findMaxScore(mid + 1, right);

			if (findLeftMinScore > findRightMinScore)
				return findLeftMinScore;
			else
				return findRightMinScore;
		} else
			return 0;
	}

	private int findMinScore(int left, int right) {
		int minScore = 0;

		if (left < right) {
			minScore = findMinScore(left + 1, right);
			if (this._students[left].score() < minScore)
				minScore = this._students[left].score();
		} else if (left == right)
			minScore = this._students[left].score();
		return minScore;
	}

	private void quickSort(int left, int right) {
		int mid;
		if (left < right) {
			mid = this.partition(left, right);
			this.quickSort(left, mid - 1);
			this.quickSort(mid + 1, right);
		}
	}

	private int partition(int left, int right) {
		Student pivot = this._students[left];
		int up = left;
		int down = right + 1;
		do {
			do {
				up++;
			} while (this._students[up].score() < pivot.score() && up != right);
			do {
				down--;
			} while (this._students[down].score() > pivot.score()
					&& down != left);
			if (up < down) {
				this.swap(up, down);
			}
		} while (up < down);
		swap(left, down);
		return down;
	}

	private void swap(int A, int B) {
		Student temp = this._students[A];
		this._students[A] = this._students[B];
		this._students[B] = temp;
	}
}
