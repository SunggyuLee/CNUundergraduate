public class CircularArrayQueue<Element> {

	private static final int DEFAULT_INITIAL_CAPACITY = 5;
	private int _maxSize;
	private int _front;
	private int _rear;
	private Element[] _elements;

	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		this._elements = (Element[]) new Object[DEFAULT_INITIAL_CAPACITY];
		this._front = 0;
		this._rear = 0;
		this._maxSize = DEFAULT_INITIAL_CAPACITY;
	}

	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int initialCapacity) {
		this._elements = (Element[]) new Object[initialCapacity];
		this._front = 0;
		this._rear = 0;
		this._maxSize = initialCapacity;
	}

	public int front() {
		return this._front;
	}

	public int rear() {
		return this._rear;
	}

	public int maxSize() {
		return this._maxSize;
	}

	public boolean isEmpty() {
		return this._front == this._rear;
	}

	public boolean isFull() {
		if (this._rear + 1 == this._maxSize)
			return this._front == 0;
		else
			return this._rear + 1 == this._front;
	}

	public int size() {
		if (this._front <= this._rear)
			return this._rear - this._front;
		else
			return (this._rear + (this._maxSize)) - this._front;
	}

	public Element frontElement() {
		Element returnElement = null;

		if (!this.isEmpty()) {
			if (this._front + 1 == this._maxSize)
				returnElement = this._elements[0];
			else
				returnElement = this._elements[this._front + 1];
		}
		return returnElement;
	}

	public boolean enQueue(Element anElement) {
		if (this.isFull())
			return false;
		else {
			if (this._rear + 1 == this._maxSize) {
				this._rear = 0;
				this._elements[this._rear] = anElement;
				return true;
			} else {
				this._elements[++this._rear] = anElement;
				return true;
			}
		}
	}

	public Element deQueue() {
		Element frontElement = null;

		if (!this.isEmpty()) {
			if (this._front + 1 == this._maxSize) {
				this._front = 0;
				frontElement = this._elements[this._front];
				this._elements[this._front] = null;
			} else {
				frontElement = this._elements[++this._front];
				this._elements[this._front] = null;
			}
		}
		return frontElement;
	}

	public void clear() {
		this._front = 0;
		this._rear = 0;
		this._elements = null;
	}

	public Element findElementByOrder(int givenPosition) {
		if(givenPosition >= this._maxSize)
			return this._elements[givenPosition - this._maxSize];
		else
			return this._elements[givenPosition];
	}
}
