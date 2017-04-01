public class ArrayBag {

	private static final int DEFAULT_MAX_SIZE = 100;
	private int _totalValue;
	private int _maxSize = 0;
	private int _size = 0;
	private Coin[] _coins;

	public ArrayBag() {
		this._coins = new Coin[DEFAULT_MAX_SIZE];
	}

	public ArrayBag(int givenMaxSize) {
		this._coins = new Coin[givenMaxSize];
		this._maxSize = givenMaxSize;
	}

	public int size() {
		return this._size;
	}

	public boolean isEmpty() {
		return (this._size == 0);
		}

	public boolean isFull() {
		return (this._size == this._maxSize);
	}
		

	public boolean doesContain(Coin givenCoin) {
		boolean found = false;
		for (int i = 0; i < this._size; i++) {
			if (this._coins[i].theSameValueAs(givenCoin)) {
				found = true;
			}
		}
		return found;
	}

	public int frequencyOf(Coin givenCoin) {
		int frequencyCount = 0;
		for (int i = 0; i < this._size; i++) {
			if (this._coins[i].theSameValueAs(givenCoin)) {
				frequencyCount++;
			}
		}
		return frequencyCount;
	}

	/*
	 * public boolean doesContain(Coin givenCoin) { boolean found = false; for
	 * (Coin c : this._coins) { if (c.theSameValueAs(givenCoin)) { found = true;
	 * } } return found; } //Fast Enumeration 형태
	 * 
	 * public int frequencyOf(Coin givenCoin) { int frequencyCount = 0; for
	 * (Coin c : this._coins) { if (c.theSameValueAs(givenCoin)) {
	 * frequencyCount++; } } return frequencyCount; } //Fast Enumeration 형태
	 */// 1-2문제 형태

	public Coin maxCoinValues() {
		Coin max = new Coin(0);
		for (int i = 0; i < this._size; i++) {
			if (this._coins[i].value() > max.value()) {
				max = this._coins[i];
			}
		}
		return max;
	}

	public int sumCoinValues() {
		this._totalValue = 0;
		for (int i = 0; i < this._size; i++) {
			this._totalValue += this._coins[i].value();
		}
		return this._totalValue;
	}

	public boolean add(Coin aCoin) {
		if (this.isFull()) {
			return false;
		} else {
			if (aCoin.value() > 1000) {
				return false;
			} else if (aCoin.value() < 0) {
				return false;
			} else {
				this._coins[this._size] = aCoin;
				this._size++;
				return true;
			}
		}
	}

	public boolean remove(Coin givenCoin) {

		int foundIndex;
		boolean found = false;
		for (foundIndex = 0; foundIndex < this._maxSize && !found; foundIndex++) {
			if (this._coins[foundIndex].theSameValueAs(givenCoin)) {
				found = true;
			}
		}
		foundIndex--;
		if (!found) { // !found는 여기서 false를 의미
			return false;
		} else {
			for (int j = foundIndex; j < this._size - 1; j++) {
				this._coins[j] = this._coins[j + 1];
			}
			this._size--;
			return true;
		}
	}

	public void clear() {
		for (int i = 0; i < this._size; i++) {
			this._coins[i] = null;
		}
		this._size = 0;
	}
}