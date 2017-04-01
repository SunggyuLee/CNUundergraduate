public class SortedLinkedDictionary<Key, Obj> {

	private static final int DEFAULT_INITIAL_CAPACITY = 20;
	private int _maxLength;
	private int _length;
	private Node<Key, Obj> _head;

	public SortedLinkedDictionary() {
		this._length = 0;
		this._head = null;
		this._maxLength = DEFAULT_INITIAL_CAPACITY;
	}

	public SortedLinkedDictionary(int givenMaxSize) {
		this._length = 0;
		this._head = null;
		this._maxLength = givenMaxSize;
	}

	public boolean isEmpty() {
		return this._length == 0;
	}

	public boolean isFull() {
		return this._length == this._maxLength;
	}

	public int size() {
		return this._length;
	}

	public boolean doesKeyExist(Key givenKey) {
		Node searchNode = this._head;
		while (searchNode != null) {
			if (searchNode.element().compareTo(givenKey) == 0)
				return true;
			searchNode = searchNode.next();
		}
		return false;
	}

	public Obj objectForKey(Key givenKey) {
		Node searchNode = this._head;
		while (searchNode != null) {
			if (searchNode.element().compareTo(givenKey) == 0)
				return (Obj) searchNode.element().object();
			searchNode = searchNode.next();
		}
		return null;
	}

	public boolean addKeyandObject(Key aKey, Obj anObject) {
		if (this.doesKeyExist(aKey))
			return false;
		else if (this.isFull())
			return false;
		else {
			Node currentNode = this._head;
			Node previousNode = null;
			Node newNode = new Node(new Element(aKey,anObject));
			if(this.isEmpty()){
				this._head = newNode;
				this._length++;
				return true;
			}
			else{
			while (currentNode != null && currentNode.element().compareTo(aKey) < 0) {
				previousNode = currentNode;
				currentNode = currentNode.next();
			}
			if(currentNode == this._head){
				newNode.setNext(currentNode);
				this._head = newNode;
			}
			else{
			newNode.setNext(currentNode);
			previousNode.setNext(newNode);
			}
			this._length++;
			return true;
			}
		}
	}

	public Obj removeObjectForKey(Key givenKey) {
		if (this.isEmpty())
			return null;
		else {
			Node currentNode = this._head;
			Node previousNode = null;
			Node removeNode = null;
			while (currentNode != null) {
				if (currentNode.element().compareTo(givenKey) == 0)
					break;
				previousNode = currentNode;
				currentNode = currentNode.next();
			}
			if(currentNode == this._head){
				removeNode = this._head;
				this._head = this._head.next();
			}
			else{
			removeNode = currentNode;
			previousNode.setNext(currentNode.next());
			currentNode.setNext(null);
			}
			this._length--;
			return (Obj) removeNode.element().object();
		}
	}

	public boolean replaceObjectFOrKey(Obj newObject, Key givenKey) {
		Node searchNode = this._head;
		while (searchNode != null) {
			if (searchNode.element().compareTo(givenKey) == 0){
				searchNode.element().setObject(newObject);
				return true;
			}
			searchNode = searchNode.next();
		}
		return false;
	}

	public void clear() {
		this._length = 0;
		this._head = null;
		this._maxLength = DEFAULT_INITIAL_CAPACITY;
	}

}
