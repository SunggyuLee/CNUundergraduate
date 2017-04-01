public class CircularLinkedQueue<Element> {

	private static final int DEFAULT_INITIAL_CAPACITY = 5;
	private int _size;
	private int _maxSize;
	private Node<Character> _rear;

	@SuppressWarnings("unchecked")
	public CircularLinkedQueue() {
		this._size = 0;
		this._rear = null;
		this._maxSize = DEFAULT_INITIAL_CAPACITY;
	}

	@SuppressWarnings("unchecked")
	public CircularLinkedQueue(int initialCapacity) {
		this._size = 0;
		this._rear = null;
		this._maxSize = initialCapacity;
	}

	public int maxSize() {
		return this._maxSize;
	}

	public boolean isEmpty() {
		return this._rear == null;
	}

	public boolean isFull() {
		return this._size == this._maxSize;
	}

	public int size() {
		return this._size;
	}

	public Element frontElement() {
		return (Element) this._rear.next().element();
	}

	public boolean enQueue(Element anElement) {
		if (this.isFull())
			return false;
		else {
			Node newNode = new Node(anElement);
			if (this.isEmpty()) {
				this._rear = newNode;
				this._rear.setNext(this._rear);
				this._size++;
				return true;
			} else {
				newNode.setNext(this._rear.next());
				this._rear.setNext(newNode);
				this._rear = newNode;
				this._size++;
				return true;
			}

		}
	}

	public Element deQueue() {
		Element frontElement = null;
		if(!this.isEmpty()){
			frontElement = (Element) this._rear.next().element();
			if(this._size == 1){
				this._rear = null;
				this._size--;
			}
			else{
				this._rear.setNext(this._rear.next().next());
				this._size--;
			}
		}
		return frontElement;
	}

	public void clear() {
		this._size = 0;
		this._rear = null;
	}


	public Element findElementByOrder(int givenPosition) {
		int count = 0;
		Node findElement = this._rear.next();
		while(count < givenPosition){
			findElement = findElement.next();
			count++;
		}
		return (Element) findElement.element();
	}
}