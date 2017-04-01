


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
				this._head = newNode;	// ������� ��� newNode�� head��
				this._size++;
				return true;
			} else {
				Node currentNode = this._head;	
				Node previousNode = null;	// ��ġ�� ã�� ���� �ֱ����� curNode�� preNode �ΰ��� ���
				while(currentNode != null){
				if(Integer.parseInt(currentNode.element().toString()) < Integer.parseInt(newNode.element().toString()))
					// anElement�� element ���� curNode�� element ������ ũ�� �����. (Node�� head�� �ִ����� �ϱ�� �����Ƿ�)
					break;
				previousNode = currentNode;
				currentNode = currentNode.next();
				}
				if(currentNode == this._head){
					newNode.setNext(this._head);
					this._head = newNode;	// ���� curNode�� head�� ��� newNode�� next�� head�� �� �� head�� �ű�
				}
				else{
					previousNode.setNext(newNode);
					newNode.setNext(currentNode);	// �װ� �ƴ϶�� preNode�� next�� newNode�� �� ��, newNode�� next�� curNode�� �Ѵ�.
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
			return this._head.element();	// �ִ��� head�� �ִ�
	}
	
	@Override
	public Element removeMax() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			return null;
		else{
			Node removeNode = this._head;	// head�� ���� removeNode�� ����
			this._head = this._head.next();	//head�� head.next�� �ִ´�
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
