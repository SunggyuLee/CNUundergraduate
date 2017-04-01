public class LinkedBag {

	private int _size;
	private Node _head;

	public LinkedBag() {
		this._size = 0;
		this._head = null;
	}

	public boolean isEmpty() {
		return (this._size == 0);
	}

	public int size() {
		return this._size;
	}

	public boolean doesContain(Coin givenCoin) {
		boolean found = false;
		Node searchNode = _head;
		while (searchNode != null && !found) {
			if (searchNode.coin().theSameValueAs(givenCoin))
				found = true;
			searchNode = searchNode.next();
		}
		return found;
	}

	public int frequencyOf(Coin givenCoin) {
		int count = 0;
		Node searchNode = _head;
		while (searchNode != null) {
			if (searchNode.coin().theSameValueAs(givenCoin))
				count++;
			searchNode = searchNode.next();
		}
		return count;
	}

	public Coin maxValueCoin() {
		Coin max = new Coin(0);
		Node searchNode = this._head;
		while (searchNode != null) {
			if (searchNode.coin().value() > max.value())
				max = searchNode.coin();
			searchNode = searchNode.next();
		}
		return max;
	}

	public int sumCoinValues() {
		int sum = 0;
		Node searchNode = this._head;
		while (searchNode != null) {
			sum += searchNode.coin().value();
			searchNode = searchNode.next();
		}
		return sum;
	}

	public boolean add(Coin aCoin) {
		if (aCoin.value() > 1000) {
			System.out.println("- [오류] 입력한 액수가 1000을 넘었습니다");
			return false;
		} else if (aCoin.value() < 0) {
			System.out.println("- [오류] 입력한 액수가 0보다 작습니다.");
			return false;
		} else {
			Node newNode = new Node(aCoin);
			newNode.setNext(this._head);
			this._head = newNode;
			this._size++;
			return true;
		}
	}

	public boolean remove(Coin givenCoin) {

		if (this.isEmpty())
			return false;
		else {
			Node previousNode = null;
			Node currentNode = this._head;
			boolean found = false;
			// 첫번째 단계: 삭제할 위치 찾기
			while (currentNode != null && !found) {
				if (currentNode.coin().theSameValueAs(givenCoin))
					found = true;
				else {
					previousNode = currentNode;
					currentNode = currentNode.next();
				}
			}
			// 두번째 단계: 삭제하기
			if (!found)
				return false;
			else {
				if (currentNode == this._head)
					this._head = this._head.next();
				else
					previousNode.setNext(currentNode.next());
			}
			this._size--;
			return true;
		}
	}

	public boolean removeAny() {
		if (this.isEmpty())
			return false;
		else {
			Coin removeCoin = new Coin(this._head.coin().value());
			System.out.println("임의의 코인 "+removeCoin.value()+"을(를) 삭제합니다");
			this._head = this._head.next();
			this._size--;
			return true;
		}
	}

	public void clear() {
		this._size = 0;
		this._head = null;
	}
}
