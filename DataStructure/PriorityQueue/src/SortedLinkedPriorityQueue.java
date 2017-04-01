


public class SortedLinkedPriorityQueue<Element> implements PriorityQueue<Element> {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 100;
	private int _size;
	private int _maxSize;
	private Node<Element> _head;
	
	public SortedLinkedPriorityQueue(){
		this._size = 0;
		this._head = null;
		this._maxSize = DEFAULT_INITIAL_CAPACITY;
	}
	
	public SortedLinkedPriorityQueue(int initialCapacity){
		this._size = 0;
		this._head = null;
		this._maxSize = initialCapacity;
	}
	
	@Override
	public boolean add(Element anElement) {
		// TODO Auto-generated method stub
		if (this.isFull())
			return false;
		else {
			Node newNode = new Node(anElement);
			if (this.isEmpty()) {
				this._head = newNode;	// 비어있을 경우 newNode를 head로
				this._size++;
				return true;
			} else {
				Node currentNode = this._head;	
				Node previousNode = null;	// 위치를 찾고 쉽게 넣기위해 curNode와 preNode 두개를 사용
				while(currentNode != null){
				if(Integer.parseInt(currentNode.element().toString()) < Integer.parseInt(newNode.element().toString()))
					// anElement의 element 값이 curNode의 element 값보다 크면 멈춘다. (Node는 head를 최댓값으로 하기로 했으므로)
					break;
				previousNode = currentNode;
				currentNode = currentNode.next();
				}
				if(currentNode == this._head){
					newNode.setNext(this._head);
					this._head = newNode;	// 만약 curNode가 head의 경우 newNode의 next를 head로 한 후 head를 옮김
				}
				else{
					previousNode.setNext(newNode);
					newNode.setNext(currentNode);	// 그게 아니라면 preNode의 next를 newNode로 한 후, newNode의 next를 curNode로 한다.
				}
				this._size++;
				return true;
			}

		}
	}
	
	@Override
	public Element max() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else
			return this._head.element();	// 최댓값은 head에 있다
	}
	
	@Override
	public Element removeMax() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else{
			Node removeNode = this._head;	// head에 값을 removeNode에 저장
			this._head = this._head.next();	//head에 head.next를 넣는다
			this._size--;
			return (Element) removeNode.element();
		}
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this._head == null;
	}
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return this._size == this._maxSize;
	}
	public Iterator<Element> iterator() {
		return new Iterator();
	}
	public class Iterator<Element> {
		private Node _nextNode;
		
		private Iterator(){
			this._nextNode = _head;
		}
		public boolean hasNext(){
			return (this._nextNode != null);
		}
		public Element next(){
			if(this._nextNode == null)
				return null;
			else {
				@SuppressWarnings("unchecked")
				Element element = (Element) this._nextNode.element();
				this._nextNode = this._nextNode.next();
				return element;
				}
		}
	}
}
