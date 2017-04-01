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
			System.out.println("- [����] �Է��� �׼��� 1000�� �Ѿ����ϴ�");
			return false;
		} else if (aCoin.value() < 0) {
			System.out.println("- [����] �Է��� �׼��� 0���� �۽��ϴ�.");
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
			// ù��° �ܰ�: ������ ��ġ ã��
			while (currentNode != null && !found) {
				if (currentNode.coin().theSameValueAs(givenCoin))
					found = true;
				else {
					previousNode = currentNode;
					currentNode = currentNode.next();
				}
			}
			// �ι�° �ܰ�: �����ϱ�
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
			System.out.println("������ ���� "+removeCoin.value()+"��(��) �����մϴ�");
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
