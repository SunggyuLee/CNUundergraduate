public class LinkedSet {

	private static final int DEFAULT_MAX_SIZE = 100;
	private static final int DEFAULT_MAX_COORDINATE_SIZE = 100;
	private int _maxSize;
	private int _size;
	private Node _head;

	public LinkedSet() {
		this._maxSize = DEFAULT_MAX_SIZE;
		this._size = 0;
		this._head = null;
	}

	public LinkedSet(int givenMaxSize) {
		this._maxSize = givenMaxSize;
		this._size = 0;
		this._head = null;
	}

	public int size() {
		return this._size;
	}

	public boolean isEmpty() {
		return this._size == 0;
	}

	public boolean isFull() {
		return this._size == this._maxSize;
	}

	public boolean doesContain(Star givenStar) {
		boolean found = false;

		Node searchNode = new Node();
		searchNode.setStar(this._head.star());
		while (searchNode != null) {
			if (searchNode.star().theSameValueAs(givenStar)) {
				found = true;
				break;
			} else
				searchNode = searchNode.next();
		}
		return found;
	}

	public boolean add(Star aStar) {

		if (this.isFull())
			return false;
		else if (this.isEmpty()) {
			if (Math.abs(aStar.xCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE
					&& Math.abs(aStar.yCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE) {
				Node newNode = new Node(aStar);
				this._head = newNode;
				this._size++;
				System.out.println("[ADD]"+ aStar.xCoordinate()+","+aStar.yCoordinate()+","+aStar.starName());
				return true;
			} else {
				System.out.println("[오류] 입력 실패");
				return false;
			}
		} else if (Math.abs(aStar.xCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE
				&& Math.abs(aStar.yCoordinate()) <= DEFAULT_MAX_COORDINATE_SIZE
				&& (this.doesContain(aStar)) == false) {

			Node newNode = new Node(aStar);
			this._head.setNext(newNode);
			this._head = newNode;
			this._size++;
			System.out.println("[ADD]"+ aStar.xCoordinate()+","+aStar.yCoordinate()+","+aStar.starName());
			return true;
		} else {
			System.out.println("[오류] 입력 실패");
			return false;
		}
	}

	public boolean remove(Star givenStar) {
		if (this.isEmpty())
			return false;
		else {
			Node previousNode = null;
			Node currentNode = this._head;
			boolean found = false;
			// 첫번째 단계: 삭제할 위치 찾기
			while (currentNode != null && !found) {
				if (currentNode.star().theSameValueAs(givenStar))
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
				if (currentNode == this._head){
					System.out.println("[REMOVE]"+ this._head.star().xCoordinate()+","+this._head.star().yCoordinate()+","+this._head.star().starName());
					this._head = this._head.next();
				}else{
					System.out.println("[REMOVE]"+ currentNode.star().xCoordinate()+","+currentNode.star().yCoordinate()+","+currentNode.star().starName());					
					previousNode.setNext(currentNode.next());
				}
				}
			this._size--;
			return true;
		}
	}

	public boolean removeAny() {
		if (this.isEmpty())
			return false;
		else {
			this._head = this._head.next();
			this._size--;
			System.out.println("[REMOVE] 임의의 별 삭제");
			return true;
		}
	}

	public void clear() {
		Node clearNode = this._head;
		while (clearNode != null) {
			clearNode = null;
			clearNode = clearNode.next();
		}
		this._size = 0;
	}
}
