public class Student {
	private int _score;
	private char _grade;

	public Student() {
	}

	public Student(int score) {
		this._score = score;
	}

	public int score() {
		return _score;
	}

	public char grade() {
		this._grade = score2grade();
		return _grade;
	}

	private char score2grade() {
		if (this._score <= 100 && this._score >= 90) {
			return 'A';
		} else if (this._score < 90 && this._score >= 80) {
			return 'B';
		} else if (this._score < 80 && this._score >= 70) {
			return 'C';
		} else if (this._score < 70 && this._score >= 60) {
			return 'D';
		} else if (this._score < 60 && this._score >= 0) {
			return 'F';
		} else {
			return 'X';
		}
	}
}
